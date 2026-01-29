function test_structs :
read X
%
    if X then
        Y := (cons nil nil)
    else
        Y := nil
    fi;
    
    while Y do
        Y := (tl Y)
    od;

    foreach Element in X do
        nop
    od
%
write Y