package fr.univ.vv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FerryTest {

    @Mock
    IVehicule vehiculeMock;

    @Test
    @DisplayName("Nominal : Ajout d'un véhicule valide")
    void testAjouterVehiculeNominal() {
        // ARRANGE
        // Ferry de capacité 100 longueur, 100 passagers
        Ferry ferry = new Ferry(100, 100);

        when(vehiculeMock.getLongueur()).thenReturn(10);
        when(vehiculeMock.getPassagers()).thenReturn(5);
        when(vehiculeMock.clone()).thenReturn(vehiculeMock);

        // ACT
        boolean ajoutReussi = ferry.ajouter(vehiculeMock);

        // ASSERT
        assertTrue(ajoutReussi, "Le véhicule devrait être ajouté car il y a de la place");
        assertEquals(10, ferry.getLongueur(), "La longueur utilisée doit être mise à jour à 10");
        assertEquals(5, ferry.getPassagers(), "Le nombre de passagers doit être mis à jour à 5");
    }

    @Test
    @DisplayName("Limite : Ajout d'un véhicule qui remplit exactement le Ferry (Boundary Value)")
    void testAjouterVehiculeLimiteExacte() {
        // ARRANGE
        Ferry ferry = new Ferry(50, 20); // Petit ferry

        // Véhicule qui fait exactement la taille du ferry
        when(vehiculeMock.getLongueur()).thenReturn(50);
        when(vehiculeMock.getPassagers()).thenReturn(20);
        when(vehiculeMock.clone()).thenReturn(vehiculeMock);

        // ACT
        boolean ajoutReussi = ferry.ajouter(vehiculeMock);

        // ASSERT
        assertTrue(ajoutReussi, "Le véhicule de taille exacte devrait être accepté (condition <=)");
        assertEquals(50, ferry.getLongueur());
    }

    @Test
    @DisplayName("Echec : Refus pour dépassement de Longueur")
    void testAjouterVehiculeTropLong() {
        // ARRANGE
        Ferry ferry = new Ferry(10, 100); // Max longueur 10

        // Véhicule de 11m
        when(vehiculeMock.getLongueur()).thenReturn(11);
        when(vehiculeMock.getPassagers()).thenReturn(1);

        // ACT
        boolean ajoutReussi = ferry.ajouter(vehiculeMock);

        // ASSERT
        assertFalse(ajoutReussi, "Le véhicule devrait être refusé (11 > 10)");
        assertEquals(0, ferry.getLongueur(), "La capacité ne doit pas changer en cas d'échec");
    }

    @Test
    @DisplayName("Echec : Refus pour dépassement de Passagers")
    void testAjouterVehiculeTropDeMonde() {
        // ARRANGE
        Ferry ferry = new Ferry(100, 10); // Max passagers 10

        // Véhicule court mais bondé (11 passagers)
        when(vehiculeMock.getLongueur()).thenReturn(5);
        when(vehiculeMock.getPassagers()).thenReturn(11);

        //ACT
        boolean ajoutReussi = ferry.ajouter(vehiculeMock);

        // ASSERT
        assertFalse(ajoutReussi, "Le véhicule devrait être refusé (11 > 10 passagers)");
        assertEquals(0, ferry.getPassagers(), "Le compteur passagers ne doit pas changer");
    }

    @Test
    @DisplayName("Accumulation : Ajout de deux véhicules successifs")
    void testAjoutMultiple() {
        // ARRANGE
        Ferry ferry = new Ferry(100, 100);
        IVehicule v1 = mock(IVehicule.class);
        IVehicule v2 = mock(IVehicule.class);

        // V1 : 10m, 10p
        when(v1.getLongueur()).thenReturn(10);
        when(v1.getPassagers()).thenReturn(10);
        when(v1.clone()).thenReturn(v1);

        // V2 : 20m, 20p
        when(v2.getLongueur()).thenReturn(20);
        when(v2.getPassagers()).thenReturn(20);
        when(v2.clone()).thenReturn(v2);

        // ACT
        ferry.ajouter(v1);
        boolean ajoutV2 = ferry.ajouter(v2);

        // ASSERT
        assertTrue(ajoutV2);
        assertEquals(30, ferry.getLongueur(), "10 + 20 devrait donner 30");
        assertEquals(30, ferry.getPassagers(), "10 + 20 devrait donner 30");
    }

    @Test
    @DisplayName("Formatage : toString doit afficher l'état du ferry")
    void testToString() {
        // ARRANGE
        Ferry ferry = new Ferry(100, 50);

        // ACT
        String resultat = ferry.toString();

        // ASSERT
        assertFalse(resultat.contains("fr.univ.vv.Ferry@"),
                "La méthode toString ne doit plus afficher l'adresse mémoire brute");
        assertTrue(resultat.contains("Longueur disponible           : 100"),
                "L'affichage doit contenir la longueur restante correcte");
        assertTrue(resultat.contains("Places   disponibles          : 50"),
                "L'affichage doit contenir les places restantes correctes");
    }

    /**
     * Teste l'ajout d'un véhicule dont les dimensions sont inférieures aux limites.
     * Cas nominal : vérifie l'acceptation et la mise à jour des compteurs.
     */
    @Test
    void testAjouter_Partition_Valide() {
        // Arrange : Ferry de 10m/10p, véhicule de 5m/2p
        Ferry ferry = new Ferry(10, 10);
        IVehicule v = new VehiculeStub(5, 2);
        // Act : Tentative d'ajout
        boolean result = ferry.ajouter(v);
        // Assert : Vérification du succès et de l'état interne
        assertTrue(result, "Le véhicule devrait être accepté (cas nominal).");
        assertEquals(5, ferry.getLongueur());
    }

    /**
     * Teste l'ajout d'un véhicule dont les dimensions atteignent exactement la capacité maximale.
     * Valeur limite : vérifie si l'opérateur de comparaison inclut la borne supérieure.
     */
    @Test
    void testAjouter_Partition_Limite_Exacte() {
        // Arrange : Véhicule pile à la taille du Ferry
        Ferry ferry = new Ferry(10, 10);
        IVehicule v = new VehiculeStub(10, 10);

        // Act & Assert
        assertTrue(ferry.ajouter(v), "Le véhicule à la limite exacte doit être accepté.");
    }

    /**
     * Teste le rejet d'un véhicule dont la longueur dépasse la capacité restante.
     * Valeur hors limite : vérifie la robustesse face à un dépassement de longueur.
     */
    @Test
    void testAjouter_Partition_HorsLimite_Longueur() {
        // Arrange : Véhicule trop long (11m pour 10m)
        Ferry ferry = new Ferry(10, 10);
        IVehicule v = new VehiculeStub(11, 2);

        // Act & Assert : Vérifie le refus et l'absence d'effet de bord
        assertFalse(ferry.ajouter(v), "Le véhicule trop long doit être refusé.");
        assertEquals(0, ferry.getLongueur(), "Le compteur ne doit pas avoir bougé.");
    }

    @Test
    void testFerryGettersEtTris() {
        // ARRANGE
        Ferry ferry = new Ferry(100, 50);
        IVehicule v1 = mock(IVehicule.class);
        IVehicule v2 = mock(IVehicule.class);
        
        when(v1.clone()).thenReturn(v1);
        when(v2.clone()).thenReturn(v2);
        
        when(v1.getLongueur()).thenReturn(5);
        when(v2.getLongueur()).thenReturn(10);
        
        when(v1.compareTo(any())).thenReturn(-1); 
        
        // ACT
        ferry.ajouter(v2); 
        ferry.ajouter(v1); 
        
        // ACT
        assertEquals(100, ferry.getMaxCapacLong());
        assertEquals(50, ferry.getMaxCapacPass());
        assertEquals(15, ferry.getLongueur());
        
        // ACT
        ferry.trier(); 
        
        ferry.trier(new ComparateurLongueur());
    }
}
