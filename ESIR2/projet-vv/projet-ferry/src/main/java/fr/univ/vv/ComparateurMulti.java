package fr.univ.vv;

import java.util.Comparator;

public class ComparateurMulti implements Comparator<IVehicule>
{
  private Comparator<IVehicule> c1, c2;

  public ComparateurMulti(
			  Comparator<IVehicule> c1,
			  Comparator<IVehicule> c2
			  )
    {
      this.c1 = c1; 
      this.c2 = c2;
    }

  public int compare(IVehicule v1, IVehicule v2)
  {
    int delta =  c1.compare(v1, v2);
    if (delta == 0) {
      delta = c2.compare(v1, v2);
    }
    return delta;
  }
}
