package fr.univ.vv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ComparTest {

    @Mock
    IVehicule v1;
    @Mock
    IVehicule v2;

    // STUB
    private static class ComparStub extends Compar {

        public ComparStub(boolean croissant) {
            super(croissant);
        }

        public ComparStub() {
            super();
        }

        @Override
        public int doCompare(IVehicule a, IVehicule b) {
            if (a == null || b == null) return 0;
            return (a.hashCode() - b.hashCode());
        }
    }

    private static class ComparDeterministe extends Compar {
        public ComparDeterministe(boolean c) { super(c); }
        public ComparDeterministe() { super(); }

        @Override
        public int doCompare(IVehicule a, IVehicule b) {
            int valA = (a.toString().equals("V1")) ? 10 : 20;
            int valB = (b.toString().equals("V1")) ? 10 : 20;
            return valA - valB;
        }
    }

    @Test
    @DisplayName("Ordre Croissant (Défaut) : Délègue doCompare(v1, v2)")
    void testCroissant() {
        // ARRANGE
        ComparDeterministe compar = new ComparDeterministe(true);

        org.mockito.Mockito.when(v1.toString()).thenReturn("V1");
        org.mockito.Mockito.when(v2.toString()).thenReturn("V2");

        // ACT
        int resultat = compar.compare(v1, v2);

        // ASSERT
        assertEquals(-10, resultat, "En mode croissant, l'ordre des paramètres doit être conservé.");
    }

    @Test
    @DisplayName("Ordre Décroissant : Inverse les arguments doCompare(v2, v1)")
    void testDecroissant() {
        // ARRANGE
        ComparDeterministe compar = new ComparDeterministe(false);

        org.mockito.Mockito.when(v1.toString()).thenReturn("V1");
        org.mockito.Mockito.when(v2.toString()).thenReturn("V2");

        // ACT
        int resultat = compar.compare(v1, v2);

        // ASSERT
        assertEquals(10, resultat, "En mode décroissant, l'ordre des paramètres doit être inversé (signe opposé).");
    }

    @Test
    @DisplayName("Constructeur par défaut : Doit être croissant")
    void testConstructeurDefaut() {
        ComparDeterministe compar = new ComparDeterministe();
        org.mockito.Mockito.when(v1.toString()).thenReturn("V1");
        org.mockito.Mockito.when(v2.toString()).thenReturn("V2");

        assertEquals(-10, compar.compare(v1, v2));
    }
}