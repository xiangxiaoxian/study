#!/usr/bin/env python3
# -*- coding: utf-8 -*-
__author__ = "XianDaXia"

import pymysql


def get_connect_cursor():
    conn = pymysql.connect(host='localhost', user='root', passwd='123456', db='j200502', charset='utf8')
    return conn, conn.cursor()


def execute_insert_update_delete(cursor, sql):
    result = cursor.execute(sql)
    return result


def execute_query(cursor, sql):
    cursor.execute(sql)
    return cursor.fetchall()


def commit_(conn):
    conn.commit()


def close_connect_cursor(cur, conn):
    cur.close()
    conn.close()


def execute(sql):
    conn, cursor = get_connect_cursor()
    result = None
    if sql.startswith("select"):
        result = execute_query(cursor,sql)
        close_connect_cursor(conn,cursor)
    else:
        result = execute_insert_update_delete(cursor,sql)
        commit_(conn)
        close_connect_cursor(conn,cursor)
    return result


if __name__ == '__main__':
    # print(execute('select * from user'))
    pass
