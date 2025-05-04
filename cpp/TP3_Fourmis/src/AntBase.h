#ifndef ANTBASE_H
#define ANTBASE_H

#include "Agent.h"
#include "Anthill.h"
#include "Food.h"
#include "Timer.h"
#include "MathUtils.h"
#include "Renderer.h"
#include <vector>


class AntBase : public Agent{

    protected:
        float vitesse =1.0f;
        Vector2<float> m_direction;
        int duree_vie;
        Anthill* m_hill;
        
    public:

        float bouffe = 0.0f;

        AntBase(Environment* environment, const Vector2<float>& position, Anthill* hill,Vector2<float> direction):
        Agent(environment, position, 1.0f), m_hill(hill), m_direction(direction), duree_vie(MathUtils::random(1000,2500)){}


        void update() override;
        void avancer();
        void tourner(float angle);
        void demiTour();
        void cible(const Vector2<float>& pos_cible);
        void deposerBouffe();
        void recolter();
        
        const Vector2<float>& getDirection() const{ 
            return m_direction;
        }
        Anthill* getHill() const{
            return m_hill;
        }
};

#endif