package fr.univ.vv;

public class Auto extends Vehicule {
	
	private Boolean isToutTerrain;
	
	public Auto(int nbP)
	{
		super(2,nbP,"AA-000-AA");
		isToutTerrain = false;
	}	
	
	public Auto(int nbP, Boolean TT)
	{
		super(2,nbP,"AA-000-AA");
		isToutTerrain = TT;
	}
	
	
	public Auto (int nbP, String immat)
	{
		super(2,nbP,immat);
		isToutTerrain = false;
	}
	
	public Auto (int nbP, String immat, Boolean TT)
	{
		super(2,nbP,immat);
		isToutTerrain = TT;
	}
	
	public Boolean isTT()
	{
		return isToutTerrain;
	}


	public IVehicule clone()
	{
		return new Auto(getPassagers(),getImmatriculation(),isTT());
	}
	
	public String toString()
	{
		String TT;
		if(isToutTerrain)
			TT = "Il est tout terrain !";
		else
			TT = "Il n'est pas tout terrain...";
		return ("Véhicule "+getImmatriculation()+" : "+getPassagers()+" personnes et de longueur "+getLongueur()+".\n"+TT);
	}

}
