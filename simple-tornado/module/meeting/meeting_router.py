import module.meeting.meeting_ctl as meeting_ctl


rootdir = r'/meeting'

def getrouters():
    return [
        (rootdir +r'/room', meeting_ctl.Room),
        (rootdir +r'/meeting', meeting_ctl.Meeting)
    ]
