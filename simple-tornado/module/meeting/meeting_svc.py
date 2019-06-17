import module.meeting.meeting_dao as meeting_dao
from model.model import *
from util.util import genret, paramdecode, switch, genretswitchtuple


def addroom(params):
    roomname = params.get('name')
    if roomname != None:
        new_room = Room(name=roomname)
        meeting_dao.addroom(new_room)
        return genret(0, msg='suc')
    else:
        return genret(1, msg='fail')


def getroom(params):
    roomid = params.get('id')
    data = meeting_dao.getroom()
    return genret(0, data=data)


def addmeeting(params, **kwargs):
    room_id = params.get('room_id'),
    people_creator_id = kwargs['userid'],
    meeting = Meeting(
        day=params.get('day'),
        begin_time=params.get('begin_time'),
        end_time=params.get('end_time'),
        membersize=params.get('membersize'),
    )
    meeting.room_id = room_id
    meeting.people_creator_id = people_creator_id

    tuple = meeting_dao.checkmeeting(meeting)

    def suc(*args, **kwargs):
        meeting_dao.addmeeting(meeting)
        return genret(0, msg='suc')

    return genretswitchtuple({
        0: suc
    }, tuple)


def getmeetings(params):
    return meeting_dao.getmeetings()
