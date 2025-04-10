#ifndef LISTE_H
#define LISTE_H

#include "cyclicNode.h"

template <class T>

class Liste{

    protected:

        typedef DataStructure::cyclicNode<T> Chainon;
        Chainon* sentinelle;
        size_t taille;

    public:
        // Constructeur
        Liste() : sentinelle(new Chainon()), taille(0) {
            sentinelle->insertAfter(sentinelle);
            sentinelle->insertBefore(sentinelle);
        }

        // Constructeur de copie
        Liste(const Liste<T>& liste) : sentinelle(new Chainon()), taille(0) {
            for (const auto& elem : liste) {
                this->push_back(elem);
            }
        }
        // Destructeur
        ~Liste() {
            while (!empty()) {
                pop_front();
            }
            delete sentinelle;
        }
        
        // Verifie si la liste est vide ou non
        bool empty()const{
            return taille == 0;
        }

        // Renvoie la taille de la liste
        int size()const{
            return taille;
        }
        
        // Renvoie l'élement en tête de liste
        T& front()const{
            assert(!empty() && "Liste vide");
            return sentinelle->next()->data();
        }
        T& front(){
            assert(!empty() && "Liste vide");
            return sentinelle->next()->data();
        }

        // Renvoie l'élement en fin de liste
        T& back()const{
            assert(!empty() && "Liste vide");
            return sentinelle->previous()->data();
        }
        T& back(){
            assert(!empty() && "Liste vide");
            return sentinelle->previous()->data();
        }
        
        // Ajoute un élément en tête de liste
        void push_front(const T& valeur) {
            Chainon* nouveau = new Chainon(valeur);
            sentinelle->insertAfter(nouveau);
            taille++;
        }
        
        // Ajoute un élement en fin de listes
        void push_back(const T& valeur) {
            Chainon* nouveau = new Chainon(valeur);
            sentinelle->insertBefore(nouveau);
            taille++;
        }

        // Supprime le premier élement de la liste 
        void pop_front() {
            assert(!empty() && "Liste vide");
        
            Chainon* toDelete = sentinelle->next();
            if (toDelete != sentinelle) {
                toDelete->detach();
                delete toDelete; 
                taille--; 
            }
        }

        // Supprime le dernier élement de la liste 
        void pop_back(){
            assert(!empty() && "Liste vide");
            Chainon* toDelete = sentinelle->previous();
            if(toDelete != sentinelle){
                toDelete->detach();
                delete toDelete;
                taille--;
            }
        }


        class const_iterator{
            protected :
                Chainon* iterateur;
                Chainon* sentinelle;

                // Constructeur
                const_iterator(Chainon* c, Chainon* s) : iterateur(c), sentinelle(s) {};

                friend class Liste<T>;

            public:

            // Destructeur
            ~const_iterator() = default;

            /** opérateur ++ préfixé
            *   positionne l ' itérateur sur l 'élément suivant
            *   @pre l ' itérateur désigne une position valide dans la liste(!=end())
            *   @return nouvelle valeur de l ' itérateur
            */
            const_iterator & operator ++(void){
                assert(iterateur != sentinelle);
                assert(iterateur != nullptr);
                iterateur = iterateur->next();
                return *this;
            }


            /** opérateur -- préfixé
            *   positionne l ' itérateur sur l 'élément précédent
            *   @pre l ' itérateur ne désigne pas l 'élément de tête (!=begin())
            *   @return nouvelle valeur de l ' itérateur
            */
           const_iterator& operator--(void) {
            assert(iterateur != nullptr);
            assert(iterateur != sentinelle->next());
            iterateur = iterateur->previous();
            return *this;
            }
        
        
            

            /** opérateur d' indirection * (accès NONmodifiable)
            *   @pre l ' itérateur désigne une position valide dans la liste(!=end())
            *   @return valeur de l 'élément désigné par l ' itérateur
            */
            const T& operator * (void) const{
                assert(iterateur != nullptr);
                assert(iterateur != sentinelle);
                return iterateur->data();
            }


            /** opérateur d' indirection > (accès NONmodifiable)
            *   @pre l ' itérateur désigne une position valide (!=end())
            *   @return adresse de l 'élément désigné par l ' itérateur
            */
            const T* operator -> (void) const{
                assert(iterateur != sentinelle);
                assert(iterateur != nullptr);
                return &iterateur->data();
            }
            
            bool operator==(const const_iterator& other) const {
                return iterateur == other.iterateur;
            }
            
            bool operator!=(const const_iterator& other) const {
                return iterateur != other.iterateur;
            }

        };

        /**  renvoie un itérateur sur le début de liste
        *    cet itérateur désigne le premier élément de la liste si elle n'est pas vide ;
        *    sinon , i l désigne la même position que l ' itérateur renvoyé par end()
        */
        const_iterator begin(void) const{
            if(empty()){
                return const_iterator(sentinelle, sentinelle);
            }
            return const_iterator(sentinelle->next(), sentinelle);
        }

