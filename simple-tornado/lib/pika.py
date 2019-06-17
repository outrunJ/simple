import pika

host = 'localhost'

def getconn():
    connection = pika.BlockingConnection(
        pika.ConnectionParameters(host)
    )
    return connection

def getchannel():
    conn = getconn()
    channel = conn.channel()
    return channel
