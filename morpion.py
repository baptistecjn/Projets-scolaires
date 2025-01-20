import doctest
import socket
import random
import time

def generer_grille_vide(nb_col,nb_lig) :

    grille=[]
    for i in range(nb_lig):
        grille+=[[0]*nb_col]
    return grille

def affiche_grille(grille) :
    ligne=0
    affiche=""
    for i in range(len(grille)):
        if ligne<10:
            affiche+=" "+str(ligne)+"| "
        else:
            affiche+=str(ligne)+"| "
    
        for j in range (len(grille[i])):
            
            if grille[i][j]==0:
                affiche+=". "
            elif grille[i][j]==1:
                affiche+="X "
            elif grille[i][j]==2:
                affiche+="O "
            
        ligne+=1
        affiche+="\n"    
    a = ("   ------------------------------\n    A B C D E F G H I J K L M N O")
    affiche+=a
    return affiche



def joue(grille,colonne,ligne,joueur) :

    if joueur == 1:
        grille[ligne][colonne]=1
        return grille    
    else:
        grille[ligne][colonne]=2
        return grille
    
def a_gagne_vert(grille,joueur) :

    compt=0
    for i in range(len(grille)):
        for j in range(len(grille[i])):
            if grille[j][i]==joueur:
                compt+=1
            else:
                compt=0
            if compt>=5:
                return True

    return False

def a_gagne_hor(grille,joueur) :

    compt=0
    for i in range(len(grille)):
        for j in range(len(grille[i])):
            if grille[i][j]==joueur:
                compt+=1
            else:
                compt=0
            if compt>=5:
                return True
    return False

def a_gagne_diag1(grille,joueur) :

    for i in range(len(grille)):
        for j in range(len(grille[i])):
            if i+3 <= len(grille) and j+3<=len(grille[i]) and grille[i][j]==joueur and grille[i+1][j+1]==joueur and grille[i+2][j+2]==joueur and grille[i+3][j+3]==joueur and grille[i+4][j+4]==joueur:
                return True
    
    return False

def a_gagne_diag2(grille,joueur) :

    for i in range(len(grille)):
        for j in range(len(grille[i]),-1,-1):
            if i-3>=0 and j+3<=len(grille[i]) and grille[i][j]==joueur and grille[i-1][j+1]==joueur and grille[i-2][j+2]==joueur and grille[i-3][j+3]==joueur and grille[i+4][j+4]==joueur:
                return True
    return False

def a_gagne(grille,joueur) :

    if a_gagne_vert(grille,joueur) or a_gagne_hor(grille,joueur) or a_gagne_diag1(grille,joueur) or a_gagne_diag2(grille,joueur):
        return True
    else:
        return False

def grille_pleine(grille) :

    for i in range(len(grille)):
        for j in range(len(grille[i])):
            if grille[i][j]==0:
                return    
    return True



serveur = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
serveur.setsockopt(socket.SOL_SOCKET,socket.SO_REUSEADDR,1)
port = 5005
serveur.bind(('127.0.0.1', port))
joueur={}
names={}
BALISE_NEW_NAME = "__new name__:"
BALISE_COUP = "__coup__:"
BALISE_QUIT = "__quit__"
grille_vide=generer_grille_vide(15,15)



def nouveau_joueur(addr,name):
    
    if (addr,5005) not in joueur and len(joueur)==0:
        joueur[(addr,5005)]="X"
        names["X"]=name
        msg="bienvenue "+str(name)+" !"
        serveur.sendto(msg.encode("utf-8"),addr)


    elif (addr,5005) not in joueur and len(joueur)==1:
        joueur[(addr,5005)]="O"
        names["O"]=name
        msg="bienvenue "+str(name)+" !"
        serveur.sendto(msg.encode("utf-8"),addr)

    else:
        msg="vous etes deja connectes ou la partie est deja au complet"
        serveur.sendto(msg.encode('utf-8'),addr)


def decode_coup1(message):
    if len(message)>=2:    
        x=message[0]
        y=message[1:]
    
    else:
        x=0
        y=0
    
    return y,x

def verif_coup(coup):
    nb_col=15
    nb_lig=15
    alpha=["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"]
    if len(coup)<2:
        return False
    if coup[1] not in alpha:
        return False
    if int(coup[0])>nb_lig or int(coup[0])<0:
        return False
    ligne=int(coup[0])
    colonne=int(alpha.index(coup[1]))
    return True,ligne,colonne

def decode_coup(message):
    if verif_coup(decode_coup1(message)):
        return verif_coup(decode_coup1(message))[1],verif_coup(decode_coup1(message))[2]


def premier_joueur():
    if len(names) == 0:
        return
    else:
        first = random.choice(list(names))
        
        if first=="X":
            first = 1
        elif first=="O":
            first = 2
    return first

def joueur_suivant(j):
    if j == 1:
        j = 2
    elif j == 2:
        j = 1
    return j


def bon_joueur(j):

    if j==1:
        return "X"
    elif j==2:
        return "O"
    

def peut_jouer(addr,a) :

    if (addr,5005) in joueur:
        b=bon_joueur(a)
        if str(joueur[(addr,5005)])!=str(b):
            return False
    return True

def boucle_principale(data,addr,nouv_grille,player) :
    ajoue=decode_coup(data)
        
    nouv_grille=joue(nouv_grille,ajoue[1],ajoue[0],player)
    msg=affiche_grille(nouv_grille)
    for i in joueur:
        serveur.sendto(msg.encode('utf-8'),i[0])
        
    if a_gagne(nouv_grille,player):
        if player ==1:
            sign="X"
        else:
            sign="O"
        for i in joueur:
            ess="le joueur "+str(names[sign])+" remporte la partie\nLe jeu est terminé ! (appuyez sur ctrl+C pour quitter)"
            serveur.sendto(ess.encode('utf-8'),i[0])
        return
        

def traite_data(addr,data):
    
    if data.startswith(BALISE_COUP):
        chaine=str(data).split(":")
        print("recv-data: ",str(data).encode('utf-8'))
        boucle_principale(chaine[1],addr,grille_vide,a)
    
    if data.startswith(BALISE_NEW_NAME):
        chaine=str(data).split(":")
        print("recv-data: ",str(data).encode('utf-8'))
        nouveau_joueur(addr,chaine[1])

                
                
if __name__ == "__main__" :
    while len(names)!=2:
        data,addr=serveur.recvfrom(65536)
        datadec=data.decode()
        if datadec.startswith(BALISE_NEW_NAME):
            traite_data(addr,datadec)
    a=premier_joueur()
    vide=affiche_grille(generer_grille_vide(15,15))
    for i in joueur:
         serveur.sendto(vide.encode('utf-8'),i[0])
    serveur.sendto("entrez votre prochain coup :".encode(),addr)
    while True:
        data,addr=serveur.recvfrom(65536)
        datadec=data.decode()
        
        if peut_jouer(addr,a):
            if verif_coup(decode_coup1(str(datadec).split(":")[1])):
                traite_data(addr,datadec)
                a=joueur_suivant(a)
            else:
                serveur.sendto("mauvais coup !".encode(),addr)
                
        else:
            serveur.sendto("ce n'est pas a votre tour !".encode(),addr)
        