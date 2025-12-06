function result = points_alignes(A, B, C)
    M = [A(1), A(2), 1; B(1), B(2), 1; C(1), C(2), 1];
    result = (det(M) == 0);
end
