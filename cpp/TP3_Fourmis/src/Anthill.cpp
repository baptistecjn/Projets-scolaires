#include "Anthill.h"
#include "Agent.h"
#include "MathUtils.h"
#include "Food.h"
#include "Renderer.h"


void Anthill::update(){
    Renderer::getInstance()->drawCircle(getPosition(), 10, Renderer::Color(0, 0, 255, 255));

}
void Anthill::depositFood(float quantity){
    stockBouffe+=quantity;
}
