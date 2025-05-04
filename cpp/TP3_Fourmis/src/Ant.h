#ifndef ANT_H
#define ANT_H

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
#include "AntBasePheromone.h"

class Ant : public AntBasePheromone{
public:
    Ant(Environment* env, const Vector2<float>& position, Anthill* hill, const Vector2<float>& direction) : AntBasePheromone(env, position, hill, direction){}

    void update() override;
};

#endif
