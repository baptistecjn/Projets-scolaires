#ifndef PHEROMONE_H
#define PHEROMONE_H

#include <algorithm>
#include <SDL2/SDL.h>
#include "Agent.h"
#include "Timer.h"
#include "Renderer.h"

class Pheromone : public Agent{
    private:
        float qte;

    public:
        Pheromone(Environment* environment, const Vector2<float>& position, float initialQte) : Agent(environment, position), qte(initialQte){}

        float getQuantity() const;

        void addQuantity(float q);

        void update() override;
};


#endif