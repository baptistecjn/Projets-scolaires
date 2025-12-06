% Exercice1

A = 0.5 * [sqrt(3) + 1, -2; 1, sqrt(3) - 1];

[eigenvectors, eigenvalues] = eig(A);


v = [1; 2];

Av = A * v;
A2v = (A * A) * v;
A3v = (A * A * A) * v;

B = (1/sqrt(2))* [sqrt(3) + 1, -2; 1, sqrt(3) - 1];
C = (1/2*sqrt(2))* [sqrt(3) + 1, -2; 1, sqrt(3) - 1];

Bv = B * v;
B2v = (B * B) * v;
B3v = (B * B * B) * v;

Cv = C * v;
C2v = (C * C) * v;
C3v = (C * C * C) * v;

% Affichage des résultats

disp("Eigenvalues (valeurs propres) :");
disp(diag(eigenvalues));

disp("Eigenvectors (vecteurs propres) :");
disp(eigenvectors);

disp("Av = ");
disp(Av);

disp("A^2v = ");
disp(A2v);

disp("A^3v = ");
disp(A3v);


disp("Bv = ");
disp(Bv);

disp("B^2v = ");
disp(B2v);

disp("B^3v = ");
disp(B3v);

disp("Cv = ");
disp(Cv);

disp("C^2v = ");
disp(C2v);

disp("C^3v = ");
disp(C3v);



