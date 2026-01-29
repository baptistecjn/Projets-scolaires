package fr.univ.vv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

    /**
     * Classe de test pour la classe abstraite Vehicule.
     * Utilise une implémentation Stub pour tester les méthodes concrètes.
     */
    public class VehiculeTest {

        // STUB 
        private static class VehiculeConcret extends Vehicule {
            public VehiculeConcret(int capacL, int capacP, String immat) {
                super(capacL, capacP, immat);
            }

            @Override
            public IVehicule clone() { return new VehiculeConcret(getLongueur(), getPassagers(), getImmatriculation()); }
            @Override
            public String toString() { return "VehiculeConcret"; }
        }

        @Test
        @DisplayName("Initialisation : Vérification des getters")
        void testInitialisation() {
            // ARRANGE & ACT
            Vehicule v = new VehiculeConcret(15, 4, "AA-123-BB");

            // ASSERT
            assertEquals(15, v.getLongueur(), "Le getter Longueur doit renvoyer la valeur constructeur");
            assertEquals(4, v.getPassagers(), "Le getter Passagers doit renvoyer la valeur constructeur");
            assertEquals("AA-123-BB", v.getImmatriculation(), "Le getter Immat doit renvoyer la bonne chaîne");
        }

        @Test
        @DisplayName("Comparaison : Partitionnement des résultats <, >, =")
        void testCompareTo() {
            // ARRANGE
            Vehicule petit = new VehiculeConcret(10, 0, "A");
            Vehicule moyen = new VehiculeConcret(20, 0, "B");
            Vehicule grand = new VehiculeConcret(30, 0, "C");

            // ACT & ASSERT
            assertTrue(petit.compareTo(moyen) < 0, "10 - 20 doit être négatif");

            assertTrue(grand.compareTo(moyen) > 0, "30 - 20 doit être positif");

            assertEquals(0, moyen.compareTo(moyen), "Un véhicule comparé à lui-même doit valoir 0");
        }

        @Test
        @DisplayName("Robustesse : Création avec des valeurs négatives (Faille identifiée)")
        void testValeursNegatives() {
            // ARRANGE
            Vehicule vBug = new VehiculeConcret(-100, -50, null);

            // ASSERT
            assertEquals(-100, vBug.getLongueur());
        }

        @Test
        @DisplayName("Limite : Comparaison avec valeurs extrêmes")
        void testCompareToLimites() {
            // ARRANGE
            Vehicule vMax = new VehiculeConcret(Integer.MAX_VALUE, 0, "MAX");
            Vehicule vZero = new VehiculeConcret(0, 0, "ZERO");

            // ACT & ASSERT
            assertTrue(vMax.compareTo(vZero) > 0);
        }
    }

