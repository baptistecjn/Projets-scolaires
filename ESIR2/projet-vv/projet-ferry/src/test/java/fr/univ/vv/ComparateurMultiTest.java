package fr.univ.vv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ComparateurMultiTest {

    @Mock
    Comparator<IVehicule> c1Mock; // Premier critère
    @Mock
    Comparator<IVehicule> c2Mock; // Second critère
    @Mock
    IVehicule v1;
    @Mock
    IVehicule v2;

    @Test
    @DisplayName("Priorité : Si le 1er comparateur tranche, le 2ème n'est pas appelé")
    void testPremierComparateurDomine() {
        // ARRANGE
        when(c1Mock.compare(v1, v2)).thenReturn(10);

        ComparateurMulti multi = new ComparateurMulti(c1Mock, c2Mock);

        // ACT
        int resultat = multi.compare(v1, v2);

        // ASSERT
        assertEquals(10, resultat, "Le résultat doit être celui du premier comparateur");

        verify(c2Mock, never()).compare(any(), any());
    }

    @Test
    @DisplayName("Cascade : Si le 1er comparateur renvoie 0, le 2ème est utilisé")
    void testSecondComparateurAppeleSiEgalite() {
        // ARRANGE
        when(c1Mock.compare(v1, v2)).thenReturn(0);
        when(c2Mock.compare(v1, v2)).thenReturn(-5);

        ComparateurMulti multi = new ComparateurMulti(c1Mock, c2Mock);

        // ACT
        int resultat = multi.compare(v1, v2);

        // ASSERT
        assertEquals(-5, resultat, "Le résultat doit être celui du second comparateur en cas d'égalité du premier");

        verify(c1Mock).compare(v1, v2);
        verify(c2Mock).compare(v1, v2);
    }
}