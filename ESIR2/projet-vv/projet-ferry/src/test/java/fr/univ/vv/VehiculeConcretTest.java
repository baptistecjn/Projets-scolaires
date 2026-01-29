package fr.univ.vv;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehiculeConcretTest {

    @Test
    void testAutoFullCoverage() {
        // Test de tous les constructeurs
        Auto a1 = new Auto(5);
        Auto a2 = new Auto(5, true);
        Auto a3 = new Auto(5, "CC-333-CC");
        Auto a4 = new Auto(5, "DD-444-DD", true);

        // Test des méthodes spécifiques
        assertFalse(a1.isTT());
        assertTrue(a2.isTT());
        
        // Test toString
        assertNotNull(a1.toString());
        assertNotNull(a2.toString());
        
        IVehicule clone = a2.clone();
        assertTrue(((Auto)clone).isTT());
    }

    @Test
    void testBusFullCoverage() {
        Bus b1 = new Bus(15, 50, "BB-222-BB");
        
        assertEquals(15, b1.getLongueur());
        assertEquals(50, b1.getPassagers());
        assertNotNull(b1.toString());
        
        IVehicule clone = b1.clone();
        assertEquals("BB-222-BB", clone.getImmatriculation());
    }

    @Test
    void testAmbulanceFullCoverage() {
        Ambulance amb = new Ambulance(2, "AA-111-AA", true);
        assertNotNull(amb.toString());
        amb.clone(); 
    }
    
    @Test
    void testCycleFullCoverage() {
        Cycle c = new Cycle("ZZ-999-ZZ");
        assertEquals(1, c.getPassagers());
        assertNotNull(c.toString());
    }
}