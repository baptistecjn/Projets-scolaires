#include "OrRule.h"

// Retourne vrai si une des règles est satisfaite
bool OrRule::condition() const{
    for(const auto& regle : m_regles){
        if(regle->condition()){
            return true;
        }
    }
    return false;
}

// Exécute l'action de la règle satisfaite
void OrRule::action(){
    for(const auto& regle : m_regles){
        if(regle->condition()){
            regle->action();
            break;
        }
    }
}