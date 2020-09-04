#!/usr/bin/env python3
# -*- coding: utf-8 -*-
__author__ = "XianDaXia"

'''
gzbd 存数据
'''

import mysql_utils
import excel_utils
import xiang.gzbd.python_spider


def storage_mysql():
    # 获取数据
    gzbd_all_data = xiang.gzbd.python_spider.gzbd_all_data()
    print(gzbd_all_data)
    for item in gzbd_all_data:
        # sql
        epidemic_data = item['日期']
        query_sql = "select * from gzbd_epidemic where date = '%s'" % (epidemic_data,)
        insert_sql = "insert into gzbd_epidemic (region, date, diagnosis, overseas_import, cure, " \
                     "death, therapy, observation) values ('%s', '%s', %s, %s, %s, %s, %s, %s)" %\
                     (item["地区"], item["日期"], item.get("确诊数", None), item.get("境外输入数", None),
                      item.get("治愈数", None), item.get("死亡数", None), item.get("隔离数", None), item.get("观察数", None))
        insert_sql = insert_sql.replace('None', 'NULL')

        # 查询数据，以此判断是否需要插入
        result = mysql_utils.execute(query_sql)
        if len(result) == 0:
            # 插入数据
            result = mysql_utils.execute(insert_sql)
            if result > 0:
                print('success')
            else:
                print('error')


def storage_excel():
    # 获取数据
    gzbd_all_data = xiang.gzbd.python_spider.gzbd_all_data()

    header = ["地区", "日期", "确诊数", "境外输入数", "治愈数", "死亡数", "隔离数", "观察数"]
    body = list()
    for item in gzbd_all_data:
        line = []
        line.append(item['地区'])
        line.append(item['日期'])
        line.append(item.get('确诊数', None))
        line.append(item.get('境外输入数', None))
        line.append(item.get('治愈数', None))
        line.append(item.get('死亡数', None))
        line.append(item.get('隔离数', None))
        line.append(item.get('观察数', None))
        body.append(line)
    file_path = 'F:/uploadFile/gzbd_data.xlsx'
    excel_utils.creat_excel(header, body, file_path)


def get_gzbd_data():
    sql = "select * from gzbd_epidemic order by date desc "


if __name__ == '__main__':
    storage_mysql()
    # storage_excel()
    # pass


