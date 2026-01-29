package fr.univ.vv;

public class VehiculeStub implements IVehicule {
    private int longueur;
    private int passagers;

    public VehiculeStub(int l, int p) { this.longueur = l; this.passagers = p; }
    @Override public int getLongueur() { return longueur; }
    @Override public int getPassagers() { return passagers; }
    @Override public String getImmatriculation() { return "STUB-01"; }
    @Override public IVehicule clone() { return new VehiculeStub(longueur, passagers); }
    @Override public int compareTo(IVehicule v) { return 0; }
}