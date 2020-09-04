#!/usr/bin/env python3
# -*- coding: utf-8 -*-
__author__ = "XianDaXia"

'''
正则
'''

import re


def sss():
    new_dict = {}
    s = "全省累计报告新型冠状病毒肺炎确诊病例623例(其中境外输入82例），累计治愈出院606例，死亡3例，目前在院隔离治疗14例，548人尚在接受医学观察。"
    line_re = r'全省累计报告新型冠状病毒肺炎确诊病例(\d+)例\(其中境外输入(\d+)例\），累计治愈出院(\d+)例，死亡(\d+)例，目前在院隔离治疗(\d+)例，(\d+)'
    line_compile = re.compile(line_re)
    if re.search(line_compile, s):
        new_dict['确诊数'] = re.search(line_compile, s).group(1)
        new_dict['境外输入数'] = re.search(line_compile, s).group(2)
        new_dict['治愈数'] = re.search(line_compile, s).group(3)
        new_dict['死亡数'] = re.search(line_compile, s).group(4)
        new_dict['隔离数'] = re.search(line_compile, s).group(5)
        new_dict['观察数'] = re.search(line_compile, s).group(6)
        print('ssss')
        print(new_dict)


if __name__ == '__main__':
    sss()