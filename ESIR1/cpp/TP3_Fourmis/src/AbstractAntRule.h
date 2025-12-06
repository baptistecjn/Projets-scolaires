#ifndef ABSTRACT_ANT_RULE_H
#define ABSTRACT_ANT_RULE_H

#include "AbstractRule.h"
#include "AntBasePheromone.h"

class AbstractAntRule : public AbstractRule {
    protected:
        AntBasePheromone* m_ant;

    public:
        AbstractAntRule(AntBasePheromone* ant) : m_ant(ant) {}
        virtual ~AbstractAntRule() = default;

        virtual bool condition() const override = 0;
        virtual void action() override = 0;
};

#endif