package fr.univ.vv;

public class Bus extends Vehicule {	
	
	public Bus(int longueur, int nbPass, String immat)
	{
		super(longueur,nbPass,immat);
	}
	
	public IVehicule clone()
	{
		return new Bus(getLongueur(), getPassagers(), getImmatriculation());
	}
	
	public String toString()
	{
		return ("Bus "+getImmatriculation()+" : "+getPassagers()+" personnes et de longueur "+getLongueur()+".");
	}

}
