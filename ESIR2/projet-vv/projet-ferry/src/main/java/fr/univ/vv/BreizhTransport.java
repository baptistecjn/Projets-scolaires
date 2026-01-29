package fr.univ.vv;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BreizhTransport
{
	private static Map<String,Tarif> tableTarifs;
	
  public static void main(String [] aieurh)
  {
	    // créer les différents tarifs
		  TarifAuto.createSingleton(100, 0);
		  TarifAutoTT.createSingleton(350, 0);
		  TarifBus.createSingleton(200, 50);
		  TarifAmbulance.createSingleton(0, 0);
		  TarifCycle.createSingleton(20, 0);
		  
		  tableTarifs = new HashMap<String,Tarif>();
	  
	  
    Random alea = new Random();
    // créer un ferry dont la capacité est comprise :
    // - entre  50 et 100 unités de longueur
    // - entre 150 et 250 personnes
    Ferry jules = new Ferry(50 + alea.nextInt(50),
			    150 + alea.nextInt(100));
    afficher(jules, "Contenu initial du ferry");
    IVehicule unVehicule = null;
    while (jules.getMaxCapacLong() - jules.getLongueur() > 0 &&
	   jules.getMaxCapacPass() - jules.getPassagers() > 0)
      {
    	Tarif monTarif = null;
	// tirer au hasard le type de véhicule
	switch (alea.nextInt(4)) {
	case 0:
		boolean tt = alea.nextInt(3) == 0;
	  unVehicule = new Auto(alea.nextInt(5), newImmat(alea), tt);
	  if(tt)
	  {
		  monTarif = TarifAutoTT.getInstance();
	  }
	  else
	  {
		  monTarif = TarifAuto.getInstance();
	  }
	  break;

	case 1:
	  // Bus : longueur, nombre de personnes
	  unVehicule = new Bus(10 + alea.nextInt(10), 20 + alea.nextInt(60), newImmat(alea));
	  monTarif = TarifBus.getInstance();
	  break;

	case 2:
	  // ambulance : nombre de personnes, tout terrain ?
	  unVehicule = new Ambulance(alea.nextInt(5), newImmat(alea), alea.nextInt(3) == 0);
	  monTarif = TarifAmbulance.getInstance();
	  break;

	case 3:
	  // cycle
	  unVehicule = new Cycle(newImmat(alea));
	  monTarif = TarifCycle.getInstance();
	  break;

	}

	System.out.println("\nAjout de " + unVehicule);
	boolean ok = jules.ajouter(unVehicule);
	if (! ok) {
	  System.err.println("*** Impossible d'ajouter " + unVehicule + "***");
	}
	else {
		tableTarifs.put(unVehicule.getImmatriculation(), monTarif);
	  afficher(jules, "\nContenu du ferry");
	}
      }
    jules.trier();
    afficher(jules, "\nContenu du ferry après tri par défaut");

    Comparator<IVehicule> compLongueur = new ComparateurLongueur();
    Comparator<IVehicule> compTarif = new ComparateurTarif();

    // trier le conteneur par longueur décroissante
    jules.trier(compLongueur.reversed());
    afficher(jules, "\nContenu du ferry après tri par longueur DÉcroissante");

    // trier le conteneur avec ComparateurTarif
    jules.trier(compTarif);
    afficher(jules, "\nContenu du ferry après tri par tarif croissant");

    jules.trier(compTarif.reversed());
    afficher(jules, "\nContenu du ferry après tri par tarif DÉcroissant");

    // trier par longueur croissante et tarif décroissant
    jules.trier(new ComparateurMulti(
				     compLongueur,
				     compTarif.reversed()
				     ));
    afficher(jules, "\nContenu du ferry après tri par longueur croissante et tarif décroissant");

    // inverser le sens du tri précédent
    jules.trier(new ComparateurMulti(
				     compLongueur.reversed(),
				     compTarif
				     ));
    afficher(jules, "\nContenu du ferry après inversion du sens de tri précédent");

    // trier par numéro d'immatriculation avec une lambda
    jules.trier((v1, v2)
		->
		(int) (v1.getImmatriculation().compareTo(v2.getImmatriculation()))
		);
    afficher(jules, "\nContenu du ferry après tri par immatriculation avec une lambda");

    // trois clés de tri
    jules.trier(new ComparateurMulti(new ComparateurMulti(compLongueur, compTarif),
				     (v1, v2)
				     ->
				     (int) (v1.getImmatriculation().compareTo(v2.getImmatriculation()))
				     ));
    afficher(jules, "\nContenu du ferry après tri par longueur, tarif, immat");
  }
  
  public static int calculerTarif(IVehicule v)
  {
	  Tarif leTarif = tableTarifs.get(v.getImmatriculation());
	  return leTarif.calculerTarif(v);
  }

  // afficher un ferry
  static void afficher(Ferry f, String msg)
  {
    System.out.println(msg + "\n" + f);
  }

  // générer un numéro d'immatriculation
  // @param : générateur de nombres aléatoires
  static String newImmat(Random alea)
  {
    String lettres = "AA-000-AA";
    int [] nombre = { 26, 26, 1, 10, 10, 10, 1, 26, 26 };
    String immat = "";
    for (int i = 0; i < lettres.length(); ++i)
    {
      immat += (char) (lettres.charAt(i) + alea.nextInt(nombre[i]));
    }
    return immat;
  }

  public static void enregistrerTarifTest(String immat, Tarif t) {
        if (tableTarifs == null) {
            tableTarifs = new java.util.HashMap<>();
        }
        tableTarifs.put(immat, t);
    }
}
