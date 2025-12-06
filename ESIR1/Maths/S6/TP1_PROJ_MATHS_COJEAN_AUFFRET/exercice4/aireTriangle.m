function S = aireTriangle(A, B, C)
    S = abs(det([B - A; C - A])) / 2;
end
