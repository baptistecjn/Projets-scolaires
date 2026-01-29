package fr.univ.vv;

public abstract class Vehicule implements IVehicule {

	private int longueur,nbPass;
	private String immat;
	
	public Vehicule(int capacL, int capacP, String immat)
	{
		longueur = capacL;
		nbPass = capacP;
		this.immat = immat;
	}
	
	@Override
	public int getLongueur() {
		// TODO Auto-generated method stub
		return longueur;
	}

	@Override
	public int getPassagers() {
		// TODO Auto-generated method stub
		return nbPass;
	}

	@Override
	public String getImmatriculation() {
		// TODO Auto-generated method stub
		return immat;
	}


	@Override
	public int compareTo(IVehicule v) {
		// TODO Auto-generated method stub
		return getLongueur() - v.getLongueur();
	}
	
	public abstract IVehicule clone();
	
	public abstract String toString();

}
