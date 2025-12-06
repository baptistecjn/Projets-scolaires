####### TP1 : Traitement du signal ######
#Auteur : Gatien Auffret et Baptiste Cojean

#Import
from scipy import signal
import numpy as np
import matplotlib.pyplot as plt


####### Partie 1 : Prise en mains des outils pour le signal ######
#Définition des variables
fe = 100 #Fréquence d'échantillonnage
t = np.arange(0, 2, 1/fe) #Définition de l'axe t

#Création des signaux
signal_creneau = signal.square(2 * np.pi * 2 * t)
signal_sinusoidal = np.sin(2 * np.pi * 5 * t)
signal_dents = signal.sawtooth(2 * np.pi * 10 * t)

#Génératipn des fenêtres
N = len(t)
fenetre_rect = signal.windows.boxcar(N)
fenetre_tri = signal.windows.triang(N)
fenetre_hamming = signal.windows.hamming(N)

#Multiplication du signal créneau par les fenêtres
signal_creneau_rect = signal_creneau * fenetre_rect
signal_creneau_tri = signal_creneau * fenetre_tri
signal_creneau_hamming = signal_creneau * fenetre_hamming

#Affichage des signaux
plt.figure(figsize=(12, 5))
plt.plot(t, signal_creneau, label="Signal créneau (original)", linestyle='--', color='black', marker='o', markersize=3)
plt.plot(t, signal_creneau_rect, label="Fenêtre rectangulaire", color='blue')
plt.plot(t, signal_creneau_tri, label="Fenêtre triangulaire", color='orange')
plt.plot(t, signal_creneau_hamming, label="Fenêtre de Hamming", color='green')
plt.title("Signal créneau avec différentes fenêtres")
plt.xlabel("Temps (s)")
plt.ylabel("Amplitude")
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.show()


####### Partie 2 : Décomposition en série de Fourier ######
#Définition des variables
E = 2 #Amplitude
f0 = 5 #Fréquence du signal
fe = 1000 #Fréquence d'échantillonnage
T0 = 1 / f0  #Période du signal
t = np.arange(0, 2, 1/fe) #Définition de l'axe t
v = E/2 + (E/2) * signal.square(2 * np.pi * f0 * (t + T0/4)) #Signal créneau périodique

#Tracé du signal créneau périodique
plt.figure(figsize=(8, 4))
plt.plot(t, v, label="Signal créneau périodique (f0=5 Hz, E=2)")
plt.title("Signal créneau périodique")
plt.xlabel("Temps (s)")
plt.ylabel("Amplitude")
plt.grid(True)
plt.show()

#Reconstruction avec les 7 harmoniques
def Fourier(f0, t, E, N):
    retour = np.zeros_like(t) + E / 2
    for i in range(1, N+1):
        An = (2 * E / (np.pi * i)) * np.sin(np.pi * i / 2)
        retour += An * np.cos(2 * np.pi * i * f0 * t)
    return retour

Fourier7 = Fourier(f0, t, E, 7)

#Affichage
plt.figure(figsize=(8, 4))
plt.plot(t, v, label="Signal créneau périodique", color='blue')
plt.plot(t, Fourier7, label="Série de Fourier (7 harmoniques)", color='purple')
plt.title("Signal créneau périodique et signal par série de Fourier")
plt.xlabel("Temps (s)")
plt.ylabel("Amplitude")
plt.legend()
plt.grid(True)
plt.show()

#Affichage et calcul pour l'influence du nombre d'harmoniques
harmoniques = [5, 10, 15, 20, 25]
for N in harmoniques:
    harmoniques = Fourier(f0, t , E, N)
    plt.figure(figsize=(10, 6))
    plt.plot(t, v, label='Signal créneau périodique')
    plt.plot(t, harmoniques, label=f"Série de Fourier ({N} harmoniques)", linestyle='--')
    plt.title(f'Signal créneau périodique et somme des {N} premiers harmoniques')
    plt.xlabel('Temps (s)')
    plt.ylabel('Amplitude')
    plt.legend()
    plt.grid(True)
    plt.show()

#Spectre en amplitude
n = np.arange(1, 8)
Amplitude = (2 * E / (np.pi * n)) * np.sin(np.pi * n / 2)

#Affichage du spectre en amplitude
plt.figure(figsize=(6, 4))
plt.stem(n, np.abs(Amplitude))
plt.title("Spectre en amplitude")
plt.xlabel("Harmonique")
plt.ylabel("Amplitude")
plt.grid(True)
plt.show()

#Génération d'un signal en dent de scie
#Définition des variables
f0 = 1 / (2 * np.pi) #Fréquence de la dent de scie
fe = 1000 #Fréquence d'échantillonnage
t = np.arange(-5, 5, 1/fe) #Axe t
signal_dents = np.pi * signal.sawtooth(t-np.pi) #Signal en dent de scie

#Tracé du signal en dent de scie
plt.figure(figsize=(8, 4))
plt.plot(t, signal_dents, label="Signal en dent de scie")
plt.title("Signal en dent de scie")
plt.xlabel("Temps (s)")
plt.ylabel("Amplitude")
plt.grid(True)
plt.legend()
plt.show()

#Reconstruction avec 3 harmoniques
def Fourier_dent(f0, t, N):
    retour = np.zeros_like(t)
    for i in range(1, N + 1):
        Bn = (2 * (-1) ** (i + 1)) / i
        retour += Bn * np.sin(2 * np.pi * i * f0 * t)
    return retour

