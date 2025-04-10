% Nombre de points de données
N = 100;

X = unifrnd(0, 100, [1, N]);

w0 = randi([-10, 10]);
w1 = randi([-10, 10]);

% Création de y = w0 * x + w1 + un bruit gaussien ou normal (hypothèse de la régression linéaire)
Y = w0 * X + w1 + normrnd(0, 10, [1, N]);

% Tracer les données pour voir si elles semblent linéaires
scatter(X, Y, 'filled', 'r');
hold on;
plot(X, w0 * X + w1, 'k');
hold off;

%% SSR
yt = X * w0;  
SSR = sum((yt - Y).^2);
disp("Erreur SSR : "+SSR);

%% MSE 
MSE = SSR / length(Y);
disp("Erreur MSE : "+MSE);

%% gradient
Y_pred = w0 * X + w1;
dL_dw0 = (-2/N) * sum(X .* (Y - Y_pred));
dL_dw1 = (-2/N) * sum(Y - Y_pred);    
grad = [dL_dw0; dL_dw1];
disp("gradient : "+grad);

%% descente gradient

% Paramètres d'initialisation :

mu = 0.000001; % Taux d'apprentissage
n_iter = 1000; % Nombre d'itérations
history = zeros(n_iter, 4); % Vleurs des paramètres et de la perte

for i = 1:n_iter
    Y_pred = w0 * X + w1;

    % Calcul des gradients
    dL_dw0 = (-2/N) * sum(X .* (Y - Y_pred));
    dL_dw1 = (-2/N) * sum(Y - Y_pred);

    w0 = w0 - mu * dL_dw0;
    w1 = w1 - mu * dL_dw1;

    MSE = sum((Y_pred - Y).^2) / N;

    history(i, :) = [i, w0, w1, MSE];

    if mod(i, 100) == 0
        disp("Itération : "+ i);
        disp("MSE : "+ MSE);
        disp("Gradient : " + [dL_dw0, dL_dw1]);
    end
end

%% descente gradient avec critère d'arret epsilon

% Paramètres d'initialisation : 

mu = 0.00001; % Taux d'apprentissage
epsilon = 0.00001; % Critère d'arrêt
history2 = []; % Valeurs des paramètres et de la perte
prev_MSE = inf; % Initialisation de MSE précédent
iteration = 0;


while true
    Y_pred = w0 * X + w1;

    % Calcul des gradients
    dL_dw0 = (-2/N) * sum(X .* (Y - Y_pred));
    dL_dw1 = (-2/N) * sum(Y - Y_pred);

    w0 = w0 - mu * dL_dw0;
    w1 = w1 - mu * dL_dw1;

    MSE = sum((Y_pred - Y).^2) / N;

    history2 = [history2; iteration, w0, w1, MSE];

    if mod(iteration, 100) == 0
        disp("Itération : "+ iteration);
        disp("MSE : "+ MSE);
        disp("Gradient : " + [dL_dw0, dL_dw1]);
    end

    % Vérification du critère d'arrêt
    if abs(prev_MSE - MSE) < epsilon
        break;
    end
    
    prev_MSE = MSE;
    iteration = iteration + 1;
end

%% Tracer l'évolution de la MSE au fil des itérations
figure;
plot(history(:, 1), history(:, 4), '-b', 'LineWidth', 2); 
xlabel('Nombre d itérations');
ylabel('MSE');
title('Évolution de la Loss (MSE) en fonction du nombre d itérations');
grid on;


%% Tracer l'évolution de la MSE au fil des itérations avec critère d'arrêt
figure;
plot(history2(:, 1), history2(:, 4), '-r', 'LineWidth', 2);
xlabel('Nombre d iterations');
ylabel('MSE');
title('Évolution de la Loss (MSE) en fonction du nombre d iterations (avec critère d arret)');
grid on;



%% Comparaison des différents taux d'apprentissage et mesure du temps

mu_values = [0.1, 0.01, 0.001, 0.0001];
colors = ['b', 'r', 'g', 'm'];
temps_exec = zeros(1, length(mu_values));
figure;
hold on;
for j = 1:length(mu_values)
    w0_temp = randi([-10, 10]);
    w1_temp = randi([-10, 10]);
    history_temp = zeros(1000, 4);
    
    tic;
    for i = 1:1000
        Y_pred = w0_temp * X + w1_temp;
        dL_dw0 = (-2/N) * sum(X .* (Y - Y_pred));
        dL_dw1 = (-2/N) * sum(Y - Y_pred);
        w0_temp = w0_temp - mu_values(j) * dL_dw0;
        w1_temp = w1_temp - mu_values(j) * dL_dw1;
        MSE = sum((Y_pred - Y).^2) / N;
        history_temp(i, :) = [i, w0_temp, w1_temp, MSE];
    end
    temps_exec(j) = toc;
    
    plot(history_temp(:,1), history_temp(:,4), 'Color', colors(j), 'LineWidth', 2);
end
hold off;
xlabel('Nombre d iterations');
ylabel('MSE');
legend('mu = 0.1', 'mu = 0.01', 'mu = 0.001', 'mu = 0.0001');
title('Impact du taux d apprentissage sur la descente de gradient');
grid on;

% Tracer le temps d’exécution en fonction de µ
figure;
plot(mu_values, temps_exec, 'LineWidth',2); 
xlabel('Taux d''apprentissage (\mu)');
ylabel('Temps d''exécution (s)');
title('Impact du taux d''apprentissage sur le temps d''exécution');
grid on;


