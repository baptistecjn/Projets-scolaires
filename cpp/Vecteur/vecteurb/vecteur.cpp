/** \brief Ce fichier doit contenir l'ensemble des implémentations
relatives à la classe vecteur et aux fonctionnalités la concernant */

#include <cassert>
#include <iostream>
#include "vecteur.h"


Vecteur::Vecteur(unsigned int N, float valeur) : m_N(N) {
    m_coordonnees = new float[m_N];
    
    for(unsigned int i = 0; i < m_N; i++) {
        m_coordonnees[i] = valeur;
    }
    
    // std::cout << "Construction du vecteur suivant : " << std::endl;
    // std::cout << "Vecteur de dimension : " << m_N << std::endl;
    // std::cout << "Coordonnées : ";
    // for (unsigned int i = 0; i < m_N; i++) {
    //     std::cout << m_coordonnees[i] << " ";
    // }
    //std::cout << std::endl;
    
}

float Vecteur::get(int index) const {
    assert(index >= 0 && index < m_N);
    return m_coordonnees[index];
}

void Vecteur::set(int index, float valeur) {
    assert(index >= 0 && index < m_N);
    m_coordonnees[index] = valeur;
}

int Vecteur::dimensions() const {
    return m_N;
}

void afficherVecteur(const Vecteur * v, std::ostream & out){
    out << "Vecteur de dimension : " << v->dimensions() << std::endl;
    out << "Coordonnées : ";
    for (unsigned int i = 0; i < v->dimensions(); i++) {
        out << v->get(i) << " ";
        }
    out << std::endl;
}

Vecteur * lireVecteur(std::istream & in){
    std::cout << "Entrez le nombre de dimensions : "<< std::endl;
    unsigned int N;
    in >> N;

    std::cout << "Entrez les N coordonnées du vecteur de N dimensions : "<< std::endl;
    float valeur;
    in >> valeur;

    return new Vecteur(N, valeur);
}

Vecteur add(const Vecteur* v1, const Vecteur* v2) {
    assert(v1->dimensions() == v2->dimensions());

    Vecteur v(v1->dimensions(), 0);

    for (unsigned int i = 0; i < v.dimensions(); i++){
        v.set(i, v1->get(i) + v2->get(i));
    }
    return v;
}


Vecteur::Vecteur(const Vecteur &autre) : m_N(autre.m_N) {
    m_coordonnees = new float[m_N];

    for (unsigned int i = 0; i < m_N; i++) {
        m_coordonnees[i] = autre.m_coordonnees[i];
    }
}


Vecteur::~Vecteur(){
    delete[] m_coordonnees;
}


Vecteur& Vecteur::operator=(const Vecteur &autre){
    if (this != &autre) {
        assert(m_N == autre.m_N && "tailles différentes");

        for (unsigned int i = 0; i < m_N; i++) {
            m_coordonnees[i] = autre.m_coordonnees[i];
        }
    }
    return *this;
}

Vecteur Vecteur::operator+(const Vecteur& autre)const{
    assert(m_N == autre.m_N && "tailles différentes");

    Vecteur resultat(m_N, 0.0f);
    for (unsigned int i = 0; i < m_N; i++) {
        resultat.m_coordonnees[i] = m_coordonnees[i] + autre.m_coordonnees[i];
    }
    return resultat;
}

Vecteur operator*(const Vecteur& v1, const Vecteur& v2) {
    assert(v1.dimensions() == v2.dimensions() && "tailles différentes");

    Vecteur resultat(v1.dimensions(), 0.0f);
    for (unsigned int i = 0; i < v1.dimensions(); i++) {
        resultat.set(i, v1.get(i) * v2.get(i));
    }
    return resultat;
}


std::ostream& operator<<(std::ostream& out, const Vecteur& v) {
    out << "[";
    for (unsigned int i = 0; i < v.dimensions(); i++) {
        out << v.get(i);
        if (i < v.dimensions() - 1) {
            out << ", ";
        }
    }
    out << "]";
    return out;
}

std::istream& operator>>(std::istream& in, Vecteur& v) {
    for (unsigned int i = 0; i < v.dimensions(); i++) {
        in >> v.m_coordonnees[i];
    }
    return in;
}


float Vecteur::operator[](int index) const {
    assert(index >= 0 && index < m_N);
    return m_coordonnees[index];
}

float& Vecteur::operator[](int index) {
    assert(index >= 0 && index < m_N);
    return m_coordonnees[index];
}
