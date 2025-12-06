% Exercice1

q1 = 2 + 3;
q2 = 2^10;
q3 = sin(2 * pi);
q4 = exp(1i * pi) + 1;

A = [1, 2; 3, 4];
B = [2, 3; 4, 5];
q5 = A * B;

A = [1, 7; 4, 2];
q6 = det(A);

% Affichage des résultats
disp(['2+3 = ', num2str(q1)]);
disp(['2^10 = ', num2str(q2)]);
disp(['sin(2π) = ', num2str(q3)]);
disp(['e^(πi)+1 = ', num2str(q4)]);
disp('(1 2)(2 3)');
disp('(3 4)(4 5) = ');
disp(q5);
disp('|1 7|');
disp('|4 2| = ');
disp(num2str(q6));
