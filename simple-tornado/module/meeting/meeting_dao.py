from lib.db import getsession
from model.model import *
from sqlalchemy import func, or_, not_, and_
from util.util import time2timestamp, paramdecode, genret


def addroom(room):
    session = getsession()
    session.add(room)
    session.commit()


def getroom():
    session = getsession()
    return session.query(Room).all()


def checkmeeting(meeting):
    session = getsession()
    if ( time2timestamp(paramdecode(meeting.begin_time)) > time2timestamp(paramdecode(meeting.end_time))):
        return genret(1, msg='begin_time > end_time')

    meetings = session.query(Meeting).filter(
        Meeting.room_id == meeting.room_id,
    ).filter(
        or_(
            and_(Meeting.begin_time <= meeting.begin_time, Meeting.end_time > meeting.begin_time),
            and_(Meeting.begin_time < meeting.end_time, Meeting.end_time > meeting.end_time),
            and_(Meeting.begin_time >= meeting.begin_time, Meeting.end_time < meeting.end_time),
        )
    ).all()

    if len(meetings) > 0:
        return genret(2, msg='meeting\' s checking not pass')
    else:
        return genret(0)


def addmeeting(meeting):
    session = getsession()
    session.add(meeting)
    session.commit()

def  getmeetings():
    session = getsession()
    data = session.query(Meeting).all()
    return genret(0, data=data)