        /**  renvoie un itérateur qui désigne une position située après le dernier élément */
        const_iterator end(void) const{
            return const_iterator(sentinelle, sentinelle);
        }


        class iterator{

            friend class Liste<T>;
            protected :
                Chainon* iterateur;
                Chainon* sentinelle;


            public:

            iterator(Chainon* c, Chainon* s) : iterateur(c),sentinelle(s) {};

            ~iterator() = default;


            /** opérateur ++ préfixé
            *   positionne l ' itérateur sur l 'élément suivant
            *   @pre l ' itérateur désigne une position valide dans la liste(!=end())
            *   @return nouvelle valeur de l ' itérateur
            */
            iterator & operator ++(void){
                assert(iterateur != sentinelle);
                iterateur = iterateur->next();
                return *this;
            }


            /** opérateur -- préfixé
            *   positionne l ' itérateur sur l 'élément précédent
            *   @pre l ' itérateur ne désigne pas l 'élément de tête (!=begin())
            *   @return nouvelle valeur de l ' itérateur
            */
           iterator& operator--(void) {
            assert(iterateur != nullptr);
            assert(iterateur != sentinelle->next());
            iterateur = iterateur->previous();
            return *this;
            }


            /** opérateur d' indirection * (accès NONmodifiable)
            *   @pre l ' itérateur désigne une position valide dans la liste(!=end())
            *   @return valeur de l 'élément désigné par l ' itérateur
            */
            T& operator * (void){
                assert(iterateur != sentinelle);
                return iterateur->data();
            }


            /** opérateur d' indirection > (accès NONmodifiable)
            *   @pre l ' itérateur désigne une position valide (!=end())
            *   @return adresse de l 'élément désigné par l ' itérateur
            */
            T* operator -> (void){
                assert(iterateur != sentinelle);
                return &iterateur->data();
            }

            bool operator==(const iterator& other) const {
                return iterateur == other.iterateur;
            }
            
            bool operator!=(const iterator& other) const {
                return iterateur != other.iterateur;
            }

        };

        /**  renvoie un itérateur sur le début de liste
        *    cet itérateur désigne le premier élément de la liste si elle n'est pas vide ;
        *    sinon , i l désigne la même position que l ' itérateur renvoyé par end()
        */
        iterator begin(void){
            if(empty()){
                return iterator(sentinelle, sentinelle);
            }
            return iterator(sentinelle->next(), sentinelle);
        }

        /**  renvoie un itérateur qui désigne une position située après le dernier élément */
        iterator end(void){
            return iterator(sentinelle, sentinelle);
        }

        // Insert un élement de la valeur donnée en paramètre à la position donnée en paramètre
        iterator insert(iterator position, const T& val) {
            assert(position.iterateur != nullptr);
            assert(position.sentinelle == sentinelle);
        
            Chainon* nouveau = new Chainon(val);
            if (sentinelle->next() == sentinelle) {
                sentinelle->insertBefore(nouveau);
            } else {
                position.iterateur->insertBefore(nouveau);
            }
            taille++;
            return iterator(nouveau, sentinelle);
        }
        
        // Supprime l'élement donné en paramètre
        iterator erase(iterator aSupr) {
            assert(aSupr.iterateur != nullptr);
            assert(aSupr.sentinelle == sentinelle);
            assert(aSupr.iterateur != sentinelle);
        
            Chainon* suivant = aSupr.iterateur->next();
            aSupr.iterateur->detach();
            delete aSupr.iterateur;
            taille--;
        
            return iterator(suivant, sentinelle);
        }


        // Operateur d'affectation
        Liste<T>& operator=(const Liste<T>& autre){
            if (this != &autre){
                while (!empty()){
                    pop_front();
                }
                for (const auto& elt : autre){
                    push_back(elt);
                }
            }
            return *this;
        }

        // Opérateur de concaténation
        Liste<T> operator+(const Liste<T>& autre) const{
            Liste<T> resultat;
            for (const auto& elt : *this){
                resultat.push_back(elt);
            }
            for (const auto& elt : autre){
                resultat.push_back(elt);
            }
            return resultat;
        }

        // Opérateur d'affichage
        friend std::ostream& operator<<(std::ostream& affiche, const Liste<T>& liste) {
            affiche << "<";
            bool first = true;
            for (const auto& elem : liste) {
                if (!first) {
                    affiche << " ";
                }
                affiche << elem;
                first = false;
            }
            affiche << " >";
            return affiche;
        }
            
};

/** 3.1 chercher un élément dans la séquence [premier , dernier [
 *  @param premier : début de la séquence
 *  @param dernier : fin de séquence
 *  @param x : valeur cherchée
 * @return itérateur qui désigne x s ' i l est trouvé ;
 * cet itérateur est égal à dernier si x est absent
 */
template <class InputIterator , class T>
InputIterator find(InputIterator premier, InputIterator dernier, const T & x){
    for (; premier != dernier; ++premier){
        if (*premier == x) {
            return premier;
        }
    }
    return dernier;
}

#endif
