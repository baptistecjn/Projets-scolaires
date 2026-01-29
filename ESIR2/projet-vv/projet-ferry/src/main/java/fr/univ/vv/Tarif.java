package fr.univ.vv;

public abstract class Tarif {
	
	private static int tarifP = 1;
	private int tarifFixe;
	private int tarifVariable;

	protected Tarif(int tFixe, int tVar)
	{
		tarifFixe = tFixe;
		tarifVariable = tVar;
	}
	
	public static int getTarifPass()
	{
		return tarifP;
	}
	
	public int getTarifFixe()
	{
		return tarifFixe;
	}
	
	public int getTarifVariable()
	{
		return tarifVariable;
	}
	
	static void setTarifPass(int tPass)
	{
		tarifP = tPass;
	}
	
	void setTarifFixe(int tFixe)
	{
		tarifFixe = tFixe;
	}
	
	void setTarifVariable(int tVar)
	{
		tarifVariable = tVar;
	}
	
	public abstract int calculerTarif(IVehicule v);
}
