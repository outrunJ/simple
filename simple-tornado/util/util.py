from sqlalchemy.ext.declarative import DeclarativeMeta
import json
import datetime
import time
import hashlib


class AlchemyEncoder(json.JSONEncoder):
    def default(self, o):
        if isinstance(o.__class__, DeclarativeMeta):
            fields = {}
            for field in [x for x in dir(o) if not x.startswith('_') and x != 'metadata']:
                data = o.__getattribute__(field)
                try:
                    json.dumps(data)
                    fields[field] = data
                except TypeError:
                    if isinstance(data, datetime.datetime):
                        fields[field] = data.isoformat()
                    elif isinstance(data, datetime.date):
                        fields[field] = data.isoformat()
                    elif isinstance(data, datetime.timedelta):
                        fields[field] = (datetime.datetime.min + data).time().isoformat()
                    else:
                        fields[field] = None
            return fields
        else:
            return json.JSONEncoder.default(self, o)


def alchemyjsondumps(o):
    return json.dumps(o, cls=AlchemyEncoder)


def tojson(o):
    return alchemyjsondumps(o)


def genret(status, *args, **kwargs):
    return (status, kwargs.get('data'), kwargs.get('error'), kwargs.get('msg'))


def switch(cases, case, *args, **kwargs):
    fothers = cases.get('others')

    f = cases.get(case)

    if isfunction(f):
        return f(*args, case=case, **kwargs)
    elif f != None:
        return f
    elif isfunction(fothers):
        return fothers(*args, case=case, **kwargs)
    else:
        return None


def genretswitchtuple(cases, tuple, *args, **kwargs):
    def fail(*args, **kwargs):
        case = kwargs.get('case')
        return genret((case if case != None else 1), *args, **kwargs)

    def suc(*args, **kwargs):
        msg = kwargs.get('msg')
        return genret(0, *args, msg=(msg if msg != None else 'suc'), **kwargs)

    casessuc = cases.get(0)
    if casessuc == None:
        cases[0] = suc

    cases['others'] = fail

    return switch(cases, tuple[0], *args, data=tuple[1], msg=tuple[3], **kwargs)


def isfunction(obj):
    return hasattr(obj, '__call__')


def timestamp2datetime(timestamp):
    timeary = time.localtime(timestamp)
    return time.strftime('%Y-%m-%d %H:%M:%S', timeary)


def datetime2timestamp(datetime):
    timeary = time.strptime(datetime, '%Y-%m-%d %H:%M:%S')
    return int(time.mktime(timeary))


def time2timestamp(timestr):
    timeary = time.strptime(timestr, '%H:%M:%S')
    return int(time.mktime(timeary))


def timestamp2time(timestamp):
    timeary = time.localtime(timestamp)
    return time.strftime('%H:%M:%S', timeary)


def md5(s):
    return hashlib.md5(s.encode()).hexdigest()


def paramdecode(param):
    if isinstance(param, list) and len(param) == 1:
        return param[0].decode()
    else:
        return None


def isfunction(obj):
    return hasattr(obj, '__call__')


def dowhen(cond, action, *args, **kwargs):
    thitheraction = kwargs['thitheraction']
    if cond:
        if isfunction(action):
            return action(*args, **kwargs)
        else:
            return action
    else:
        if isfunction(thitheraction):
            return thitheraction(*args, **kwargs)
        else:
            return thitheraction
