function add :
read Op1, Op2
%
    Result := Op1;
    for Op2 do
        Result := (cons nil Result)
    od
%
write Result

function main :
read Arg1, Arg2
%
    Result := (add Arg1 Arg2)
%
write Result