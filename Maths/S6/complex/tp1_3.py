from sympy import *

def integr(f,g,a,b):
    gp = diff(g,t)
    f = f.replace(z,g) * gp
    return simplify( integrate(f,(t,a,b)))

t = symbols('t',real=True)
z = symbols('z',complex=True)
f = z*z + 3*z
gamma = 2*exp(I*t)
a = 2
b = 2*I
b_inf = solve(gamma - a,t)
b_sup = solve(gamma - b,t)
print(integr(f,gamma,b_inf,b_sup))

#1.3b

t = symbols('t',complex=True)
z = symbols('z',complex=True)
f = z*z + 3*z
a = 2
b = 2+2*I
c = 2*I
gamma = -I*t
b_inf = solve(gamma-a,t)
b_sup = solve(gamma-b,t)
res1 = integr(f,gamma,b_inf,b_sup)
gamma = -t
b_inf = solve(gamma-b,t)
b_sup = solve(gamma-c,t)
res2 = integr(f,gamma,b_inf,b_sup)
print(res1+res2)