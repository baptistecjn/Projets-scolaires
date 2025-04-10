from sympy import *

def integr(f,g,a,b):
    gp = diff(g,t)
    f = f.replace(z,g) * gp
    return simplify( integrate(f,(t,a,b)))

z = symbols('z',complex=True)
t = symbols('t',complex=True)
f1 = z*z
f2 = 1 / z
f3 = conjugate( z )
g = cos(t) + I * sin(t)
a = 0
b = 2*pi
print( "f1 : ",integr(f1,g,a,b) )
print( "f2 : ",integr(f2,g,a,b) )
print( "f3 : ",integr(f3,g,a,b) )