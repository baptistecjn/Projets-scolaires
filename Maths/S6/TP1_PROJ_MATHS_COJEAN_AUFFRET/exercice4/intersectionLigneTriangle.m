function result = intersectionLigneTriangle(A, B, C, normal, P)
    d = dot(normal, P - A);
    if abs(d) < 1e-10
        result = pointDansTriangle(P, A, B, C);
    else
        result = false;
    end
end