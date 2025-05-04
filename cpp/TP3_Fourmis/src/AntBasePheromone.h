#ifndef ANTBASEPHEROMONE_H
#define ANTBASEPHEROMONE_H

#include "Pheromone.h"
#include "Environment.h"
#include "Agent.h"
#include "Anthill.h"
#include "Food.h"
#include "Timer.h"
#include "MathUtils.h"
#include "Renderer.h"
#include <vector>
#include "AntBase.h"


class AntBasePheromone : public AntBase{
public:
    AntBasePheromone(Environment* environment, const Vector2<float>& position, Anthill* hill, Vector2<float> direction) : AntBase(environment, position, hill, direction){}

    void putPheromone(float q);
    void update() override;
    Pheromone* choisirPheromone() const;
};

#endif