Fourier_dent3 = Fourier_dent(f0, t , 3)

#Tracé de la somme des 3 premiers harmoniques
plt.figure(figsize=(8, 4))
plt.plot(t, Fourier_dent3, label="Série de Fourier (3 harmoniques)")
plt.title("Série de Fourier")
plt.xlabel("Temps (s)")
plt.ylabel("Amplitude")
plt.legend()
plt.grid(True)
plt.show()

#Tracé de la somme des N premiers harmoniques
harmoniques = [5, 10, 15, 20, 25]
for N in harmoniques:
    harmoniques = Fourier_dent(f0, t ,N)
    plt.figure(figsize=(10, 6))
    plt.plot(t, signal_dents, label='Signal en dents de scie')
    plt.plot(t, harmoniques, label=f'Somme des {N} premiers harmoniques', linestyle='--')
    plt.title(f'Signal en dents de scie et somme des {N} premiersharmoniques')
    plt.xlabel('Temps (s)')
    plt.ylabel('Amplitude')
    plt.legend()
    plt.grid(True)
    plt.show()

n = 11 #Pour l'harmonique 11
An_creaneau = (2 * E / (np.pi * n)) * np.sin(np.pi * n / 2) #Amplitude signal créneau pour l'harmonique 11
An_dents = (2 / (np.pi * n)) * (-1)**(n + 1) #Amplitude signal dents de scie pour l'harmonique 11

#Affichage
plt.figure(figsize=(6, 4))
plt.stem([11], [np.abs(An_creaneau)], label='Signal créneau')
plt.stem([11], [np.abs(An_dents)], label='Signal en dents de scie', markerfmt='ro')
plt.title("Comparaison des amplitudes pour l'harmonique 11")
plt.xlabel("Harmonique")
plt.ylabel("Amplitude")
plt.legend()
plt.grid(True)
plt.show()


####### Partie 3 : Convolution ######
#Définition des variables
t = np.linspace(-2, 2, 1000)#Axe t
#Signal sinusoïdal
A = 2 #Amplitude
v0 = 5 #Fréquence propre
phi = 0 #Phase à l'origine
a_t = A * np.sin(2 * np.pi * v0 * t)

# Tracé du signal sinusoïdal
plt.figure(figsize=(10, 4))
plt.plot(t, a_t, label="Sinusoïdal", color='blue')
plt.title("Signal sinusoïdal")
plt.xlabel("Temps (s)")
plt.ylabel("Amplitude")
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.show()

#Signal triangulaire
b_t = np.piecewise(t, [np.abs(t) < 1, np.abs(t) >= 1], [lambda t: 1 - np.abs(t), 0])

#Signal rectangulaire
c_t = np.piecewise(t, [np.abs(t) < 0.5, np.abs(t) >= 0.5], [1, 0])

#Signal échelon
d_t = np.piecewise(t, [t >= 0, t < 0], [1, 0])

#Signal exponentiel
e_t = np.piecewise(t, [t >= 0, t < 0], [lambda t: np.exp(-2*t), 0])

# Tracé des signaux
plt.figure(figsize=(12, 6))
plt.plot(t, b_t, label="Triangulaire", color='orange')
plt.plot(t, c_t, label="Rectangulaire", color='green')
plt.plot(t, d_t, label="Échelon", color='red')
plt.plot(t, e_t, label="Exponentiel", color='purple')
plt.title("Autres signaux (triangulaire, rectangulaire, échelon, exponentiel)")
plt.xlabel("Temps (s)")
plt.ylabel("Amplitude")
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.show()

#Convolution entre b(t) et c(t)
conv_bc = signal.convolve(b_t, c_t, mode='same') * (t[1] - t[0])

#Affichage de la convulution entre b(t) et c(t)
plt.figure(figsize=(6, 4))
plt.plot(t, conv_bc)
plt.title('Convolution entre b(t) et c(t)')
plt.xlabel('Temps (s)')
plt.ylabel('Amplitude')
plt.grid(True)
plt.show()

#Convolution entre d(t) et e(t)
conv_de = signal.convolve(d_t, e_t, mode='same') * (t[1] - t[0])

#Affichage de la convulution entre d(t) et e(t)
plt.figure(figsize=(6, 4))
plt.plot(t, conv_de)
plt.title('Convolution entre d(t) et e(t)')
plt.xlabel('Temps (s)')
plt.ylabel('Amplitude')
plt.grid(True)
plt.show()


####### Partie 4 : Corrélation ######
#Autocorrélation de a(t)
autoCorr_a = signal.correlate(a_t, a_t, mode='same') * (t[1] - t[0])

#Affichage de l'autocorrélation de a(t)
plt.figure(figsize=(6, 4))
plt.plot(t, autoCorr_a)
plt.title('Autocorrélation du signal sinusoïdal')
plt.xlabel('Temps (s)')
plt.ylabel('Amplitude')
plt.grid(True)
plt.show()

#Convolution (intercorrélation) entre d(t) et e(t)
interCorr_de = signal.correlate(d_t, e_t, mode='same') * (t[1] - t[0])

#Affichage de l'intercorrélation
plt.figure(figsize=(6, 4))
plt.plot(t, interCorr_de)
plt.title('Intercorrélation entre d(t) et e(t)')
plt.xlabel('Temps (s)')
plt.ylabel('Amplitude')
plt.grid(True)
plt.show()