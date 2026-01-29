package fr.univ.vv;

import java.util.Comparator;

public abstract class Compar implements Comparator<IVehicule> {

	private boolean croissant;
	
	public Compar(boolean c)
	{
		croissant = c;
	}
	
	public Compar()
	{
		croissant = true;
	}
	
	@Override
	public int compare(IVehicule arg0, IVehicule arg1) {
		// TODO Auto-generated method stub
		if(croissant)
		{
			return doCompare(arg0,arg1);
		}
		else
		{
			return doCompare(arg1,arg0);
		}
	}

	public abstract int doCompare(IVehicule v1, IVehicule v2);
}
