package fr.univ.vv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Ferry {
	
	private final int maxCL;
	private int capacL;
	private final int maxCP;
	private int capacP;
	List<IVehicule> trans;
	
	public Ferry(int CapacMaxLong, int CapacMaxPass)
	{
		maxCL = CapacMaxLong;
		maxCP = CapacMaxPass;
		capacL = 0;
		capacP = 0;
		trans = new ArrayList<IVehicule>();
	}
	
	public int getMaxCapacLong()
	{
		return maxCL;
	}
	
	public int getMaxCapacPass()
	{
		return maxCP;
	}
	
	public int getLongueur()
	{
		return capacL;
	}
	
	public int getPassagers()
	{
		return capacP;
	}
	
	public Boolean ajouter(IVehicule v)
	{
		int newCapacL = v.getLongueur()+capacL;
		int newCapacP = v.getPassagers()+capacP;
		if( newCapacL <= maxCL && newCapacP <= maxCP)
		{
			trans.add(v.clone());
			capacL = newCapacL;
			capacP = newCapacP;
			return true;
		}
		return false;
	}
	
	public int calculerTarif()
	{
		int tarif = 0;
		Iterator<IVehicule> it = trans.iterator();
		while(it.hasNext())
		{
			tarif += BreizhTransport.calculerTarif(it.next());
		}
		return tarif;
	}
	
	public void trier()
	{
		Collections.sort(trans);
	}
	
	public void trier(Comparator<IVehicule> comp)
	{
		Collections.sort(trans, comp);
	}
	
	public String toString()
	{
		 StringBuilder aff = new StringBuilder();
		    aff.append("-----------------------------------------------------------\n");
		    Iterator<IVehicule> itv = trans.iterator();
		    while (itv.hasNext()) {
		      IVehicule courant = itv.next();
		      aff.append(courant + "\n");
		    }
		    aff.append("-----------------------------------------------------------\n");
		    aff.append("Longueur disponible           : " + (maxCL - capacL) + "\n");
		    aff.append("Places   disponibles          : " + (maxCP - capacP) + "\n");
		    aff.append("Montant du contenu du ferry   : " + calculerTarif() + "\n");
		    return aff.toString();
	}
}
