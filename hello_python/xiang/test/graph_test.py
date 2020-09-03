#!/usr/bin/env python3
# -*- coding: utf-8 -*-
__author__ = "XianDaXia"

import plotly


def draw_line_graph():
    # 准备图轨数据
    trace_l = plotly.graph_objs.Scatter(
        x=[1, 2, 3, 4],
        y=[11, 22, 33, 44],
        name="散点图",
        mode="markers"
    )
    trace_m = plotly.graph_objs.Scatter(
        x=[1, 2, 3, 4],
        y=[111, 222, 333, 444],
        name="折线图"
    )
    line_data = [trace_l, trace_m]
    # 设置画布布局
    layout = plotly.graph_objs.Layout(title='折线图测试', xaxis={'title': 'x'}, yaxis={'title': 'y'})
    # 融合图轨数据和布局
    figure = plotly.graph_objs.Figure(data=line_data, layout=layout)
    # 输出
    plotly.offline.plot(figure, filename='F:/uploadFile/one.html', image='png')


def draw_bar_graph():
    # 准备图轨数据
    trace_l = plotly.graph_objs.Bar(
        x=[1, 2, 3, 4],
        y=[11, 22, 33, 44],
        name='一'
    )
    trace_m = plotly.graph_objs.Bar(
        x=[1, 2, 3, 4],
        y=[13, 25, 36, 46],
        name='二'
    )
    trace_n = plotly.graph_objs.Bar(
        x=[1, 2, 3, 4],
        y=[15, 27, 39, 41],
        name='三'
    )
    bar_data = [trace_l, trace_m, trace_n]
    # 设置画布布局
    layout = plotly.graph_objs.Layout(title='柱状图测试', xaxis={'title': '季度'}, yaxis={'title': '产值'})
    # 融合图轨数据和布局
    figure = plotly.graph_objs.Figure(data=bar_data, layout=layout)
    # 输出
    plotly.offline.plot(figure, filename='F:/uploadFile/two.html', image='png')


def draw_pie_graph():
    # 准备图轨数据
    trace_l = plotly.graph_objs.Pie(
        labels=["产品一", "产品二", "产品三", "产品四", "产品五"],
        values=[13.5, 24.7, 33.7, 76.7, 65.7]
    )
    pie_data = [trace_l]
    # 设置画布布局
    layout = plotly.graph_objs.Layout(title='饼图测试')
    # 融合图轨数据和布局
    figure = plotly.graph_objs.Figure(data=pie_data, layout=layout)
    # 输出
    plotly.offline.plot(figure, filename='F:/uploadFile/three.html', image='png')


if __name__ == '__main__':
    # draw_line_graph()
    # draw_bar_graph()
    draw_pie_graph()


