import tornado.ioloop
import tornado.web
import module.meeting.meeting_router as meeting_router
import module.user.user_router as user_router

PORT = 9501

class MainHandler(tornado.web.RequestHandler):
    def get(self, id):
        print("test: ", id)

    def post(self):
        self.set_header("Content-Type", "text/plain")
        self.write(self.get_argument("msg"))


routers = [
    (r"/demo/([0-9]+)", MainHandler),
]
routers.extend(meeting_router.getrouters())
routers.extend(user_router.getrouters())

application = tornado.web.Application(routers)

if __name__ == "__main__":
    application.listen(PORT)
    tornado.ioloop.IOLoop.instance().start()
    print('server started at: %d', PORT)
