%exercice 3

% Définir un intervalle pour x
x = linspace(-5, 5, 1000);

% Définir les fonctions
f1 = x.^3;

f2 = x.^2 .* sin(1 ./ x);

f3 = x.^2 .* sin(1 ./ x.^2);

f4 = 1 ./ (1 + exp(-x));

f5 = 1 ./ (1 + exp(-10*x));

f6 = 1 ./ (1 + exp(-100*x));

f7 = (exp(x) - exp(-x)) ./ (exp(x) + exp(-x));

f8 = (exp(100*x) - exp(-100*x)) ./ (exp(100*x) + exp(-100*x));

% Tracer les graphes
figure;
subplot(3, 3, 1); plot(x, f1); title('f(x) = x^3');
subplot(3, 3, 2); plot(x, f2); title('f(x) = x^2 sin(1/x)');
subplot(3, 3, 3); plot(x, f3); title('f(x) = x^2 sin(1/x²)');
subplot(3, 3, 4); plot(x, f4); title('Sigmoid function');
subplot(3, 3, 5); plot(x, f5); title('Sigmoid function 2');
subplot(3, 3, 6); plot(x, f6); title('Sigmoid function 3');
subplot(3, 3, 7); plot(x, f7); title('Hyperbolic tangent');
subplot(3, 3, 8); plot(x, f8); title('Hyperbolic tangent 2');

