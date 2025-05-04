#ifndef ORRULE_H
#define ORRULE_H

#include "AbstractRule.h"
#include <vector>
#include <memory>

class OrRule : public AbstractRule{
        
    protected:
        std::vector<std::shared_ptr<AbstractRule>> m_regles;

    public:
        OrRule(const std::vector<std::shared_ptr<AbstractRule>>& regles) : m_regles(regles){}

        bool condition() const override;
        void action() override;
        const std::vector<std::shared_ptr<AbstractRule>>& getRules() const{
            return m_regles;
        }


};

#endif