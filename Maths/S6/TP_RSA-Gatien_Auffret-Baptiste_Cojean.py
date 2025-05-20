####### TP RSA ######
#Auteur : Gatien Auffret et Baptiste Cojean

#Imports
import doctest
import random
import time
import sympy

#Cette fonction sera utile pour les fonctions est_inversible(p,n) et generer_cle_publique_privee(n1,n2)
def pgcd(a,b):
    """
    Calcule le plus grand commun diviseur (PGCD) de deux entiers a et b
    en utilisant l'algorithme d'Euclide.

    >>> pgcd(48, 18)
    6
    >>> pgcd(17, 5)
    1
    >>> pgcd(100, 25)
    25
    >>> pgcd(0, 7)
    7
    >>> pgcd(7, 0)
    7
    """

    while b != 0: #Tant que le second nombre n'est pas nul
        a, b = b, a % b #On remplace a par b, et b par le reste de la division euclidienne a % b
    #Quand b vaut 0, a est le PGCD
    return a

def exponentiation_rapide(x,n) :
    """
    Calcul de x**n

    >>> exponentiation_rapide(3,4)
    81
    >>> exponentiation_rapide(2,10)
    1024
    """

    if n == 1:
        return x

    elif n == 0:
        return 1

    elif (n % 2) == 0:
        a = exponentiation_rapide(x, n // 2) #Si n pair, on calcule (x^(n/2))^2
        return a * a

    elif ( n % 2 == 1 ):
        a = exponentiation_rapide(x, n // 2) #Si n impair, on calcule x * (x^(n//2))^2
        return x * a * a

def exponentiation_rapide_modulaire(x, n, m) :
    """
    Calcul de x**n mod m

    >>> exponentiation_rapide_modulaire(2,10,10)
    4
    >>> exponentiation_rapide_modulaire(3,6,7)
    1
    """

    if m <= 0:
        raise ValueError("Le module m doit être strictement positif.")

    if n == 1:
        return x % m

    elif n == 0:
        return 1

    elif (n%2) == 0:
        a = exponentiation_rapide_modulaire(x, n // 2, m) #Cas n pair : calcul récursif sur n/2 et carré modulo m
        return (a * a) % m

    else:
        a = exponentiation_rapide_modulaire(x, (n - 1) // 2, m) #Cas n impair : on enlève un facteur x et on fait pareil sur (n-1)/2
        return (x * a * a) % m

def est_inversible(p,n) :
    """
    Renvoie True si p est inversible modulo n
    
    >>> est_inversible(4,16)
    False
    >>> est_inversible(13,28)
    True
    """

    return pgcd(p, n) == 1 #p est inversible modulo n si et seulement si pgcd(p,n) = 1

def calcul_inverse(p,n) :
    """
    Calcul de l'inverse de p modulo n
    (on suppose que p est inversible modulo n)
    >>> calcul_inverse(13,101)*13 % 101 == 1
    True
    """

    #Initialisation des variables pour l'algorithme étendu d'Euclide
    reste, coef_p, coef_n = p, 1, 0
    reste_suivant, coef_p_suiv, coef_n_suiv = n, 0, 1

    while reste_suivant != 0: #Tant que le reste suivant n'est pas nul
        quotient = reste // reste_suivant
        #Mise à jour des variables selon l'algorithme d'Euclide étendu
        reste, coef_p, coef_n, reste_suivant, coef_p_suiv, coef_n_suiv = (
            reste_suivant,
            coef_p_suiv,
            coef_n_suiv,
            reste - quotient * reste_suivant,
            coef_p - quotient * coef_p_suiv,
            coef_n - quotient * coef_n_suiv,
        )

    #coef_p est l'inverse de p modulo n (modulo n pour avoir un résultat positif)
    return coef_p % n

def convert_message_to_numbers(message,n) :
    """
    Convertit un message textuel en suite de nombres possédant au maximum n chiffres (en décimal)
    
    (La fonction s'assure que les coupures ne produisent pas d'entiers tronqués)
    """
    data = message.encode()
    data_str = "".join([str(1000+int(i)) for i in data])
    res = []
    j=0
    while j+n < len(data_str) :
        k=j+n
        while data_str[k] == "0" :
            k-=1
        res.append(int(data_str[j:k]))
        j = k
    res.append(int(data_str[j:]))
    return res

def convert_numbers_to_message(numbers) :
    """
    Convertit une suite de nombres en un message textuel.
    """
    data_str = "".join([str(i) for i in numbers])
    data = []
    for k in range(0,len(data_str),4) :
        data.append(int(data_str[k:k+4])-1000)
    return bytes(data).decode()

def generer_cle_publique_privee(n1,n2) :
    """
    Prend en paramètre deux nombres premiers n1 et n2 et
    génère un triplet n,d,e vérifiant :
        - n = n1*n2
        - ed = 1 mod phi(n)
    """

    n = n1 * n2 #Calcul du module n
    phi = (n1 - 1) * (n2 - 1) #Calcul de la fonction indicatrice d'Euler phi(n)

    cle_publique = random.randint(2, phi - 1) #Choix aléatoire de l'exposant e (clé publique) premier avec phi
    while pgcd(phi, cle_publique) != 1:
        cle_publique = random.randint(2, phi - 1)

    cle_privee = calcul_inverse(cle_publique, phi) #Calcul de l'exposant d (clé privée), inverse modulaire de e modulo phi

    return n, cle_privee, cle_publique #Retourne le module, la clé privée d, et la clé publique e

def chiffrement_RSA(message, cle_publique) :
    """
    Applique l'algorithme de chiffrement RSA avec la clé publique (n,d)

    >>> chiffrement_RSA("coucou",(3281309, 1556897)) == [3031416, 755249, 1486542, 2067531, 3031416, 755249, 1486542, 2067531]
    True
    """
    
    module, exposant_chiffrement = cle_publique #Récupère le module et l'exposant de chiffrement
    message_code = convert_message_to_numbers(message, len(str(module)) // 2) #Convertit le message en une liste de nombres adaptés au module
    return [exponentiation_rapide_modulaire(nombre, exposant_chiffrement, module) for nombre in message_code] #Chiffre chaque nombre par exponentiation modulaire

def dechiffrement_RSA(numbers,cle_privee) :
    """
    Applique l'algorithme de déchiffrement RSA avec la clé privée (n,e)

    >>> dechiffrement_RSA(chiffrement_RSA("coucou",(3281309, 1556897)),(3281309, 3033677)) == "coucou"
    True
    """

    module, exposant_dechiffrement = cle_privee #Récupère le module et l'exposant de déchiffrement
    message_decode = [exponentiation_rapide_modulaire(nombre, exposant_dechiffrement, module) for nombre in numbers] #Applique l'exponentiation modulaire à chaque nombre
    return convert_numbers_to_message(message_decode) #Convertit la liste des nombres déchiffrés en message texte

def test_miller_rabin_unitaire(n,a) :
    """
    >>> test_miller_rabin_unitaire(21,8)
    False
    >>> test_miller_rabin_unitaire(561,50)
    True
    """

    exposant = n - 1
    r = 0

    #Décompose n-1 en 2^r * d avec d impair
    while exposant % 2 == 0:
        exposant //= 2
        r += 1

    #Teste la propriété de Miller-Rabin
    for indice in range(r):
        if exponentiation_rapide_modulaire(a, (2 ** indice) * exposant, n) == n - 1:
            return True

    return exponentiation_rapide_modulaire(a, exposant, n) == 1  #Vérifie la condition principale

def test_miller_rabin(n) :
    """
    Effectue le test de Miller-Rabin avec k entiers aléatoires.
    Pour n < 100, on prend k = 3, sinon k = nombre de chiffres de n.
    """

    nombre_tests = max(3, len(str(n))) #Nombre de tests : au moins 3 ou nombre de chiffres de n

    #Test avec k bases aléatoires
    for _ in range(nombre_tests):
        base_alea = random.randint(2, n - 2)
        if not test_miller_rabin_unitaire(n, base_alea):
            return False

    return True

def gererer_nombre_premier(nb_chiffre) :
    """
    Génère un nombre premier de nb_chiffre chiffres, vérifié par le test de Miller-Rabin.
    """

    borne_min = exponentiation_rapide(10, nb_chiffre - 1)
    borne_max = exponentiation_rapide(10, nb_chiffre) - 1
    candidat = random.randint(borne_min, borne_max) | 1  # Choix d'un nombre impair aléatoire dans l'intervalle

    #Teste jusqu'à obtenir un nombre premier probable
    while not test_miller_rabin(candidat):
        candidat = random.randint(borne_min, borne_max) | 1

    return candidat

if __name__ == "__main__" :
    ###### tests unitaires
    doctest.testmod()
    
    ###### Préliminaires
    x,n,m = 1_039_538,234_761,91_234
    t1 = time.time()
    a = exponentiation_rapide(x, n) % m
    t2 = time.time()
    print("durée du calcul de : exponentiation_rapide("+str(x)+", "+str(n)+") % "+str(m)+" :",t2-t1)
    t1 = time.time()
    a = exponentiation_rapide_modulaire(x, n, m)
    t2 = time.time()
    print("durée du calcul de : exponentiation_rapide_modulaire("+str(x)+", "+str(n)+", "+str(m)+") :",t2-t1)

    m1,m2 = 173452, 105763221
    t1 = time.time()
    calcul_inverse(m1, m2)
    t2 = time.time()
    print("durée du calcul de : calcul_inverse("+str(m1)+", "+str(m2)+") :",t2-t1)

    ###### Algorithme RSA
    n_premier1 = 52_300_003_631
    n_premier2 = 27_100_002_169

    n_cle_publique,d_cle_publique,e_cle_privee = generer_cle_publique_privee(n_premier1, n_premier2)

    print(generer_cle_publique_privee(1223, 2683))

    message_clair = "Ce message a été codé en utilisant la méthode RSA !"

    message_chiffre = chiffrement_RSA(message_clair, (n_cle_publique,d_cle_publique) )
    print(message_chiffre)

    message_dechiffre = dechiffrement_RSA(message_chiffre, (n_cle_publique,e_cle_privee))
    print(message_dechiffre)

    ###### Génération de nombres premiers
    a = gererer_nombre_premier(20)
    print("nombre premier généré :",a)

    ###### Le calcul suivant nécessite l'installation de sympy (à décommenter dans les imports)
    print("sympy.isprime(a) :",sympy.isprime(a))