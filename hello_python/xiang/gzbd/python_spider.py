#!/usr/bin/env python3
# -*- coding: utf-8 -*-
__author__ = "XianDaXia"

'''
爬虫
'''

import requests
from bs4 import BeautifulSoup
import re

__wjw_regin = '四川'
__wjw_domain = 'http://wsjkw.sc.gov.cn'
__wjw_base_url = '/scwsjkw/gzbd01/ztwzlmgl.shtml'
__wjw_page_count = 5


# 爬取页面数据
def new_page_data(url):
    new_dict = {}
    # requests获取页面内容
    r = requests.get(url)
    r.encoding = r.apparent_encoding
    # 解析页面标签
    bs = BeautifulSoup(r.text, 'html.parser')
    span_list = bs.find_all(name='span', attrs={'style': 'font-size: 12pt;'})
    line = span_list[1].get_text()
    line1 = span_list[0].get_text()
    # 正则表达式提取数据，并装载到dict
    line_re = r'全省累计报告新型冠状病毒肺炎确诊病例(\d+)例\(其中境外输入(\d+)例\），累计治愈出院(\d+)例，死亡(\d+)例，目前在院隔离治疗(\d+)例，(\d+)'
    line_compile = re.compile(line_re)
    re_1 = re.search(line_compile, line)
    re_2 = re.search(line_compile, line1)
    if re_1:
        new_dict['确诊数'] = re_1.group(1)
        new_dict['境外输入数'] = re_1.group(2)
        new_dict['治愈数'] = re_1.group(3)
        new_dict['死亡数'] = re_1.group(4)
        new_dict['隔离数'] = re_1.group(5)
        new_dict['观察数'] = re_1.group(6)
    if re_2:
        new_dict['确诊数'] = re_2.group(1)
        new_dict['境外输入数'] = re_2.group(2)
        new_dict['治愈数'] = re_2.group(3)
        new_dict['死亡数'] = re_2.group(4)
        new_dict['隔离数'] = re_2.group(5)
        new_dict['观察数'] = re_2.group(6)
    return new_dict


# 爬取列表
def new_pages_data(url):
    new_lists = []
    r = requests.get(url)
    # 编码
    r.encoding = r.apparent_encoding
    # 解析页面标签
    bs = BeautifulSoup(r.text, 'html.parser')
    li_list = bs.find(name='div', attrs={'class': 'contMain fontSt'}).find_all(name='li')
    for li in li_list:
        child_span = li.findChildren('span', recursive=False)[0]
        child_a = li.findChildren('a', recursive=False)[0]
        new_page_url = __wjw_domain + child_a.get('href')
        new_dict = new_page_data(new_page_url)
        new_dict['日期'] = child_span.get_text()
        new_dict['地区'] = __wjw_regin
        new_lists.append(new_dict)
    return new_lists


# 爬取所有数据
def gzbd_all_data():
    all_data = []
    # 创建新闻列表页面链接 获取列表页的数据
    new_pages_url = __wjw_domain
    for i in range(1, __wjw_page_count + 1):
        if i == 1:
            new_pages_url += __wjw_base_url
        else:
            l = __wjw_base_url.split('.')
            l.insert(1, '_%d.' % (i,))
            new_pages_url += ''.join(l)
        print('URL: %s' % (new_pages_url,))
        new_lists = new_pages_data(new_pages_url)
        print(' %s' % (new_lists))
        all_data += new_lists
        new_pages_url = __wjw_domain
    return all_data


if __name__ == '__main__':
    # url = "http://wsjkw.sc.gov.cn/scwsjkw/gzbd01/2020/8/17/c7b397064aaa46b49618b8d2d642bb8c.shtml"
    # print(new_page_data(url))
    # url = "http://wsjkw.sc.gov.cn/scwsjkw/gzbd01/ztwzlmgl.shtml"
    # print(new_pages_data(url))
    gzbd_all_data()
