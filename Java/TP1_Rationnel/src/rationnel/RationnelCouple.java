package rationnel;


import types.Rationnel;
import util.Outils;
import util.Couple;

public class RationnelCouple implements Rationnel {

    Couple<Integer,Integer> couple;

    /**
     * initialiser un rationnel à partir d'un entier : nb/1
     * @param num : valeur du numérateur
     */
    public RationnelCouple(int num){
        this.couple = new Couple<>(num,1);
    }

    /**
     * initialiser un rationnel avec numerateur et dénominateur
     * @param num : valeur du numérateur
     * @param den : valeur du dénominateur
     * @pre den != 0
     * @post fraction irréductible et dénominateur > 0
     */
    public RationnelCouple(int num,int den){
        if (den <= 0){
            System.out.println("erreur : le dénominateur doit être positif et non nul");

        }else{
            int pgcd = Outils.pgcd(num, den);
            this.couple = new Couple<>(num / pgcd, den / pgcd);
        }
    }

    /**
     * initialiser un rationnel à partir d'un autre
     * @param r : rationnel à dupliquer
     */
    public RationnelCouple(Rationnel r){
        this.couple = new Couple<>(r.getNumerateur(),r.getDenominateur());
    }

    /**
     * comparer (égalité) deux rationnels
     * @param r : rationnel à comparer au rationnel this
     * @return vrai si le rationnel this est égal au rationnel paramètre
     */
    public boolean equals(Rationnel r){
        return this.couple.getFirst() == r.getNumerateur() && this.couple.getSecond() == r.getDenominateur();

    }


    /**
     * additionner deux rationnels
     * @param r : rationnel à additionner avec le rationnel this
     * @return nouveau rationnel somme du rationnel this et du rationnel paramètre
     */
    public Rationnel somme(Rationnel r) {
        int num = this.couple.getFirst() * r.getDenominateur() + r.getNumerateur() * this.couple.getSecond();
        int den = this.couple.getSecond() * r.getDenominateur();
        return new RationnelCouple(num, den);
    }

    /**
     * inverser le rationnel this
     * @return nouveau rationnel inverse du rationnel this
     * @pre numérateur != 0
     */
    public Rationnel inverse(){
        assert(this.couple.getSecond() != 0);
        return new RationnelCouple(this.couple.getSecond(),this.couple.getFirst());
    }

    /**
     * calculer la valeur réelle du rationnel this
     * @return valeur réelle du rationnel this
     */
    public double valeur(){
        return (double) this.couple.getFirst() /this.couple.getSecond() ;
    }

    /**
     *  @return représentation affichable d'un rationnel
     */
    public String toString(){
        return this.couple.getFirst() + "/" + this.couple.getSecond() ;
    }

    // accesseurs
    public int getNumerateur(){
        return this.couple.getFirst() ;
    }

    public int getDenominateur(){
        return this.couple.getSecond() ;
    }

    // méthode de l'interface Comparable<Rationnel>
    // comparaison ordonnée du rationnel this et du rationnel autre
    public int compareTo(Rationnel autre){
        if (this.valeur() < autre.valeur()){
            return -1;
        }
        if(this.valeur() == autre.valeur()){
            return 0;
        }else{
            return 1;
        }
    }
}
