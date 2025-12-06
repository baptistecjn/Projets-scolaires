####### TP2 : Traitement du signal ######
# Auteurs : Gatien Auffret et Baptiste Cojean

import numpy as np
import matplotlib.pyplot as plt
from numpy.fft import fft
from numpy.fft import fftshift
from scipy import signal


## --- Partie 1 : Effet de la Fréquence d'échantillonnage
mu0 = 1
t_continu = np.linspace(0, 2, 1000)
x_continu = np.sin(2 * np.pi * mu0 * t_continu)

frequences_echantillonnage = [1, 2, 4, 10]

plt.figure(figsize=(15, 10))

for i, fe in enumerate(frequences_echantillonnage):
    Te = 1 / fe
    t_echantillon = np.arange(0, 2, Te)
    x_echantillon = np.sin(2 * np.pi * mu0 * t_echantillon)

    plt.subplot(2, 2, i+1)
    plt.plot(t_continu, x_continu, label='Signal continu')
    plt.stem(t_echantillon, x_echantillon, linefmt='r-', label=f'Échantillonné à {fe} Hz')
    plt.xlabel('Temps (s)')
    plt.ylabel('Amplitude')
    plt.legend()
    plt.grid(True)

plt.tight_layout()


## --- Partie 2 : Transformée de Fourier Discrète
f0 = 10 
fs = 32 * f0
Ts = 1 / fs
t = np.arange(0, 2, Ts)
x = np.cos(2 * np.pi * f0 * t)

plt.figure()
plt.plot(t, x, label='x(t) = cos(2πf₀t)')
plt.xlabel("Temps (s)")
plt.ylabel("Amplitude")
plt.grid()
plt.legend()

M = 256

def TFD(m, signal):
    n = 1 / M
    s = 0
    for i in range(len(signal)):
        s += signal[i] * np.e ** (-2 * 1j * np.pi * i * m / M)
    return s

ms = np.arange(0, M, 1)
sTDF = TFD(ms, x)
plt.figure()
plt.plot(ms,np.absolute(sTDF))
plt.grid()

lfft = fft(x, M)
plt.figure()
plt.plot(ms,np.absolute(lfft))
plt.grid()

freqs = np.fft.fftfreq(M, Ts)
freqs_shifted = fftshift(freqs)
sfft = fftshift(lfft)

plt.figure()
plt.plot(freqs_shifted, np.abs(sfft))
plt.grid()
plt.xlabel("Fréquence (Hz)")
plt.ylabel("Amplitude")


## --- Partie 3 : Effet des Fenêtres
# --- partie A
f0 = 100
fs = 1 / f0
t = np.arange(0, 10, fs)

v = lambda t: np.sin(2 * np.pi * 1.12 * t)

def rect_fen(t):
     if t < T:
         return 1
     else:
         return 0

T = 1
rect = np.zeros(t.size)
for k in range(t.size):
    rect[k] = rect_fen(t[k])

M = 1000
freq = np.arange(0, M, 1) * f0 / M
plt.figure()
plt.xlim([0, 10])
plt.plot(freq, np.absolute(fft(v(t) * rect)))
plt.grid()

for T in (2, 4, 8):
    def rect_fen(t):
        if t < T:
            return 1
        else:
            return 0

    rect = np.zeros(t.size)
    for k in range(t.size):
        rect[k] = rect_fen(t[k])

    M = 1000
    freq = np.arange(0, M, 1) * f0 / M
    plt.figure()
    plt.xlim([0, 10])
    plt.plot(freq, np.absolute(fft(v(t) * rect)))
    plt.grid()

f0 = 100
t = np.arange(0, 10, 1 / f0)
x = lambda t: np.sin(2 * np.pi * 1.12 * t) + np.sin(2 * np.pi * 2.01 * t)

plt.figure()
plt.plot(t, x(t))
plt.grid()

for T in (0.6, 2, 4, 8):
    rect0 = np.zeros(t.size)
    for k in range(t.size):
        rect0[k] = rect_fen(t[k])

    M = len(t)
    freq = np.arange(0, M, 1) * f0 / M
    s_rect0 = fft(x(t) * rect0)

    plt.figure()
    plt.xlim([0, 10])
    plt.plot(freq, np.absolute(s_rect0))
    plt.grid()

# --- partie B
f0 = 20
t = np.arange(0, 10, 1 / f0)
u = lambda t: np.sin(2 * np.pi * 1.524 * t) + 0.17 * np.sin(2 * np.pi * 1.867 * t)

plt.figure()
plt.plot(t, u(t))
plt.grid()

T = 7
rect2 = np.zeros(t.size)
for k in range(t.size):
    rect2[k] = rect_fen(t[k])

M = 200
freq = np.arange(0, M, 1) * f0 / M
s_rect = fft(u(t) * rect2)

plt.figure()
plt.xlim([0, 10])
plt.stem(freq, np.absolute(s_rect))
plt.grid()


## --- Partie 4 : Comparaison des Fenêtres
triangle = signal.windows.triang
hamming = signal.windows.hamming

# Fenêtre triangulaire
f_triangle = np.concatenate((triangle(int(len(t)/10*2)), np.zeros(len(t)-int(len(t)/10*2))))
plt.figure()
plt.plot(t, f_triangle)
plt.grid()
s_triangle = fft(u(t) * f_triangle)
plt.figure()
plt.xlim([0, 10])
plt.plot(freq, np.absolute(s_triangle))
plt.grid()

# Fenêtre de Hamming
f_hamming = np.concatenate((hamming(int(len(t)/10*2)), np.zeros(len(t)-int(len(t)/10*2))))
plt.figure()
plt.plot(t, f_hamming)
plt.grid()
s_hamming = fft(u(t) * f_hamming)
plt.figure()
plt.xlim([0, 10])
plt.plot(freq, np.absolute(s_hamming))
plt.grid()

fftrect2 = fft(rect2)
ffttriangle = fft(f_triangle)
ffthamming = fft(f_hamming)

eps = 1e-12

# Comparaison des effets des fenêtres
plt.figure()
plt.plot(freq, 10 * np.abs(fft(rect2 * u(t))), label="Signal fenêtre rectangle")
plt.plot(freq, 20 * np.log10((np.abs(fftrect2) + eps) / (np.max(np.abs(fftrect2)) + eps)), label="Fenêtre rectangle")
plt.legend()
plt.grid()

plt.figure()
plt.plot(freq, 10 * np.abs(fft(f_triangle * u(t))), label="Signal fenêtre triangle")
plt.plot(freq, 20 * np.log10((np.abs(ffttriangle) + eps) / (np.max(np.abs(ffttriangle)) + eps)), label="Fenêtre triangle")
plt.legend()
plt.grid()

plt.figure()
plt.plot(freq, 10 * np.abs(fft(f_hamming * u(t))), label="Signal fenêtre Hamming")
plt.plot(freq, 20 * np.log10((np.abs(ffthamming) + eps) / (np.max(np.abs(ffthamming)) + eps)), label="Fenêtre Hamming")
plt.legend()
plt.grid()

plt.show()