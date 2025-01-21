#importation des modules
from tkinter import*
from tkinter.messagebox import*
from random import*
import pygame

def rejouer():
    do_replay()
pygame.init()
pygame.mixer.init()
musique = pygame.mixer.Sound("motus-generique-france-2.mp3")
musique.play(0, 0, 3000)
fond = pygame.mixer.Sound("musique_fond.mp3")
def stop():#coupe la musique motus et en lance une autre plus calme
    musique.stop()
    fond.play(1,0,5000)
    fond.set_volume(5)

def stop_all():
    pygame.mixer.stop()


    

def regles(): #Fenetre d'affichage des règles
    showinfo(title = "REGLES", message = "Voici les règles :\n\nentrez un mot de 6 lettres dans la barre de saisie commençant par la lettre indiquée sur la gauche\n\nsiginfication des couleurs des lettres:\n-verte: lettre correcte\n-orange: lettre mal placée\n-rouge: lettre incorrecte.")


points = 6
xL = 50
yL = 50

#récupère les mots de 6 lettres du dico etgénere un mot aléatoire parmis les mots récupérés. 
def choix_mot(): 

    with open('dico_propre.txt', "r") as dico :
        contenu = [i for i in dico if len(i)==7]
    mot_random=contenu[randint(0,12672)] 
    mot = mot_random.rstrip('\n')
    
    return mot
motT = choix_mot()
 
#création de la page principale

canvas_width = 600
canvas_height = 500
fen = Tk()
fen.title("MOTUS")

label_pp = Label(fen, text = 'Bienvenue dans notre jeu motus !',font=("Courier", 20) )
label_pp.grid(row = 0)

Label(fen, text = "Veuillez entrer un mot :\n (écrire le mot en majustcule)\n⚠\ncliquer sur quitter pour fermer la page !",font=("Courier", 15)).grid(row=1,column=0)


#Zone d'entrée
entre = Entry(fen)
entre.grid(row=2,column=0)


def mot_rentré():
        
    global yL,xL,points,motT
    essaie = str(entre.get()) #recupère le contenu de la zone d'entrée dans la variable essaie
    liste =list(essaie)       #transforme la variable essaie en une liste
    liste_à_trouver=list(motT)
    xL=50
    if len(essaie)!=6:
        showerror(title = "erreur", message = "Attention, un mot de 6 lettres est attendu, ni plus, ni moins !")#declare une erreur si le mot proposé par l'utilisateur est faux 
        
    
    elif motT[0]!=essaie[0]:
        showerror(title = "erreur", message = "la premère lettre est " + motT[0] + " pas " + essaie[0] + " !")#declare une erreur si la première lettre proposée n'est pas la bonne(car déjà connue)
        
    
    else:
        
        points = points-1
                    
        for i in range(6) :
        
            if motT[i]==essaie[i]:
                can.create_text(xL,yL,text=liste[i],font=("Courier", 25),fill='green') #si la lettre est corecte elle s'affiche en vert
                
            elif liste[i] in liste_à_trouver:
                can.create_text(xL,yL,text=liste[i],font=("Courier", 25),fill='orange')#en orange si seulement mal placée

                
            else:
                can.create_text(xL,yL,text=essaie[i],font=("Courier", 25),fill='red')#en rouge si incorrecte

            
            xL=xL+100#colonne suivante à chaque lettre 
        yL=yL+100#ligne suivante quand validé
        
    pts = str(points)#transforme les points qui sont des entier en caractère
    if motT==essaie:
        win = 'vous avez gagné, le mot était bien:'+ motT +',\nvous remporetez '+ pts +' points!'
        label_win = Label(fen, text = win,font=("Courier", 20) )
        label_win.grid()  #lorsque le mot est trouvé

    elif points==1:
        loose='vous avez perdu, le mot était :'+ motT +', Vous remporetez '+ pts +' points !'
        label_loose = Label(fen, text = loose,font=("Courier", 20) )
        label_loose.grid()
            

#création des boutons
valider = Button(fen, text="valider", command=mot_rentré, fg='blue')#bouton valider afin d'activer la fonction mot_rentré
valider.grid(row=3,column=0)
quitter = Button(fen, text="Quitter", command=fen.quit, fg='red')#bouton quitter
quitter.grid()
aide = Button(fen, text=' regles ', command=regles, fg='green')#bouton qui affiche les règles
aide.grid()
pause= Button(fen, text = 'autre musique', command=stop)
pause.grid(row=3, column=1)
stop = Button(fen, text = 'arret musique', command=stop_all)
stop.grid(row=4, column=1)

rejouer= Button(fen, text='rejouer', command=rejouer)
rejouer.grid(row=1, column=5)

#création du canevas qui contiendra les cases

can = Canvas(fen, width=canvas_width,height=canvas_height, bg="#5decff")

x0,y0,x1,y1= 0,0,600,0

#écrit la première lettre du mot à trouver à chaque ligne.

can.create_text( 50,50,text=motT[0],font=("Courier", 25))
can.create_text( 50,150,text=motT[0],font=("Courier", 25))
can.create_text( 50,250,text=motT[0],font=("Courier", 25))
can.create_text( 50,350,text=motT[0],font=("Courier", 25))
can.create_text( 50,450,text=motT[0],font=("Courier", 25))


#Creation des lignes
 
for loop in range(7):
    can.create_line(x0,y0,x1,y1,width = 2, fill="black")
    y0= y0 + 100
    y1= y1 + 100
 

x0,y0,x1,y1= 0,0,0,600
 
for loop in range(7):
    can.create_line(x0,y0,x1,y1,width = 2,fill="black")
 
    x0= x0 + 100
    x1= x1 + 100
 
can.grid(row =6 ) #emplacement du canevas avec les cases


fen.mainloop()
fen.destroy()#fermeture de la fenêtre lorsque quitter est cliqué
pygame.quit()#éteint la musique lorsque quitter est cliqué