#!/usr/bin/env python3
# -*- coding: utf-8 -*-
__author__ = "XianDaXia"

'''
python base
'''

import re, math


# === 输入输出 ===
# x = input()
# print(x)
x = 99
print(x)
y = 100
print('hello python')
print('hello python', 'xiangdaxia')
print('hello %s' % x)
print('hello %s' % (x,))
print("hello %s %s" % (x, y))

# === 数据类型 ===
a = 11
print('a = %s 数据类型: %s' % (a, type(a)))
a = 11.0
print('a = %s 数据类型: %s' % (a, type(a)))
a = 'xiangdaxia'
print('a = %s 数据类型: %s' % (a, type(a)))
a = ['11.0', 111, 222.2]
print('a = %s 数据类型: %s' % (a, type(a)))
a = ('11.0', 111, 222.2)
print('a = %s 数据类型: %s' % (a, type(a)))
a = {'11.0', 111, 222.2}
print('a = %s 数据类型: %s' % (a, type(a)))
a = None
print('a = %s 数据类型: %s' % (a, type(a)))
a = True or False
print('a = %s 数据类型: %s' % (a, type(a)))
a = 1 > 2
print('a = %s 数据类型: %s' % (a, type(a)))

# ===运算符===
print((2 * 4 / 2) * 2 - 2)
# 地板除，取整
print(11 // 2)
# 次方
print(5 ** 4)

# ===字符串===
print(u'向')
print(r'向')
print(b'jjj')

# ===ASCII===
print('98-->%s;a-->%s' % (chr(98), ord('a')))

# ===encode && decode===
print('cass'.encode('ascii'))
print('向锐'.encode('utf-8'))
print(b'cass'.decode('ascii'))
print(b'\xe5\x90\x91\xe9\x94\x90'.decode('utf-8'))

# ===function===
print(len('qqqqqqqq'))
print(len('向锐'))
print(len('qqqq'.encode('utf-8')))
print(len('向锐'.encode('utf-8')))  # 对于byte计算字节数
print('ddddddddddd'.replace('d', '==='))
print('ssssssssss'.find('s'))
print('ssssssssssd'.rfind('d'))
print('dfadsas'.isspace())

# ==== 字符串格式化 ====
print("%d----%2d----%02d" % (2, 3, 4))  # 2d（不足两位左边补空格）、02d（不足两位，左边补0）
print("%f----%.2f" % (222.2, 333.345))  # .2f（保留两位小数，四舍五入）
print("%x" % 333)  # 格式化为十六进制
print("%s%%%s" % ("3", "2"))
print('%d %% %d' % (3, 4))
print(list("%s" % x for x in range(2, 10)))  # 将2-10生成器，转化为字符串list
print("Hi {0}, 成绩提高了{1:.1f}%".format("小明", 1.234))
print("Hi {0}, 成绩提高了{1}%".format("小明", "%.1f" % 1.234))
print("-".join(["a", "b", "c"]))

# ===正则表达式===
email_re = "^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$"
# 如果匹配成功，将返回一个Math对象，失败则返回None
if re.match(email_re, "hujiangux@163.com"):
    print("ok")
else:
    print("error")

# ===切分字符串===
print("a b c".split(" "))
print(re.split(r"\s+", "a b  c"))
print(re.split(r"[\s\,\;]+", "a,b;; c   d"))


# ===分组===
# 分组提取电话号码
match = re.match(r"^(\d{3})-(\d{3,8})$", "010-12345")
print(match.groups())  # ('010', '12345')
print(match.group(0))  # 010-12345
print(match.group(1))  # 010
print(match.group(2))  # 12345

# 分组提起时间
match = re.match(r"^(0[0-9]|1[0-9]|2[0-3]|[0-9])\:"
                r"(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|[0-9])\:"
                r"(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|[0-9])$", "19:05:30")
print(match.groups())
# 分组提取数字
re_str_1 = re.compile(r'全省累计报告新型冠状病毒肺炎确诊病例(\d+)例\(其中境外输入(\d+)例\），' +
                      r'累计治愈出院(\d+)例，死亡(\d+)例，目前在院隔离治疗(\d+)例，(\d+)人尚在接受' +
                      r'医学观察')
new_text = '全省累计报告新型冠状病毒肺炎确诊病例653例(其中境外输入112例），累计治愈出院626例，死亡3例，目前在院隔离治疗24例，964人尚在接受医学观察'
print(re.search(re_str_1, new_text).group(1))
print(re.search(re_str_1, new_text).group(2))
print(re.search(re_str_1, new_text).group(3))
print(re.search(re_str_1, new_text).group(4))
print(re.search(re_str_1, new_text).group(5))
print(re.search(re_str_1, new_text).group(6))


# 贪婪匹配
print(re.match(r"^(\d+)(0*)$", "102300").groups())  # ('102300', '')
print(re.match(r"^(\d+?)(0*)$", "102300").groups())  # ('1023', '00')


# ==== list ====
l = list(range(1, 10))
print(l)
l = ["csa", 123, True, "cdsacda"]
print(l)
# 从前取值，index从0开始往后；从后取值，index从-1开始往前
print(l[0] + "----" + l[-1])
# 集合尾部添加元素
l.append("xiang")
# 将后面集合的元素添加到前面集合，注意和append的区别，append是将append的整体作为一个元素纳入前面集合；
l += list(range(3))
# 集合指定位置插入元素
l.insert(1, "xiang")
# 删除集合最后一个元素
l.pop()
# 删除集合中指定位置元素
l.pop(0)
# 直接对集合元素进行赋值
l[0] = "xiang111"
print("list: %s, length: %s" % (l, len(l)))

# ==== tuple ====
t = tuple(range(10))
t = ("aads", 123, True, None, 12.3)
# 定义只有一个元素的元祖，元素后追加“,”，以免误解成数学计算意义上的括号
t = ("cdsa",)
# 集合作为元祖的元素，我们可以修改集合的元素
t = ("vsv", ["aaa", "sss"])
t[1][1] = "bbbb"
print("tuple: %s, length: %s"%(t, len(t)))


# ==== dict ====
d = {"name": "hyman","age":33,"money": 22.3}
print("dict: %s, length: %s"%(d, len(d)))
# get 取值，没有返回 None，也可给定默认值
print(d.get("name"), d.get("name1"), d.get("name1", "hujiang"))


# 赋值取值
d["aaaa"] = "aaaa"
d["name"] = "hymanhu1"
print(d["name"], d["age"])


# 删除
d.pop("money")
print("dict: %s, length: %s" % (d, len(d)))


# ==== set ====
s = set(["aaa", 123, 123, True])
s.add("fdsaa")
# 移除下标从1开始
s.remove(1)
print("set: %s, length: %s" % (s, len(s)))


# 交集、合集
s2 = set([123, "fdcasc"])
print(s & s2)
print(s | s2)


# ==== 条件判断 ====
a = 20
if a < 10:
    print("aaaa")
elif 10 <= a < 20:
    print("bbb")
else:
    print("ccc")
a = " "
if a and a.strip():
    print(a)
else:
    print("Null string")
# 三目运算符
a, b, c = 1, 2, 3
print(a if (b > c) else c)

# ===循环===
# a = list(range(10))
# sum = 0
# for x in a:
#     print(x)
#     sum += x
# print("sum: %s" % sum)
# sum = 0
# i = 0
# while i < 10:
#     sum += i
#     i += 1
# print("sum: %s" % sum)
# i = 0
# while i < 10:
#     if i > 5:
#         break
#     i += 1
#     print(i)


# ==== 函数 ====
def test(a):
    a += 3
    return a

print(test(8));
f = test(8)
print(f)

def test_2(x, y="hujiang"):
    print(x, y)

def test_3(*num):
    count = 0
    for i in num:
        count += i
    return count

def test_4(name, **kv):
    if "city" in kv:
        print("name:%s, city:%s"%(name,kv.get("city")))
    else:
        print("name:%s, city:%s" % (name, "sichuan"))

def test_5(name, *, city):
    if not isinstance(name, (str,)):
        raise TypeError("Type error")
    print("name:%s, city:%s"%(name, city))


if __name__ == "__main__":
    test_2("hello", "hyman")
    test_2("hello")
    print(test_3())
    print(test_3(*list(range(1, 9))))
    print(test_3(1, 2, 3, 4, 5))
    test_4("hujiang", **{"age": 33})
    test_4("hujiang", **{"age": 33, "city": "cd"})
    test_5("hujiang", city="cd")
    # test_5(111, city="cd")

# ==== 内置函数 ====
print(int("22")) # 数据类型转换函数，注意，如果定义变量名和函数名一样，则不会调用该函数，会报错
print(float("22.2"))
print(str(22))
print(abs(-111)) # abs函数，求绝对值
print(max(12, 34, 123.4)) # max函数，求最大值
print(min(-21, -11, 0, 22.3)) # min函数，求最小值
print(" aa bb  cc  ".strip()) # 字符串去前后空格
print("['6K-8K']".strip('[\'\']')) # 移除字符串头尾指定的字符
print(hex(12)) # hex函数，将十进制数转十六进制
print(math.sqrt(3)) # 求平方根
print(sum(range(1, 100))) # 求和
print('cdjshdGGGG'.capitalize())


