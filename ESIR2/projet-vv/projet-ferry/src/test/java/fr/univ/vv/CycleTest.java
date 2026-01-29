package fr.univ.vv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CycleTest {

    @Test
    @DisplayName("Initialisation : Détection d'incohérence Doc/Code")
    void testConstructeur() {
        Cycle cycle = new Cycle("VELO-01");

        assertEquals(1, cycle.getLongueur(), "Le code impose une longueur de 1 (contrairement au commentaire)");
        assertEquals(1, cycle.getPassagers(), "Un cycle a toujours 1 passager");
        assertEquals("VELO-01", cycle.getImmatriculation());
    }

    @Test
    @DisplayName("Clone : Indépendance de l'instance")
    void testClone() {
        Cycle original = new Cycle("CC-123");
        IVehicule copie = original.clone();

        assertNotSame(original, copie, "Le clone doit être une nouvelle instance mémoire");
        assertInstanceOf(Cycle.class, copie, "Le clone d'un cycle doit rester un cycle");
        assertEquals("CC-123", copie.getImmatriculation());
    }

    @Test
    @DisplayName("Comparaison : Tri par longueur")
    void testCompareTo() {
        Cycle c = new Cycle("A");
        Auto a = new Auto(4);

        assertTrue(c.compareTo(a) < 0, "Un cycle (1) est plus petit qu'une auto (2)");
    }
}