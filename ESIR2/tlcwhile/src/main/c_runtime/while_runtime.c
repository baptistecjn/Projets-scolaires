#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef enum {
    NODE_NIL,
    NODE_CONS,
    NODE_SYMBOL
} NodeType;

typedef struct Tree {
    NodeType type;
    char* symbol;
    struct Tree* car;
    struct Tree* cdr;
} Tree;

Tree* NIL_NODE = NULL;

void while_init() {
    if (NIL_NODE == NULL) {
        NIL_NODE = (Tree*)malloc(sizeof(Tree));
        NIL_NODE->type = NODE_NIL;
        NIL_NODE->symbol = "nil";
        NIL_NODE->car = NULL;
        NIL_NODE->cdr = NULL;
    }
}

Tree* make_nil() {
    if (NIL_NODE == NULL) while_init();
    return NIL_NODE;
}

Tree* make_symbol(char* s) {
    Tree* t = (Tree*)malloc(sizeof(Tree));
    t->type = NODE_SYMBOL;
    t->symbol = strdup(s);
    t->car = NIL_NODE;
    t->cdr = NIL_NODE;
    return t;
}

Tree* cons(Tree* car, Tree* cdr) {
    Tree* t = (Tree*)malloc(sizeof(Tree));
    t->type = NODE_CONS;
    t->car = (car == NULL) ? NIL_NODE : car;
    t->cdr = (cdr == NULL) ? NIL_NODE : cdr;
    return t;
}

Tree* hd(Tree* t) {
    if (t == NULL || t->type != NODE_CONS) {
        return NIL_NODE;
    }
    return t->car;
}

Tree* tl(Tree* t) {
    if (t == NULL || t->type != NODE_CONS) {
        return NIL_NODE;
    }
    return t->cdr;
}

int tree_to_int(Tree* t) {
    int count = 0;
    Tree* current = t;
    while (current != NULL && current->type == NODE_CONS) {
        count++;
        current = current->cdr;
    }
    return count;
}

Tree* int_to_tree(int n) {
    Tree* res = NIL_NODE;
    for (int i = 0; i < n; i++) {
        res = cons(NIL_NODE, res);
    }
    return res;
}

void pp(Tree* t) {
    if (t == NULL || t->type == NODE_NIL) {
        printf("nil");
        return;
    }
    if (t->type == NODE_SYMBOL) {
        printf("%s", t->symbol);
        return;
    }
    
    if (t->type == NODE_CONS) {
        printf("(cons ");
        pp(t->car);
        printf(" ");
        pp(t->cdr);
        printf(")");
    }
}

Tree* read_variable() {
    int val;
    scanf("%d", &val);
    return int_to_tree(val);
}

void write_variable(Tree* t) {
    printf("Output (Tree): ");
    pp(t);
    printf("\nOutput (Int): %d\n", tree_to_int(t));
}

int is_true(Tree* t) {
    if (t == NULL) return 0;
    return (t->type == NODE_CONS);
}