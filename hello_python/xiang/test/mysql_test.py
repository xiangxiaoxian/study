#!/usr/bin/env python3
# -*- coding: utf-8 -*-
__author__ = "XianDaXia"

'''
mysql test
'''

import pymysql
import mysql_utils


def insert_user():
    conn, cur = mysql_utils.get_connect_cursor()
    sql = "insert into user (user_name,password) values ('xiangrui', 'xxx')"
    mysql_utils.execute_insert_update_delete(cur, sql)
    mysql_utils.commit_(conn)
    mysql_utils.close_connect_cursor(cur, conn)


def query_user():
    conn, cur = mysql_utils.get_connect_cursor()
    sql = "select * from user"
    result = mysql_utils.execute_query(cur, sql)
    print(result)
    mysql_utils.close_connect_cursor(cur, conn)


if __name__ == "__main__":
    query_user()


