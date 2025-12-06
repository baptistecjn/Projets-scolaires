#ifndef FOOD_H
#define FOOD_H

#include "Agent.h"
#include "MathUtils.h"

class Food : public Agent{
    private:
        float m_qte;
        
    public:
        Food(Environment* environment, const Vector2<float>& position, float qte) : Agent(environment, position, MathUtils::circleRadius(qte)), m_qte(qte){}
        
        float getFoodQuantity() const;

        float collectFood(float nv_qte);

        void update();
};

#endif