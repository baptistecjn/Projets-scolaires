package fr.univ.vv;


public class ComparateurTarif extends Compar
{
  
  public ComparateurTarif(boolean c)
    {
      super(c);
    }
  public ComparateurTarif()
  {
	  super();
  }

@Override
public int doCompare(IVehicule v1, IVehicule v2) {
	// TODO Auto-generated method stub
	return (int) (BreizhTransport.calculerTarif(v1) - BreizhTransport.calculerTarif(v2));
}
}
