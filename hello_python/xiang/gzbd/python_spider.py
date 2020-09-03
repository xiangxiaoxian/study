#!/usr/bin/env python3
# -*- coding: utf-8 -*-
__author__ = "XianDaXia"

'''
爬虫
'''

import requests
from bs4 import BeautifulSoup
import re


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

    # 正则表达式提取数据，并装载到dict
    line_re = r'全省累计报告新型冠状病毒肺炎确诊病例(\d+)例\(其中境外输入(\d+)例\），累计治愈出院(\d+)例，死亡(\d+)例，目前在院隔离治疗(\d+)例，(\d+)人尚在接受医学观察'
    line_compile = re.compile(line_re)
    if re.search(line_compile, line):
        new_dict['确诊数'] = re.search(line_compile, line).group(1)
        new_dict['境外输入数'] = re.search(line_compile, line).group(2)
        new_dict['治愈数'] = re.search(line_compile, line).group(3)
        new_dict['死亡数'] = re.search(line_compile, line).group(4)
        new_dict['隔离数'] = re.search(line_compile, line).group(5)
        new_dict['观察数'] = re.search(line_compile, line).group(6)
    return new_dict


if __name__ == '__main__':
    url = "http://wsjkw.sc.gov.cn/scwsjkw/gzbd01/2020/9/3/fe0eb6e3101d4709a9bbd27f5a12ae78.shtml"
    print(new_page_data(url))