import tornado.web
import base64
from util.util import genret

class UserHandler(tornado.web.RequestHandler):
    def get_current_user(self):
        token = self.request.headers.get('x-outrun-user-token')
        if token:
            return int(base64.b64decode(token))
        else:
            return None
