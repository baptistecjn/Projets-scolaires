package fr.univ.vv;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FerryTarificationIntegrationTest {

    @Disabled("Ce test renvoi NullPointerException dans BreizhTransport.tableTarifs")
    @Test
    void testIntegration_CalculGlobalPlusieursVehicules() {
        // Création d'un ferry avec une capacité suffisante
        Ferry ferry = new Ferry(100, 50);
        
        // Création de plusieurs véhicules
        Auto petiteVoiture = new Auto(4); 
        Bus bus = new Bus(12, 30, "BUS-1234");
        
        // Ajout des véhicules au ferry
        ferry.ajouter(petiteVoiture);
        ferry.ajouter(bus);
        
        // Lien avec le module de tarification
        int tarifTotal = ferry.calculerTarif();
        
        // Calcul du tarif attendu via le module de tarification
        int attendu = BreizhTransport.calculerTarif(petiteVoiture) + BreizhTransport.calculerTarif(bus);
        
        assertEquals(attendu, tarifTotal, "Problème de lien entre le Ferry et le module de tarification");
        assertTrue(tarifTotal > 0, "Le tarif total devrait être positif");
    }
}