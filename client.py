import socket
import threading

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

BALISE_NEW_NAME = "__new name__:"
BALISE_COUP = "__coup__:"

nom=input("Enter your name :")
requette=BALISE_NEW_NAME.encode('utf-8')+nom.encode('utf-8')
sock.sendto(requette, ('127.0.0.1', 5005))

def receive():
    while True:
        data, addr=sock.recvfrom(1024)
        print(data.decode('utf-8'))

def send():
    while True:
        message=input()
        envoi=BALISE_COUP.encode('utf-8')+message.encode('utf-8')
        sock.sendto(envoi,('127.0.0.1', 5005))

# Creation des processus
send_thread = threading.Thread(target = send)
recv_thread = threading.Thread(target = receive)
# Lancement des processus
send_thread.start()
recv_thread.start()