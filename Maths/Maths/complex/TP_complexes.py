from sympy import *

#1.1 Intégration d'une fonction

t = symbols('t',real=True)
f = lambda x : 2 + sin(x)
inte = integrate(f(t),(t,0,pi))
print(inte)

#1.2 Intégration le long d'un chemin

t = symbols('t',real=True)
z = symbols('z',complex=True)
f = z*z
gamma = exp(I*t)
gamma_p = diff(gamma,t)
f2 = f.replace(z,gamma) * gamma_p
a = 0
b = pi/3
inte = integrate(f2,(t,a,b))
print(inte)

#1.3 Création d’un script et applications

