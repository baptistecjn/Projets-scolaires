function result = pointDansTriangle(P, A, B, C)
    
    S_ABC = abs(det([B - A; C - A])) / 2;
    S_PAB = abs(det([B - P; A - P])) / 2;
    S_PBC = abs(det([C - P; B - P])) / 2;
    S_PCA = abs(det([A - P; C - P])) / 2;
    
    result = (abs(S_PAB + S_PBC + S_PCA - S_ABC) < 1e-10);
end