package fr.univ.vv;

public class TarifAuto extends Tarif {

	private static Tarif t=null;
	
	protected TarifAuto(int tFixe, int tVar) {
		super(tFixe, tVar);
		// TODO Auto-generated constructor stub
	}
	
	public static Tarif createSingleton(int tFixe, int tVar)
	{
		if (t==null)
		{
			t = new TarifAuto(tFixe,tVar);
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
		return getTarifFixe() + getTarifPass()*v.getPassagers();
	}

}