%% compraison des différents critères d'arret et mesure du temps d'execution

epsilon_values = [0.1, 0.01, 0.001, 0.0001];
colors = ['b', 'r', 'g', 'm'];
temps_exec = zeros(1, length(epsilon_values));
figure;
hold on;
for j = 1:length(epsilon_values)
    w0_temp = randi([-10, 10]);
    w1_temp = randi([-10, 10]);
    history_temp = [];
    prev_MSE = inf;
    iteration = 0;
    tic;
    while true
        Y_pred = w0_temp * X + w1_temp;
        dL_dw0 = (-2/N) * sum(X .* (Y - Y_pred));
        dL_dw1 = (-2/N) * sum(Y - Y_pred);
        w0_temp = w0_temp - mu * dL_dw0;
        w1_temp = w1_temp - mu * dL_dw1;
        MSE = sum((Y_pred - Y).^2) / N;
        history_temp = [history_temp; iteration, MSE];
        
        if abs(prev_MSE - MSE) < epsilon_values(j)
            break;
        end
        prev_MSE = MSE;
        iteration = iteration + 1;
    end
    temps_exec(j) = toc;

    plot(history_temp(:,1), history_temp(:,2), 'Color', colors(j), 'LineWidth', 2);
end
hold off;
xlabel('Nombre d itérations');
ylabel('MSE');
legend('epsilon = 0.1', 'epsilon = 0.01', 'epsilon = 0.001', 'epsilon = 0.0001');
title('Impact du critère d arret epsilon');
grid on;

% Tracer le temps d'exécution en fonction de epsion
figure;
plot(epsilon_values, temps_exec, 'LineWidth',2); 
xlabel('Critere d arret epsilon');
ylabel('Temps d exécution (s)');
title('Impact du critère d arret epsilon sur le temps d exécution');
grid on;

%% Stochastic gradient descent

w0_sgd = randi([-10, 10]);
w1_sgd = randi([-10, 10]);
mu = 0.0001;
n_iterations = 1000;

history_sgd = zeros(n_iterations, 1);

tic;
for i = 1:n_iterations

    idx = randi(N);
    x_i = X(idx);
    y_i = Y(idx);
    
    y_pred = w0_sgd * x_i + w1_sgd;
    
    dL_dw0 = -2 * x_i * (y_i - y_pred);
    dL_dw1 = -2 * (y_i - y_pred);
    
    w0_sgd = w0_sgd - mu * dL_dw0;
    w1_sgd = w1_sgd - mu * dL_dw1;
    
    Y_pred_all = w0_sgd * X + w1_sgd;
    MSE_sgd = sum((Y_pred_all - Y).^2) / N;
    
    history_sgd(i) = MSE_sgd;
    
    if mod(i, 100) == 0
        disp("Itération SGD : " + i + " | MSE : " + MSE_sgd);
    end
end
execution_time_sgd = toc;

disp("Temps d'exécution de SGD : " + execution_time_sgd + " secondes");

% Tracer l'évolution de la loss pour batch vs stochastic
figure;
semilogy(1:1000, history, 'b-', 'LineWidth', 2);
hold on;
semilogy(1:1000, history_sgd, 'r-', 'LineWidth', 2);
xlabel('Nombre d itérations');
ylabel('Erreur MSE');
title('Comparaison : descente de gradient classique vs stochastique');
legend show;
grid on;
hold off;


w0_mbgd = randi([-10, 10]);
w1_mbgd = randi([-10, 10]);
mu = 0.0001; 
n_iterations = 1000; 
batchSize = 10; 

history_mbgd = zeros(n_iterations, 1);

tic;
for i = 1:n_iterations
    indices = randperm(N, batchSize);
    X_batch = X(indices);
    Y_batch = Y(indices);
    
    Y_pred = w0_mbgd * X_batch + w1_mbgd;
    
    dL_dw0 = (-2/batchSize) * sum(X_batch .* (Y_batch - Y_pred));
    dL_dw1 = (-2/batchSize) * sum(Y_batch - Y_pred);
    
    w0_mbgd = w0_mbgd - mu * dL_dw0;
    w1_mbgd = w1_mbgd - mu * dL_dw1;
    
    Y_pred_all = w0_mbgd * X + w1_mbgd;
    MSE_mbgd = sum((Y_pred_all - Y).^2) / N;
    
    history_mbgd(i) = MSE_mbgd;
    
    if mod(i, 100) == 0
        disp("Itération Mini-Batch : " + i + " | MSE : " + MSE_mbgd);
    end
end
execution_time_mbgd = toc;

disp("Temps d'exécution de Mini-Batch GD : " + execution_time_mbgd + " secondes");

% Tracer l'évolution de la loss pour mini-batch vs batch vs stochastic

figure;
semilogy(1:1000, history, 'b-', 'LineWidth', 2, 'DisplayName', 'Batch GD');
hold on;
semilogy(1:1000, history_sgd, 'r--', 'LineWidth', 2, 'DisplayName', 'Stochastic GD');
semilogy(1:1000, history_mbgd, 'g-.', 'LineWidth', 2, 'DisplayName', 'Mini-Batch GD');
hold off;
legend show;
grid on;
xlabel('Nombre d''itérations');
ylabel('Erreur MSE (échelle log)');
title('Comparaison en échelle logarithmique');



