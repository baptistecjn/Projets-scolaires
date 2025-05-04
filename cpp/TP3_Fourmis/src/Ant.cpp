#include "Ant.h"

void Ant::update(){
    AntBasePheromone::update();

    if(bouffe > 0.0f){

        // Si la fourmi a de le nourriture

        auto fourmiliereVue = LocalizedEntity::perceive<Anthill>();
        if(!fourmiliereVue.empty() && fourmiliereVue.front() == m_hill){
            this->deposerBouffe();
            demiTour();
        }else{
            cible(m_hill->getPosition());

            auto pheromone = this->choisirPheromone();
            if (pheromone != nullptr){
                cible(pheromone->getPosition());
            }
            avancer();
        }

    }else{

        // Si la fourmi n'a pas de nourriture

        auto nourritureVue = LocalizedEntity::perceive<Food>();

        if(nourritureVue.empty()){
            auto nourritureEnVue = LocalizedEntity::perceive<Food>(m_direction, MathUtils::piDiv2, 3, 1);

            if(nourritureEnVue.empty()){
                auto pheromone = this->choisirPheromone();

                if(pheromone != nullptr){
                    cible(pheromone->getPosition());
                }else{
                    auto angle = MathUtils::random((-MathUtils::pi / 10) * Timer::dt(),(MathUtils::pi / 10) * Timer::dt());
                    tourner(angle);
                }

                avancer();
            }else{
                cible(nourritureEnVue.front()->getPosition());
                avancer();
            }

        }else{
            recolter();
        }
    }
}
