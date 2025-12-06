function result = estConvexe(A, B, C, D)
    cross1 = cross([B - A, 0], [C - B, 0]);
    cross2 = cross([C - B, 0], [D - C, 0]);
    cross3 = cross([D - C, 0], [A - D, 0]);
    cross4 = cross([A - D, 0], [B - A, 0]);
    
    result = (sign(cross1(3)) == sign(cross2(3))) && (sign(cross2(3)) == sign(cross3(3))) && (sign(cross3(3)) == sign(cross4(3)));
end
