from sqlalchemy import Column, String, Integer, ForeignKey, Date, Time, TIMESTAMP
from lib.db import engine, Base
from sqlalchemy.orm import relationship
class User(Base):
    __tablename__ = 'user'

    id = Column(Integer, primary_key=True)
    name = Column(String(20))
    pwd = Column(String(32))
    logintime = Column(TIMESTAMP)

    people = relationship('People', uselist=False, back_populates='user')


class Room(Base):
    __tablename__ = 'room'

    id = Column(Integer, primary_key=True)
    name = Column(String(20))

    meeting = relationship('Meeting', uselist=False, back_populates='room')

class People(Base):
    __tablename__ = 'people'

    id = Column(Integer, primary_key=True)
    name = Column(String(20))

    user_id = Column(Integer, ForeignKey('user.id'))
    user = relationship('User', back_populates='people')
    meetings = relationship('Meeting', back_populates='people_creator')

class Meeting(Base):
    __tablename__ = 'meeting'

    id = Column(Integer, primary_key=True)
    membersize = Column(Integer, default=20)
    day = Column(Date)
    begin_time = Column(Time)
    end_time = Column(Time)

    room_id = Column(Integer, ForeignKey('room.id'))
    room = relationship('Room', uselist=False, back_populates='meeting')
    people_creator_id = Column(Integer, ForeignKey('people.id'))
    people_creator = relationship('People', uselist=False, back_populates='meetings')

class MeetingMember(Base):
    __tablename__ = 'meeting_member'

    id = Column(Integer, primary_key=True)
    meeting_id = Column(Integer, ForeignKey('meeting.id'))
    people_id = Column(Integer, ForeignKey('people.id'))

Base.metadata.bind = engine
Base.metadata.create_all()
