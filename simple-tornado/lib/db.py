from sqlalchemy import *
from sqlalchemy.orm import sessionmaker
from sqlalchemy.ext.declarative import declarative_base

Base = declarative_base()

engine = create_engine('mysql+pymysql://root:asdf@localhost/oa?charset=utf8')
DBSession = sessionmaker(bind=engine)

def getsession():
    return DBSession()