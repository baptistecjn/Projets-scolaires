#ifndef ANT_WITH_RULE_H
#define ANT_WITH_RULE_H

#include "AntBasePheromone.h"
#include "Anthill.h"
#include "Food.h"
#include "AbstractAntRule.h"
#include "OrRule.h"
#include "MathUtils.h"
#include <vector>
#include <memory>

class AntWithRule : public AntBasePheromone{
    public:
        AntWithRule(Environment* env, const Vector2<float>& pos, Anthill* hill) : AntBasePheromone(env, pos, hill, Vector2<float>(1.0f,0.0f)){
            setupRules();
        }

        void update() override{

            if(!rules){
                std::cerr << "rules est null\n";
                return;
            }
            for(auto& r : rules->getRules()){
                if(!r){
                    std::cerr << "Règle null détectée\n";
                    continue;
                }
                if(r->condition()){
                    r->action();
                    break;
                }
            }

            if(bouffe > 0.0f){
                putPheromone(100);
            }else{
                putPheromone(10);
            }             
            Renderer::Color color = (bouffe > 0) ? Renderer::Color(128, 255, 128, 255) : Renderer::Color(255, 255, 255, 255);
            Renderer::getInstance()->drawCircle(getPosition(),1.0f, color);
        }

    private:
        std::shared_ptr<OrRule> rules;

        void setupRules() {
                std::vector<std::shared_ptr<AbstractRule>> v{
                std::make_shared<RamasserNouriture>(this),
                std::make_shared<AllerSurBouffeEnVue>(this),
                std::make_shared<SuivrePheroEnVue>(this),
                std::make_shared<DeplacementRandom>(this),
                std::make_shared<DeposerBouffeF>(this),
                std::make_shared<RetourFourmilière>(this)
            };
            rules = std::make_shared<OrRule>(v);
        }

        // La fourmi recolte de la nourriture si elle est sur une Food et si elle ne possède pas deja de la nourriture
        class RamasserNouriture : public AbstractAntRule {
            public:
                RamasserNouriture(AntBasePheromone* a): AbstractAntRule(a) {}

                bool condition() const override {
                    return m_ant->bouffe == 0.0f
                        && !m_ant->perceive<Food>().empty();
                }
                void action() override {
                    m_ant->recolter();
                }
        };

        // La fourmi se dirige vers la Food en vue si elle ne possède pas de nourriture
        class AllerSurBouffeEnVue : public AbstractAntRule {
            public:
                AllerSurBouffeEnVue(AntBasePheromone* a) : AbstractAntRule(a){}

                bool condition() const override{
                    auto inView = m_ant->perceive<Food>(m_ant->getDirection(), MathUtils::piDiv2, 3, 1);
                    return m_ant->bouffe == 0.0f && !inView.empty();
                }
                void action() override{
                    auto inView = m_ant->perceive<Food>(m_ant->getDirection(), MathUtils::piDiv2, 3, 1);
                    m_ant->cible(inView.front()->getPosition());
                    m_ant->avancer();
                }
        };

        // Si la fourmi n'a pas de nourriture elle suit la pheromone visible
        class SuivrePheroEnVue : public AbstractAntRule{
            public:
                SuivrePheroEnVue(AntBasePheromone* a) : AbstractAntRule(a){}

                bool condition() const override {
                    auto phero = m_ant->perceive<Pheromone>(m_ant->getDirection(), MathUtils::piDiv2, 3, 1);
                    return m_ant->bouffe == 0.0f && !phero.empty();
                }
                void action() override{
                    Pheromone* p = m_ant->choisirPheromone();
                    if (p) {
                        m_ant->cible(p->getPosition());
                        m_ant->avancer();
                    }
                }
        };

        // La fourmi se déplace au hasard si elle n'a pas de nourriture, ne voit pas de Food ni de Pheromone
        class DeplacementRandom : public AbstractAntRule{
            public:
                DeplacementRandom(AntBasePheromone* a) : AbstractAntRule(a){}

                bool condition() const override{
                    auto food = m_ant->perceive<Food>(m_ant->getDirection(), MathUtils::piDiv2, 3, 1);
                    auto phero  = m_ant->perceive<Pheromone>(m_ant->getDirection(), MathUtils::piDiv2, 3, 1);
                    return m_ant->bouffe == 0.0f && food.empty() && phero.empty();
                }
                void action() override{
                    float angle = MathUtils::random(-MathUtils::pi/10 * Timer::dt(), MathUtils::pi/10 * Timer::dt());
                    m_ant->tourner(angle);
                    m_ant->avancer();
                }
        };

        // Si la fourmi possède de la nourriture et est sur la fourmilière, elle la dépose
        class DeposerBouffeF : public AbstractAntRule{
            public:
                DeposerBouffeF(AntBasePheromone* a) : AbstractAntRule(a){}

                bool condition() const override{
                    auto hills = m_ant->perceive<Anthill>();
                    bool hillHere = !hills.empty() && hills.front() == m_ant->getHill();
                    return m_ant->bouffe > 0.0f && hillHere;
                }
                void action() override{
                    m_ant->deposerBouffe();
                    m_ant->demiTour();
                }
        };

        // Si la fourmi possède de la nourriture elle retourne à sa foumilière
        class RetourFourmilière : public AbstractAntRule{
            public:
                RetourFourmilière(AntBasePheromone* a) : AbstractAntRule(a){}
                bool condition() const override{
                    auto hills = m_ant->perceive<Anthill>();
                    bool hillHere = !hills.empty() && hills.front() == m_ant->getHill();
                    return m_ant->bouffe > 0.0f && !hillHere;
                    
                }
                void action() override{
                    m_ant->cible(m_ant->getHill()->getPosition());
                    Pheromone* p = m_ant->choisirPheromone();
                    if (p) {
                        m_ant->cible(p->getPosition());
                    }
                    m_ant->avancer();
                }
        };
};

#endif
