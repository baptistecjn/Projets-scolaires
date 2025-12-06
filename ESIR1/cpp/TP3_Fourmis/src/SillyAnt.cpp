#include "SillyAnt.h"

void SillyAnt::update(){
    duree_vie -= Timer::dt();
    if(duree_vie <= 0){
        setStatus(Agent::destroy);
        return;
    }

    if(bouffe == 0.0f){
        float angle = MathUtils::random(-MathUtils::pi / 10.0f * Timer::dt(), MathUtils::pi / 10.0f * Timer::dt());
        tourner(angle);
        avancer();

        auto food = perceive<Food>();
        if(!food.empty()){
            recolter();
        }

    }else{
        cible(m_hill->getPosition());
        avancer();

        auto hill = perceive<Anthill>();
        if(!hill.empty()){
            deposerBouffe();
        }
    }

    Renderer::Color color = (bouffe > 0) ? Renderer::Color(128, 255, 128, 255) : Renderer::Color(255, 255, 255, 255);
    Renderer::getInstance()->drawCircle(getPosition(),1.0f, color);
}
