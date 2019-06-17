import module.user.user_ctl as user_ctl


rootdir = r'/user'

def getrouters():
    return [
        (rootdir +r'/docapi', user_ctl.Docapi),
        (rootdir +r'/user', user_ctl.User),
        (rootdir +r'/register', user_ctl.Register),
        (rootdir +r'/login', user_ctl.Login),
        (rootdir +r'/talkto', user_ctl.TalkTo),
    ]
