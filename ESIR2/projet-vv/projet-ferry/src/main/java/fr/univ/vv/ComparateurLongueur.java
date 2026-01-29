package fr.univ.vv;


public class ComparateurLongueur extends Compar
{

  public ComparateurLongueur(boolean c)
    {
      super(c);
    }
  public ComparateurLongueur()
    {
	  super();
    }
  
@Override
public int doCompare(IVehicule v1, IVehicule v2) {
	// TODO Auto-generated method stub
	return v1.getLongueur() - v2.getLongueur();
}
}
