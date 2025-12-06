#include "Agent.h"
#include "MathUtils.h"
#include "Food.h"
#include "Renderer.h"

// Retourne la quantité de nourriture
float Food::getFoodQuantity() const{
    return m_qte;
}

// Retourne la quantité de nourriture collecté et met à jour la qauntité de nourriture
float Food::collectFood(float nv_qte){
    float retour;
    if (nv_qte >= m_qte) {
        retour = m_qte;
        m_qte = 0;
    } else {
        retour = nv_qte;
        m_qte -= nv_qte;
    }
    return retour;
}

// Met à jour la nourriture en la supprimant si elle est vide ou en ajustant le rayon
void Food::update(){
    if(m_qte==0){
        setStatus(destroy);
    }
    setRadius(MathUtils::circleRadius(m_qte));

    Renderer::getInstance()->drawCircle(getPosition(), getRadius(), Renderer::Color(154, 235, 38, 255));
}