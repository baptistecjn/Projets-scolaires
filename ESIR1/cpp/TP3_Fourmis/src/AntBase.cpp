#include "AntBase.h"


void AntBase::update(){
    duree_vie -= Timer::dt();
    if (duree_vie <= 0){
        setStatus(Agent::destroy);
        return;
    }

    Renderer::Color color = (bouffe > 0) ? Renderer::Color(128, 255, 128, 255) : Renderer::Color(255, 255, 255, 255);
    Renderer::getInstance()->drawCircle(getPosition(),1.0f, color);
}

// Fait avancer la fourmi
void AntBase::avancer(){
    translate(m_direction * vitesse * Timer::dt());
}

// Fait tourner la fourmi d'un angle donné en paramètre
void AntBase::tourner(float angle){
    m_direction = m_direction.rotate(angle);
}

// Fait faire demi-tour à la fourmi
void AntBase::demiTour(){
    m_direction = m_direction.rotate(MathUtils::pi);
}

// Fait se diriger la fourmi vers la cible donnée en paramètre
void AntBase::cible(const Vector2<float>& cible){
    m_direction = (cible-getPosition()).normalized();
}

// Permet à la fourmi de déposer de la nourriture à la fourmilière qui lui est associé
void AntBase::deposerBouffe(){
    if(bouffe>0){
        m_hill->depositFood(bouffe);
        bouffe=0;
    }
}

// Permet à la fourmi de récolter de la nourriture
void AntBase::recolter(){
    if(5.0f-bouffe>0){
        std::vector<Food*> proche = getEnvironment()->perceive<Food>(getPosition(), m_direction, MathUtils::pi / 2, 3.0f);
        for (Food* f : proche) {
            float canCarry = 5.0f - bouffe;
            float taken = f->collectFood(canCarry);
            bouffe += taken;
            if (bouffe >= 5.0f) break;
        }
    }
}