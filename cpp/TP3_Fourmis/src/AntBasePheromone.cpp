#include "AntBasePheromone.h"

// Dépose une quantité q de phéromone à la position actuelle
void AntBasePheromone::putPheromone(float q){
    std::vector<Pheromone*> pheromones = perceive<Pheromone>();

    if(!pheromones.empty()){
        pheromones.front()->addQuantity(q);
    }else{
        Pheromone* nvPheromone = new Pheromone(getEnvironment(), getPosition(), q);
    }
}

// Choisit un phéromone à suivre parmi celles perçues dans la direction actuelle
Pheromone* AntBasePheromone::choisirPheromone() const{
    std::vector<Pheromone*> pheromones = perceive<Pheromone>(m_direction, MathUtils::pi / 2, 8.0f);

    if(pheromones.empty()){
        return nullptr;
    }
    std::vector<float> weights;
    for(auto* pheromone : pheromones){
        weights.push_back(pheromone->getQuantity());
    }

    int index = MathUtils::randomChoose(weights);
    return pheromones.at(index);
}

// Met à jour la fourmi et dépose des phéromones (100 si elle a de la nourriture, 10 sinon)
void AntBasePheromone::update(){
    AntBase::update();
    if(bouffe>0){
        putPheromone(100);
    }else{
        putPheromone(10);
    }
}