#include "Agent.h"

// Retourne le status actuel de l'agent
Agent::Status Agent::getStatus() const{
    return status;
}

// Modifie le status actuel de l'agent (soit destroy, soit running)
void Agent::setStatus(Status nouveau){
    status = nouveau;
}

std::set<Agent*> Agent::agents;

// Gère le status des agents
void Agent::simulate(){
    auto it = agents.begin();

    while (it != agents.end()){
        Agent* agent = *it;

        if (agent->getStatus() == destroy){
            delete agent;
            it = agents.erase(it);
        } else {
            agent->update();
            it++;
        }
    }
}

// Supprime les agents
void Agent::finalize(){
    for (auto it = agents.begin(); it != agents.end(); ){
        delete *it;
        it = agents.erase(it);
    }
}

