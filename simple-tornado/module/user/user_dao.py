from lib.db import getsession
from sqlalchemy import func, or_, not_, and_
from model.model import *


def getuser(**kwargs):
    name = kwargs.get('name')
    pwd = kwargs.get('pwd')
    all = kwargs.get('all')
    session = getsession()
    query = session.query(User)
    conditioned = False

    if (all):
        return query.all()

    if (name != None and pwd != None):
        query = query.filter(and_(User.name == name, User.pwd == pwd))
        conditioned = True

    if (conditioned):
        return query.all()
    else:
        return []


def adduser(**kwargs):
    name = kwargs['name']
    pwd = kwargs['pwd']

    user = User(name=name, pwd=pwd)
    user.people = People(name=name)

    session = getsession()
    session.add(user)
    session.commit()
    session.close()


def upduser(user):
    session = getsession()
    session.query(User).filter(User.id==user.id).update({'logintime': user.logintime}, synchronize_session=False)
    session.commit()
    session.close()
