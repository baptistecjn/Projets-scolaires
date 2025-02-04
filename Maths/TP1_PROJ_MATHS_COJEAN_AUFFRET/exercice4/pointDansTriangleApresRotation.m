function result = pointDansTriangleApresRotation(P, A, B, C)

    R = [0 -1; 1 0];
    A_rot = R * A';
    B_rot = R * B';
    C_rot = R * C';
    
    result = pointDansTriangle(P, A_rot', B_rot', C_rot');
end