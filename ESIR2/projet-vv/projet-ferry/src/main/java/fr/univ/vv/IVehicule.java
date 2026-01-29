package fr.univ.vv;

public interface IVehicule extends Comparable<IVehicule>{

	//determiner la longueur d'un véhicule
	public int getLongueur();
	
	//déterminer le nombre de passagers
	public int getPassagers();
	
	//connaitre l'immatriculation
	public String getImmatriculation();
	
	//représentation affichable
	public String toString();


	public IVehicule clone();
	
	public int compareTo(IVehicule v);
}
