//\brief Ce fichier doit contenir la déclaration de la classe vecteur
//Attention, elle ne doit contenir aucune implémentation de méthode / fonction
#include <iostream>
#ifndef _VECTEUR_H
#define _VECTEUR_H

// Déclaration de la classe vecteur
class Vecteur {
  private :
    // attributs
    const unsigned int m_N;
    float* m_coordonnees;


  public :
    // prototypes des constructeurs et autres méthodes publiques
    Vecteur(unsigned int N = 3, float valeur = 0.0);

    Vecteur(const Vecteur &autre);

    
    float get(int index) const;
    void set(int index, float valeur);
    int dimensions() const;
    
    Vecteur& operator=(const Vecteur &autre);

    Vecteur operator+(const Vecteur& autre) const;

    friend std::ostream& operator<<(std::ostream& out, const Vecteur& v);
    friend std::istream& operator>>(std::istream& in, Vecteur& v);

    // Surcharge de l'opérateur []
    float operator[](int index) const; 
    float& operator[](int index);      
    
    ~Vecteur();

  private :
  // méthodes privées d'implémentation (si besoin)
};

// Prototypes des fonctions

void afficherVecteur(const Vecteur * v, std::ostream & out = std::cout);
Vecteur * lireVecteur(std::istream & in = std::cin);
Vecteur add(const Vecteur * v1, const Vecteur * v2);
Vecteur operator*(const Vecteur& v1,const Vecteur& v2);




#endif
