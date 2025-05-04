#ifndef ANTHILL_H
#define ANTHILL_H

#include "Agent.h"
#include "Environment.h"
#include "Vector2.h"

class Anthill : public Agent{
    private:
        float stockBouffe;
    public:
        Anthill(Environment* environment, const Vector2<float>& position) : Agent(environment, position, 10.0f), stockBouffe(0.0f){}

        void update() override;
        void depositFood(float quantity);
};

#endif // ANTHILL_H

    