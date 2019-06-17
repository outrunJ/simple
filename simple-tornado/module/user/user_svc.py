import markdown2
import os
from config.config import CLIENT_DIR
from util.util import genret, timestamp2datetime
import module.user.user_dao as user_dao
import time
from math import floor
import base64
from lib.pika import getconn


def docapiget(params):
    apifile = open(os.path.join(CLIENT_DIR, 'docapi.wiki'), 'rU')
    return markdown2.markdown(apifile.read())


def register(params):
    username = params.get('username')
    pwd = params.get('pwd')
    pwd2 = params.get('pwd2')

    if (pwd != pwd2):
        return genret(1, msg='pwd fail')

    users = user_dao.getuser(name=username, all=True)

    if (len(users) != 0):
        return genret(1, msg='user defined')

    user_dao.adduser(name=username, pwd=pwd)

    return genret(0, msg='suc')


def login(params):
    username = params.get('username')
    pwd = params.get('pwd')

    users = user_dao.getuser(name=username, pwd=pwd)

    if (len(users) == 0):
        return genret(1, msg='no user')

    user = users[0]
    user.logintime = timestamp2datetime(time.time())

    user_dao.upduser(user)
    user = {
        'id': user.id,
        'name': user.name,
        'logintime': user.logintime,
        'token': base64.b64encode(str(user.id).encode()).decode()
    }
    return genret(0, data=user, msg='suc')


def talkto(params):
    conn = getconn()
    channel = conn.channel()
    channel.queue_declare(queue='talkto')
    channel.basic_publish(
        exchange='',
        routing_key='talkto',
        body='hello talking',
    )
    conn.close()

    return genret(0)