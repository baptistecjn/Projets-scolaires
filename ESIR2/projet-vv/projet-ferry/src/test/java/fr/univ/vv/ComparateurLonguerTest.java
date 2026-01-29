package fr.univ.vv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComparateurLongueurTest {

    @Mock
    IVehicule v1;
    @Mock
    IVehicule v2;

    @Test
    @DisplayName("Comparaison : v1 plus court que v2 (doit retourner négatif)")
    void testInferieur() {
        // ARRANGE
        ComparateurLongueur comparateur = new ComparateurLongueur();
        when(v1.getLongueur()).thenReturn(10);
        when(v2.getLongueur()).thenReturn(20);

        // ACT
        int resultat = comparateur.doCompare(v1, v2);

        // ASSERT
        assertTrue(resultat < 0, "Le résultat doit être négatif car 10 < 20");
    }

    @Test
    @DisplayName("Comparaison : v1 plus long que v2 (doit retourner positif)")
    void testSuperieur() {
        // ARRANGE
        ComparateurLongueur comparateur = new ComparateurLongueur();
        when(v1.getLongueur()).thenReturn(30);
        when(v2.getLongueur()).thenReturn(5);

        // ACT
        int resultat = comparateur.doCompare(v1, v2);

        // ASSERT
        assertTrue(resultat > 0, "Le résultat doit être positif car 30 > 5");
    }

    @Test
    @DisplayName("Comparaison : v1 égal à v2 (doit retourner 0)")
    void testEgalite() {
        // ARRANGE
        ComparateurLongueur comparateur = new ComparateurLongueur();
        when(v1.getLongueur()).thenReturn(15);
        when(v2.getLongueur()).thenReturn(15);

        // ACT
        int resultat = comparateur.doCompare(v1, v2);

        // ASSERT
        assertEquals(0, resultat, "Le résultat doit être 0 pour des longueurs identiques");
    }

    @Test
    void testComparaisonComplete() {
        ComparateurLongueur comp = new ComparateurLongueur();
        IVehicule v1 = mock(IVehicule.class);
        IVehicule v2 = mock(IVehicule.class);

        // Cas 1 : v1 < v2
        when(v1.getLongueur()).thenReturn(10);
        when(v2.getLongueur()).thenReturn(20);
        assertTrue(comp.compare(v1, v2) < 0);

        // Cas 2 : v1 > v2
        assertTrue(comp.compare(v2, v1) > 0);

        // Cas 3 : égalité
        when(v2.getLongueur()).thenReturn(10);
        assertEquals(0, comp.compare(v1, v2));
    }
}