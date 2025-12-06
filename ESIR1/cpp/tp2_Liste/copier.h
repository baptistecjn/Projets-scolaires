#ifndef COPIER_H
#define COPIER_H

#include "Liste.h"

/**
   3.2.1 chercher une valeur dans une liste triée, nom de la fonction fonction : chercherTri
   @param premier : itérateur positionné sur le premier élément
   @param dernier : itérateur positionné après le dernier élément
   @param x : valeur à chercher
   @return position du premier élément de valeur >= x
   ou end() si un tel élément n'existe pas
 */
// TODO
template <typename InputIterator, typename T>
InputIterator chercherTri(InputIterator premier, InputIterator dernier, const T& x) {
    while (premier != dernier) {
        if (*premier >= x) {
            return premier;
        }
        ++premier;
    }
    return dernier;
}

/**
   3.2.2 créer une copie triée par valeurs croissantes d'une liste, nom de la fonction fonction : copierTri
   @param l : liste à copier
   @return liste triée
*/
// TODO
template <typename T>
Liste<T> * copierTri(const Liste<T>& l) {
    Liste<T> * result = new Liste<T>();;
    for(auto i = l.begin();i!=l.end();++i){
        auto position = chercherTri(result->begin(), result->end(), *i);
        result->insert(position,*i);
    }
    return result;
}


#endif