package fr.univ.vv;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ComparateurTarifTest {

    @Test
    void testCompareTroisCas() {
        ComparateurTarif comp = new ComparateurTarif();
        
        // On mocke les véhicules
        IVehicule v1 = mock(IVehicule.class);
        IVehicule v2 = mock(IVehicule.class);
        
        Tarif t1 = mock(Tarif.class);
        Tarif t2 = mock(Tarif.class);
        
        when(v1.getImmatriculation()).thenReturn("V1");
        when(v2.getImmatriculation()).thenReturn("V2");
        
        when(t1.calculerTarif(v1)).thenReturn(10);
        when(t2.calculerTarif(v2)).thenReturn(20);
        
        // Enregistrement
        BreizhTransport.enregistrerTarifTest("V1", t1);
        BreizhTransport.enregistrerTarifTest("V2", t2);

        // Cas 1 : v1 < v2 (10 < 20) -> doit retourner < 0
        assertTrue(comp.compare(v1, v2) < 0, "10€ devrait être < 20€");

        // Cas 2 : v2 > v1 (20 > 10) -> doit retourner > 0
        assertTrue(comp.compare(v2, v1) > 0, "20€ devrait être > 10€");

        // Cas 3 : égalité
        when(t2.calculerTarif(v2)).thenReturn(10);
        assertEquals(0, comp.compare(v1, v2), "10€ devrait être égal à 10€");
    }
}