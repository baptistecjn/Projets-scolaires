#ifndef AGENT_H
#define AGENT_H

#include "Environment.h"
#include <set>



class Agent : public Environment::LocalizedEntity{

    protected:
        
        static std::set<Agent*> agents;

    public : 
        enum Status { running, destroy };
        Status status;

        Agent(Environment* environment, const Vector2<float>& position, float rayon = Environment::LocalizedEntity::defaultRadius()):
        Environment::LocalizedEntity(environment, position, rayon),status(running){
            agents.insert(this);
        }

        virtual void update() = 0;

        Status getStatus()const;
        void setStatus(Status nouveau);
        static void simulate();
        static void finalize();


};

#endif //AGENT_H