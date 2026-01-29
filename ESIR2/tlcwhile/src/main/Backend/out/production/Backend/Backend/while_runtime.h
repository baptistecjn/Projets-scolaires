#ifndef WHILE_RUNTIME_H
#define WHILE_RUNTIME_H

#include <iostream>
#include <string>
#include <vector>

// --- Définitions des types ---
enum NodeType {
    NODE_NIL,
    NODE_CONS,
    NODE_SYMBOL
};

struct Tree {
    NodeType type;
    std::string symbol;
    Tree* car;
    Tree* cdr;
    Tree(); 
};

typedef Tree TreeNode;

// --- Déclarations des fonctions ---
extern Tree* NIL_NODE;

void while_init();
Tree* make_nil();
Tree* make_symbol(const std::string& s);
Tree* cons(Tree* car, Tree* cdr);
Tree* hd(Tree* t);
Tree* tl(Tree* t);
int tree_to_int(Tree* t);
Tree* int_to_tree(int n);
void pp(Tree* t);
Tree* read_variable();
void write_variable(Tree* t);
int is_true(Tree* t);

// --- Bridge pour notre Translator Java ---
class TreeNodeUtil {
public:
    static Tree* CONS(Tree* a, Tree* b) { return cons(a, b); }
    static Tree* HEAD(Tree* t) { return hd(t); }
    static Tree* TAIL(Tree* t) { return tl(t); }
    static Tree* LIST(std::vector<Tree*> elements) {
        Tree* res = make_nil();
        for (int i = (int)elements.size() - 1; i >= 0; --i) {
            res = cons(elements[i], res);
        }
        return res;
    }
};

#endif