#include "while_runtime.h"
#include <iostream>
#include <string>
#include <cstdlib>
#include <cstring>


// Définition des types de nœuds
Tree::Tree() : type(NODE_NIL), symbol(""), car(nullptr), cdr(nullptr) {}

// Pointeur global pour le singleton NIL
Tree* NIL_NODE = nullptr;

// Prototypes nécessaires
void while_init();
Tree* make_nil();

// Initialisation du runtime
void while_init() {
    if (NIL_NODE == nullptr) {
        NIL_NODE = new Tree();
        NIL_NODE->type = NODE_NIL;
        NIL_NODE->symbol = "nil";
        NIL_NODE->car = nullptr;
        NIL_NODE->cdr = nullptr;
    }
}

// Constructeur : nil
Tree* make_nil() {
    if (NIL_NODE == nullptr) while_init();
    return NIL_NODE;
}

// Constructeur : symbole (feuille)
Tree* make_symbol(const std::string& s) {
    if (NIL_NODE == nullptr) while_init();
    Tree* t = new Tree();
    t->type = NODE_SYMBOL;
    t->symbol = s;
    t->car = NIL_NODE;
    t->cdr = NIL_NODE;
    return t;
}



// Constructeur : cons (noeud interne)
Tree* cons(Tree* car, Tree* cdr) {
    if (NIL_NODE == nullptr) while_init();
    Tree* t = new Tree();
    t->type = NODE_CONS;
    // Si l'un des fils est nul, on met NIL par sécurité
    t->car = (car == nullptr) ? NIL_NODE : car;
    t->cdr = (cdr == nullptr) ? NIL_NODE : cdr;
    return t;
}
// Accesseur : Head (hd)
Tree* hd(Tree* t) {
    if (t == nullptr || t->type != NODE_CONS) {
        return make_nil();
    }
    return t->car;
}

// Accesseur : Tail (tl)
Tree* tl(Tree* t) {
    if (t == nullptr || t->type != NODE_CONS) {
        return make_nil();
    }
    return t->cdr;
}

// Conversion Arbre -> Entier (pour l'affichage)
int tree_to_int(Tree* t) {
    int count = 0;
    Tree* current = t;
    while (current != nullptr && current->type == NODE_CONS) {
        count++;
        current = current->cdr;
    }
    return count;
}

// Conversion Entier -> Arbre (pour la lecture)
Tree* int_to_tree(int n) {
    Tree* res = make_nil();
    for (int i = 0; i < n; i++) {
        res = cons(make_nil(), res);
    }
    return res;
}

Tree* list(std::vector<Tree*> liste){
    Tree* res = nullptr;
    for(Tree* tree : liste){
        res = cons(res, tree);
    }
    return res;
}
// Pretty Printer récursif
void pp(Tree* t) {
    if (t == nullptr || t->type == NODE_NIL) {
        std::cout << "nil";
        return;
    }
    if (t->type == NODE_SYMBOL) {
        std::cout << t->symbol;
        return;
    }
    
    if (t->type == NODE_CONS) {
        std::cout << "(cons ";
        pp(t->car);
        std::cout << " ";
        pp(t->cdr);
        std::cout << ")";
    }
}

// Wrapper pour 'read X'
Tree* read_variable() {
    int val;
    std::cin >> val;
    return int_to_tree(val);
}

// Wrapper pour 'write X'
void write_variable(Tree* t) {
    std::cout << "Output (Tree): ";
    pp(t);
    std::cout << std::endl;
    std::cout << "Output (Int): " << tree_to_int(t) << std::endl;
}

// Fonction pour les conditions (if)
// Retourne 1 (vrai) si c'est un CONS, 0 sinon
int is_true(Tree* t) {
    if (t == nullptr) return 0;
    return (t->type == NODE_CONS);
}