Mimport socket
import random

def generer_grille_vide(nb_col,nb_lig) :

    grille=[]
    for i in range(nb_lig):
        grille+=[[0]*nb_col]
    return grille

def affiche_grille(grille) :
    ligne=0
    for i in range(len(grille)):
        affiche=" "
    
        for j in range (len(grille[i])):
            
            if grille[i][j]==0:
                affiche+=". "
            elif grille[i][j]==1:
                affiche+="X "
            elif grille[i][j]==2:
                affiche+="O "
        if ligne<10:
            print("",ligne,"|",affiche)
        else:
            print(ligne,"|",affiche)
        ligne+=1
    a = ("    -------------------------------\n      A B C D E F G H I J K L M N O")
    print(a)

def peut_jouer(grille,colonne) :
 
    compt=0
    for i in range(len(grille)):
        if grille[i][colonne]!=0:
            compt=0
        else:
            compt+=1
    if compt==0:
        return False
    return True

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

def nouveau_joueur(addr,name):
    
    if (addr,5005) not in joueur and len(joueur)==0:
        joueur[(addr,5005)]="X"
        names[name]="X"
        msg="bienvenue "+str(name)+" !"
        serveur.sendto(msg.encode("utf-8"),addr)

    elif (addr,5005) not in joueur and len(joueur)==1:
        joueur[(addr,5005)]="O"
        names[name]="O"
        msg="bienvenue "+str(name)+" !"
        serveur.sendto(msg.encode("utf-8"),addr)

    else:
        msg="vous etes deja connectes ou la partie est deja au complet"
        serveur.sendto(msg.encode('utf-8'),addr)
    print(joueur)
    print(names)

def decode_coup(addr,message):
    colonne=message[0]
    ligne=message[1:]
    return ligne,colonne

def traite_data(addr,data):
    if data.startswith(BALISE_COUP):
        chaine=str(data).split(":")
        print("recv-data: ",str(data).encode('utf-8'))
        decode_coup(addr,chaine[1])
    
    if data.startswith(BALISE_NEW_NAME):
        chaine=str(data).split(":")
        print("recv-data: ",str(data).encode('utf-8'))
        nouveau_joueur(addr,chaine[1])
    

def boucle_principale() :
    alpha=["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"]
    nb_col=15
    nb_lig=15
    grille=generer_grille_vide(nb_col, nb_lig)
    joueur=1
    while not grille_pleine(grille):
        affiche_grille(grille)
        rep= input("Entrez votre prochain coup : ")
        while len(rep)<2:
            rep=input("coup invalide !\nnouveau coup :")
        
        colonne=rep[0]
        ligne=rep[1:]

        while colonne not in alpha:
            rep=input("coup invalide !\nnouveau coup :")
            colonne=rep[0]
            ligne=rep[1:]

        colonne=rep[0]
        ligne=rep[1:]
        print(ligne)

        ligne=int(ligne)
        colonne=int(alpha.index(colonne))
        
        while colonne>nb_col or colonne<0 or ligne>nb_lig or ligne<0:
            print("coup incorrect")
            affiche_grille(grille)

            rep= input("coup invalide !\nnouveau coup :")
            colonne=rep[0]
            ligne=rep[1:]
            colonne=int(alpha.index(colonne))
            ligne=int(ligne)
        
        joue(grille,colonne,ligne,joueur)
        
        if a_gagne(grille,joueur):
            print("le joueur",joueur,"remporte la partie\nLe jeu est terminé ! (appuyez sur ctrl+C pour quitter)")
            return
        if joueur == 1:
            joueur=2
        elif joueur==2:
            joueur=1
    print("match nul")


while True:
    data,addr=serveur.recvfrom(65536)
    traite_data(addr,data.decode('utf-8'))


