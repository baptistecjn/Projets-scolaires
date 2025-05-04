#ifndef ABSTRACTRULE_H
#define ABSTRACTRULE_H

class AbstractRule {
    public:
        virtual ~AbstractRule() = default;
        virtual bool condition() const = 0;
        virtual void action() = 0;
};

#endif
