import tornado.ioloop
import tornado.web
import module.meeting.meeting_svc as meeting_svc
from util.bizhelper import rettupledata, rettupledataaction, dowhenhasuser, dowhenhasparams, dowhenhasuserandparams
from util.util import genret
from middleware.BaseHandler import UserHandler


class Room(UserHandler):
    def post(self):
        tuple = meeting_svc.addroom(self.request.arguments)
        rettupledata(self, tuple)

    def get(self):
        tuple = meeting_svc.getroom(self.request.arguments)
        rettupledata(self, tuple)

    def put(self):
        meeting_svc.updateroom(self.request.arguments)

    def delete(self):
        meeting_svc.deleteroom(self.request.arguments)


class Meeting(UserHandler):
    def post(self):
        def bizaction(*args, **kwargs):
            return meeting_svc.addmeeting(*args, **kwargs)

        tuple = dowhenhasuserandparams(self, ['begin_time', 'end_time', 'day', 'room_id'], bizaction,
                                       self.request.arguments)
        rettupledata(self, tuple)

    def get(self):
        rettupledataaction(self, meeting_svc.getmeetings, self.request.arguments)
