#include <stdio.h>
#include <stdlib.h>

int head(int n) {
    return n; 
}

int tail(int n) {
    return (n > 0) ? n - 1 : 0; 
}


int cons(int n1, int n2) {
    return n1 + n2 + 1; 
}

int main() {
    int Op1, Op2, R0, R1, Result;

    scanf("%d", &Op1);
    scanf("%d", &Op2);
    R0 = Op1;
    R1 = Op2;
    Result = R0;

L0: 
    if (R1 == 0) goto L1;
    Result = tail(Result);
    goto L0;

L1:
    printf("%d\n", Result);

    return 0;
}