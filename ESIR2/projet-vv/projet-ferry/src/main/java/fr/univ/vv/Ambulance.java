package fr.univ.vv;

public class Ambulance extends Auto
{
  public Ambulance(int p_passagers, String p_immat, boolean p_tt)
  {
    super(p_passagers, p_immat, p_tt);
  }
  public Ambulance(int p_passagers, String p_immat)
  {
    super(p_passagers, p_immat, false);
  }

  // représentation affichable
  public String toString()
  {
    return "Ambul : " +
      (isTT() ? "toutTerrain" : "normal     ") +
      " passagers  : " + getPassagers() +
      " immat : " + getImmatriculation();
  }

  // 1.4 cloner un véhicule
  public IVehicule clone()
  {
    return new Ambulance(getPassagers(), getImmatriculation(), isTT());
  }

}
