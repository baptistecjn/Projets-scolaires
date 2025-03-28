"""https://github.com/techwithtim/Network-Game-Tutorial/blob/master/server.py"""
import socket
from _thread import *

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

server = "192.168.1.24"
port = 64310
server_ip = socket.gethostbyname(server)

try:
    s.bind((server, port))

except socket.error as e:
    print(str(e))

s.listen(2)
print("Waiting for a connection")

currentId = "0"
player_data = [
    "0:-123456,0,False,False,0.0,4,0,'','',-123456,-123456,0,-1.0,-1.0",
    "1:-123456,100,False,False,0.0,4,0,'','',-123456,-123456,0,-1.0,-1.0",
]


def threaded_client(conn):
    global currentId, player_data
    conn.send(str.encode(currentId))
    currentId = "1"
    reply = ""
    while True:
        try:
            data = conn.recv(2048)
            reply = data.decode("utf-8")
            if not data:
                conn.send(str.encode("Goodbye"))
                break
            else:
                # print("Received: " + reply)
                arr = reply.split(":")
                id = int(arr[0])
                player_data[id] = reply

                if id == 0:
                    nid = 1
                if id == 1:
                    nid = 0

                reply = player_data[nid][:]
                # print("Sending: " + reply)

            conn.sendall(str.encode(reply))
        except:
            break

    print("Connection Closed")
    conn.close()


while True:
    conn, addr = s.accept()
    print("Connected to: ", addr)

    start_new_thread(threaded_client, (conn,))
