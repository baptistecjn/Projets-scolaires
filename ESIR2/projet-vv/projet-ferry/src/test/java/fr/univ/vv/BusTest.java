package fr.univ.vv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BusTest {

    @Test
    @DisplayName("Initialisation : Bus standard")
    void testCreationBus() {
        Bus bus = new Bus(12, 50, "BUS-01");

        assertEquals(12, bus.getLongueur());
        assertEquals(50, bus.getPassagers());
        assertEquals("BUS-01", bus.getImmatriculation());
    }

    @Test
    @DisplayName("Clone : Vérification du type de retour")
    void testClone() {
        Bus bus = new Bus(10, 20, "CC-333-CC");
        IVehicule clone = bus.clone();

        assertInstanceOf(Bus.class, clone, "Le clone d'un bus doit être un bus");
        assertEquals(bus.getLongueur(), clone.getLongueur());
    }
}