import tornado.ioloop
import tornado.web
import module.user.user_svc as user_svc
from util.bizhelper import rethtml, rettupledata, rettupledataaction
from middleware.BaseHandler import UserHandler

class Docapi(tornado.web.RequestHandler):
    def get(self):
        data = user_svc.docapiget(self.request.arguments)
        rethtml(self, data)

class User(tornado.web.RequestHandler):
    def post(self):
        pass

class Register(tornado.web.RequestHandler):
    def post(self):
        rettupledataaction(self, user_svc.register, self.request.arguments)

class Login(tornado.web.RequestHandler):
    def post(self):
        rettupledataaction(self, user_svc.login, self.request.arguments)

class TalkTo(UserHandler):
    def post(self):
        rettupledataaction(self, user_svc.talkto, self.request.arguments)
