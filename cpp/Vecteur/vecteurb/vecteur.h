#ifndef _VECTEUR_H
#define _VECTEUR_H

#include <iostream>
#include <cassert>

template <typename T, unsigned int N>

class Vecteur {
  
  private:
    T m_coordonnees[N];

  public:
    // Constructeur
    Vecteur(T valeur = T{}) {
      for (unsigned int i = 0; i < N; i++) {
        m_coordonnees[i] = valeur;
      }
    }

    // Constructeur de copie
    Vecteur(const Vecteur &autre) {
      for (unsigned int i = 0; i < N; i++) {
        m_coordonnees[i] = autre.m_coordonnees[i];
      }
    }
    
    // Opérateur =
    Vecteur& operator=(const Vecteur &autre) {
      if (this != &autre) {
        for (unsigned int i = 0; i < N; i++) {
          m_coordonnees[i] = autre.m_coordonnees[i];
        }
      }
      return *this;
    }

    // Opérateur +
    Vecteur operator+(const Vecteur& autre) const {
      Vecteur resultat(T{});
      for (unsigned int i = 0; i < N; i++) {
        resultat.m_coordonnees[i] = m_coordonnees[i] + autre.m_coordonnees[i];
      }
      return resultat;
    }

    // Opérateurs []
    T operator[](int index) const {
      assert(index >= 0 && index < static_cast<int>(N));
      return m_coordonnees[index];
    }
    T& operator[](int index) {
      assert(index >= 0 && index < static_cast<int>(N));
      return m_coordonnees[index];
    }
    
    // renvoie la taille du vecteur
    unsigned int dimensions() const { return N; }

    // Destructeur
    ~Vecteur() = default;

    // Opérateurs << et >>
    friend std::ostream& operator<<(std::ostream& out, const Vecteur& v) {
      out << "[";
      for (unsigned int i = 0; i < N; i++) {
        out << v.m_coordonnees[i];
        if (i < N - 1) {
          out << ", ";
        }
      }
      out << "]";
      return out;
    }

    friend std::istream& operator>>(std::istream& in, Vecteur& v) {
      for (unsigned int i = 0; i < N; i++) {
        in >> v.m_coordonnees[i];
      }
      return in;
    }
};


template <typename T, unsigned int N>
void afficherVecteur(const Vecteur<T,N> & v) {
    std::cout << "Vecteur de dimension : " << v.dimensions() << std::endl;
    std::cout << "Coordonnées : " << v << std::endl;
}

template <typename T, unsigned int N>
Vecteur<T,N> add(const Vecteur<T,N>& v1, const Vecteur<T,N>& v2) {
    return v1 + v2;
}

// Opérateur *
template <typename T1, typename T2, unsigned int N>
Vecteur<std::common_type_t<T1,T2>,N> operator*(const Vecteur<T1,N>& v1, const Vecteur<T2,N>& v2) {
  
  using type = std::common_type_t<T1,T2>;
  Vecteur<type,N> resultat(type{});

    for (unsigned int i = 0; i < N; i++) {
        resultat[i] = v1[i] * v2[i];
    }
    return resultat;
}

#endif