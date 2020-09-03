#!/usr/bin/env python3
# -*- coding: utf-8 -*-
__author__ = "XianDaXia"

import openpyxl
import random
import excel_utils


def write_excel():
    header = ["一", "二", "三"]
    body = list()
    file_path = "F:/uploadFile/two.xlsx"
    for i in range(1, 10):
        line = list()
        for j in range(1, len(header) + 1):
            line.append(j * random.randint(1, 10))
        body.append(line)
    excel_utils.creat_excel(header, body, file_path)


if __name__ == "__main__":
    write_excel()
