#include "Pheromone.h"

// Retourne la quantité de phéromone
float Pheromone::getQuantity() const{
    return qte;
}

// Ajoute une quantité q de phéromones
void Pheromone::addQuantity(float q){
    qte += q;
}

// Met à jour le phéromone en diminuant sa quantité avec le temps et le supprime si il est trop faible
void Pheromone::update(){
    qte -= 0.01f * qte * Timer::dt();

    if(qte < 0.01f){
        setStatus(Agent::destroy);
    }
    if(qte >= 0.01f){
        Uint8 alpha = static_cast<Uint8>(std::min(qte, 255.0f));
        Renderer::getInstance()->drawCircle(getPosition(), 1, Renderer::Color(0, 128, 128, alpha));
    }
}

