#ifndef SILLYANT_H
#define SILLYANT_H

#include "Agent.h"
#include "Anthill.h"
#include "Food.h"
#include "Timer.h"
#include "MathUtils.h"
#include "Renderer.h"
#include <vector>
#include "AntBase.h"

class SillyAnt : public AntBase{
    public :
        SillyAnt(Environment* environnement, const Vector2<float>& position, Anthill* hill) : AntBase(environnement, position, hill, Vector2<float>::random()){}

        void update() override;
};

#endif