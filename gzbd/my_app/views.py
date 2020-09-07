from django.core import serializers
from django.http import HttpResponse, JsonResponse
from my_app.models import *
import random,datetime
from django.shortcuts import render
# Create your views here.


def hello_world(request):
    return HttpResponse("Hello World")


def insert_user(request):
    user = User(user_name="xiangdaxia" + str(random.randint(1, 10)), password="111111", create_date=datetime.datetime.now())
    user.save()

    user = User.objects.get(id=2)
    user_json = serializers.serialize("json", [user])
    return JsonResponse(user_json, safe=False)


def hello_one(request):
    context = {}
    context['name'] = "xiangdaxia"
    context['nameList'] = ['xiangdaxia', 'xiangxiaoxian']
    context["number"] = 1024
    context["birthday"] = datetime.datetime.now()
    context["name_list"] = ["HymanHu", "Hujiang", "Hawkist"];
    context["person"] = {"name": "Hujiang", "age": 40}
    context["person1"] = {"name": "Hujiang", "age": 40}
    context["url"] = "http://www.bing.com"
    context["a"] = "<a href='http://www.bing.com'>点击2</a>"
    context["isman"] = True
    context["age"] = 4
    return render(request, "test/one.html", context)


def test_index(request):
    return render(request, "index.html")