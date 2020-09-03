#!/usr/bin/env python3
# -*- coding: utf-8 -*-
__author__ = "XianDaXia"


import openpyxl
import random


def creat_excel(header, body, file_path):
    # 获取workbook对象
    workbook = openpyxl.Workbook()
    # 获取sheet对象
    active_sheet = workbook.active
    # 数据操作
    active_sheet.append(header)
    for i in body:
        active_sheet.append(i)
    # 文件保存
    workbook.save(filename=file_path)
