import json
from util.util import *


def retjson(that, json):
    that.set_header('Content-type', 'application/json')
    that.write(json)


def rethtml(that, html):
    that.set_header('Content-type', 'text/html')
    that.write(html)


def retdata(that, **kwargs):
    code = kwargs.get('code')
    msg = kwargs.get('msg')
    data = kwargs.get('data')

    code = switch({
        0: '0000',
        1: '0001',
        2: '0002',
        3: '0003',
        4: '0004',
        5: '0005',
        6: '0006',
        'others': '1000',
    }, code)

    jsondata = {'code': code}

    if msg != None:
        jsondata['msg'] = msg

    if data != None:
        jsondata['data'] = data

    return retjson(that, tojson(jsondata))


def rettupledata(that, tuple):
    def retsuc(*args, **kwargs):
        tuple = args[0]
        retdata(that, code=kwargs.get('case'), data=tuple[1], msg=tuple[3])

    def retfail(*args, **kwargs):
        tuple = args[0]
        retdata(that, code=kwargs.get('case'), data=tuple[1], msg=tuple[3])

    switch({
        0: retsuc,
        'others': retfail,
    }, tuple[0], tuple, )

def  rettupledataaction(self, action, *args, **kwargs):
    tuple = action(*args, **kwargs)
    return rettupledata(self, tuple)


def dowhenhasuser(self, action, *args, **kwargs):
    userid = self.current_user

    def thitheraction(*args, **kwargs):
        return genret(1, msg='not logged in')

    return dowhen(userid != None, action, thitheraction=thitheraction, userid=userid, *args, **kwargs)


def requiredparams(self, ary):
    params = self.request.arguments
    for val in ary:
        if params.get(val) == None:
            return genret(1, msg='param ' + val + ' is not detected')

    return genret(0)


def dowhenhasparams(self, ary, action, *args, **kwargs):
    tuple = requiredparams(self, ary)

    def doaction(*args, **kwargs):
        return action(*args, **kwargs)

    return genretswitchtuple({
        0: doaction
    }, tuple, *args, **kwargs)


def dowhenhasuserandparams(self, ary, action, *args, **kwargs):
    def useraction(*args, **kwargs):
        return dowhenhasparams(self, ary, action, *args, **kwargs)

    return dowhenhasuser(self, useraction, *args, **kwargs)

