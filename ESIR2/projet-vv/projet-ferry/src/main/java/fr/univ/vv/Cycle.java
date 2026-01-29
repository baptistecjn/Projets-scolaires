package fr.univ.vv;

/**
 * Une cycle est un véhicule de longueur 2, tout terrain ou pas
 */
public class Cycle extends Vehicule
{

  // init état interne
  public Cycle(String immatriculation)
  {
	  super(1,1,immatriculation);
  }

  // représentation affichable
  public String toString()
  {
    return "Cycle : " +
      "           " +
      " passagers  : " + getPassagers() +
       " immat : " + getImmatriculation() ;
  }


  // 1.4 cloner un véhicule
  public IVehicule clone()
  {
    return new Cycle(getImmatriculation());
  }

  // 1.5 comparateur : taille des véhicules
  public int compareTo(IVehicule m_v2) {
    return this.getLongueur() - m_v2.getLongueur();
  }
}
