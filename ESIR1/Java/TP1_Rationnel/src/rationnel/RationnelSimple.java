package rationnel;
import types.Rationnel;
import util.Outils;

public class RationnelSimple implements Rationnel{

    int num;
    int den;
    Rationnel r;

  /**
   * initialiser un rationnel à partir d'un entier : nb/1
   * @param num : valeur du numérateur
   */
    public RationnelSimple(int num){
        this.num=num;
        this.den=1;
    }

  /**
   * initialiser un rationnel avec numerateur et dénominateur
   * @param num : valeur du numérateur
   * @param den : valeur du dénominateur
   * @pre den != 0
   * @post fraction irréductible et dénominateur > 0
   */
    public RationnelSimple(int num,int den){
        if (den <= 0){
            System.out.println("erreur : le dénominateur doit être positif et non nul");
            return;
        }else{
            this.num = num/Outils.pgcd(num, den);
            this.den = den/Outils.pgcd(num, den);
        }
    }

  /**
   * initialiser un rationnel à partir d'un autre
   * @param r : rationnel à dupliquer
   */
    public RationnelSimple(Rationnel r){
        this.r=r;
    }

  /**
   * comparer (égalité) deux rationnels
   * @param r : rationnel à comparer au rationnel this
   * @return vrai si le rationnel this est égal au rationnel paramètre
   */
  public boolean equals(Rationnel r){
    return this.num == r.getNumerateur() && this.den == this.getDenominateur();
  }

  /**
   * additionner deux rationnels
   * @param r : rationnel à additionner avec le rationnel this
   * @return nouveau rationnel somme du rationnel this et du rationnel paramètre
   */
  public Rationnel somme(Rationnel r){
    int num = this.num * r.getDenominateur() + r.getNumerateur()* this.den;
    int den = this.den * r.getDenominateur();
    return new RationnelSimple(num,den);

  }

  /**
   * inverser le rationnel this
   * @return nouveau rationnel inverse du rationnel this
   * @pre numérateur != 0
   */
  public Rationnel inverse(){
    assert(num != 0);
    this.num = den;
    this.den = num;
    return new RationnelSimple(num, den);
    }
  /**
   * calculer la valeur réelle du rationnel this
   * @return valeur réelle du rationnel this
   */
  public double valeur(){
    return this.num/this.den ;
  }

  /**
   *  @return représentation affichable d'un rationnel
   */
  public String toString(){
    return this.num + "/" + this.den ;
  }

  // accesseurs
  public int getNumerateur(){
    return this.num ;
  }
  // consulter le numérateur
  public int getDenominateur(){
    return this.den ;
  } // consulter le dénominateur

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
  
