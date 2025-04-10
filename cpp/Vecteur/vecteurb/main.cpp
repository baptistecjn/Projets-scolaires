#include "vecteur.h"

#include <iostream>

/** \brief Programme principal */

int main(){

  const unsigned int dimensions = 2;

  // Float

  Vecteur<float,dimensions> v1,v2;

  std::cout<<"entrez les "<<dimensions<<" composantes du premier vecteur(séparer d'un espace les deux coordonnées)";
  std::cin>>v1;

  std::cout<<"entrez les "<<dimensions<<" composantes du deuxième vecteur(séparer d'un espace les deux coordonnées)";
  std::cin>>v2;

  Vecteur<float, dimensions> v3;
  v3 = add(v1,v2);
  std::cout<<"v1 + v2 = "<<v3<<std::endl;

  std::cout<<"v1 * v2 = "<<v1*v2<<std::endl;


  // String
  
  Vecteur<std::string,dimensions> v11,v22;

  std::cout<<"entrez les "<<dimensions<<" chaines de caractères du premier vecteur(séparer d'un espace les deux coordonnées)";
  std::cin>>v11;

  std::cout<<"entrez les "<<dimensions<<" chaines de caractères du deuxième vecteur(séparer d'un espace les deux coordonnées)";
  std::cin>>v22;

  Vecteur<std::string, dimensions> v33;
  v33 = add(v11,v22);
  std::cout<<"v11 + v22 = "<<v33<<std::endl;

  Vecteur<int,dimensions> a;
  Vecteur<float,dimensions> b;

  std::cout<<"entrez les "<<dimensions<<" composantes du premier vecteur(entier)";
  std::cin>>a;

  std::cout<<"entrez les "<<dimensions<<" composantes du deuxième vecteur(float)";
  std::cin>>b;

  std::cout<<"a * b = "<<a*b<<std::endl;






  return 0;
}
