
function main
    % Déclaration des optimals
    A = [1,1,1;
         1,2,2;
         1,2,3];
    k = [1;
         1;
         1];
    alpha = 0.01;
    tol = 1e-6;
    itermax = 1000;

    [p_const, erreur_const, n_iter_const] = Gradientfixe(A, k, alpha, tol, itermax);
    [p_var, erreur_var, n_iter_var] = Gradientoptimal(A, k, tol, itermax);

    % 4 cas
    [p1, e1, i1] = Gradientfixe(A, k, 0.005, tol, itermax);
    [p2, e2, i2] = Gradientfixe(A, k, 0.05, tol, itermax);
    [p3, e3, i3] = Gradientfixe(A, k, 0.1, tol, itermax);
    [p4, e4, i4] = Gradientoptimal(A, k, tol, itermax);

    % Affichage
    figure('Position', [100, 100, 1000, 700]);

    subplot(2,2,1);
    plot(e1);
    title(sprintf('Pas fixe 0.005 : %d itérations', i1));
    xlabel('Itération'); ylabel('Erreur');

    subplot(2,2,2);
    plot(e2);
    title(sprintf('Pas fixe 0.05 : %d itérations', i2));
    xlabel('Itération'); ylabel('Erreur');

    subplot(2,2,3);
    plot(e3);
    title(sprintf('Pas fixe 0.1 : %d itérations', i3));
    xlabel('Itération'); ylabel('Erreur');

    subplot(2,2,4);
    plot(e4);
    title(sprintf('Pas optimal : %d itérations', i4));
    xlabel('Itération'); ylabel('Erreur');

end

%% Gradient de la fonction
function g = gradient(A, p, k)
    g = A * p - k;
end

%% Méthode du gradient à pas fixe
function [p, erreur, n_iter] = Gradientfixe(A, k, alpha, tol, itermax)
    erreur = [];
    n = length(k);
    p{1} = rand(n, 1);

    for i = 1:itermax
        g = gradient(A, p{i}, k);
        p{i+1} = p{i} - alpha * g;
        err = norm(p{i+1} - p{i});
        erreur(end+1) = err;

        if err < tol
            break;
        end
    end

    n_iter = i;
end

%% Méthode du gradient à pas optimal
function [p, erreur, n_iter] = Gradientoptimal(A, k, tol, itermax)
    erreur = [];
    n = length(k);
    p{1} = rand(n, 1);

    for i = 1:itermax
        g = gradient(A, p{i}, k);

        a2 = (g') * A * g / 2;
        a1 = (k' * g) - (p{i}' * A * g / 2) - (g' * A * p{i} / 2);
        alpha = -a1 / (2 * a2);

        p{i+1} = p{i} - alpha * g;
        err = norm(p{i+1} - p{i});
        erreur(end+1) = err;

        if err < tol
            break;
        end
    end

    n_iter = i;
end



