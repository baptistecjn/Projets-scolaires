package fr.univ.vv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AutoTest {

    @Test
    @DisplayName("Constructeur : Vérification des valeurs par défaut")
    void testConstructeurDefaut() {
        Auto auto = new Auto(4);

        assertEquals(2, auto.getLongueur(), "Une auto doit toujours avoir une longueur de 2");
        assertEquals(4, auto.getPassagers());
        assertFalse(auto.isTT(), "Par défaut, une auto n'est pas Tout-Terrain");
    }

    @Test
    @DisplayName("Tout-Terrain : Vérification de l'attribut isToutTerrain")
    void testToutTerrain() {
        Auto auto4x4 = new Auto(2, true);
        assertTrue(auto4x4.isTT(), "Le constructeur doit prendre en compte le booléen TT");
    }

    @Test
    @DisplayName("Clone : Copie profonde et indépendance")
    void testClone() {
        Auto original = new Auto(5, "AA-123-AA", true);
        IVehicule copie = original.clone();

        assertNotSame(original, copie, "Le clone doit être une nouvelle instance");

        assertEquals(original.getImmatriculation(), copie.getImmatriculation());
        assertEquals(original.getPassagers(), copie.getPassagers());
    }
}