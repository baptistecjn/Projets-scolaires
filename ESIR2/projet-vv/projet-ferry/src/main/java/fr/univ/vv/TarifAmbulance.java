package fr.univ.vv;

public class TarifAmbulance extends Tarif {

	private static Tarif t = null;
	
	protected TarifAmbulance(int fixe, int var)
	{
		super(fixe,var);
	}
	
	public static Tarif createSingleton(int fixe, int var)
	{
		if (t == null)
		{
			t = new TarifAmbulance(fixe,var);
		}
		return t;
	}
	
	public static Tarif getInstance()
	{
		return t;
	}
	
	@Override
	public int calculerTarif(IVehicule v) {
		// TODO Auto-generated method stub
		return 0;
	}

}
