package fr.univ.vv;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * classe FerryIntegrationTest
 * tests d'intégration
 * objectif : vérifier la cohérence du conteneur et du contenu
 */
class FerryIntegrationTest {

    @Test
    void testChargementMixte() {
        Ferry ferry = new Ferry(100, 100);

        Auto voitureFamille = new Auto(4);
        
        Bus busScolaire = new Bus(15, 30, "BUS-001");

        boolean ajoutAuto = ferry.ajouter(voitureFamille);
        boolean ajoutBus = ferry.ajouter(busScolaire);

        assertTrue(ajoutAuto, "L'auto doit pouvoir entrer");
        assertTrue(ajoutBus, "Le bus doit pouvoir entrer");

        assertEquals(17, ferry.getLongueur(), "La longueur totale utilisée doit être la somme des véhicules réels");
        
        assertEquals(34, ferry.getPassagers(), "Le nombre de passagers doit être la somme réelle");
    }

    @Test
    void testTriReel() {
        Ferry ferry = new Ferry(100, 100);
        
        Bus grandBus = new Bus(20, 50, "ZZ-999-ZZ");
        Auto petiteAuto = new Auto(1, "AA-000-AA");

        ferry.ajouter(grandBus);
        ferry.ajouter(petiteAuto);
        ferry.trier(new ComparateurLongueur());

        assertDoesNotThrow(() -> ferry.trier());
        assertEquals(22, ferry.getLongueur());
    }
}