package fr.univ.vv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BreizhTransportTest {

    @Mock
    IVehicule vehiculeMock;

    @Test
    @DisplayName("Utilitaire : Génération d'immatriculation (Format Regex)")
    void testNewImmat() {
        Random random = new Random();

        String immat = BreizhTransport.newImmat(random);

        assertTrue(immat.matches("[A-Z]{2}-\\d{3}-[A-Z]{2}"),
                "L'immatriculation " + immat + " ne respecte pas le format attendu XX-000-XX");
    }

    @Test
    @DisplayName("Défaut de Conception : Crash de calculerTarif si main non lancé")
    void testCalculerTarifCrash() {

        when(vehiculeMock.getImmatriculation()).thenReturn("AA-123-AA");

        assertThrows(NullPointerException.class, () -> {
            BreizhTransport.calculerTarif(vehiculeMock);
        }, "Doit échouer car la tableTarifs n'est initialisée que dans le main()");
    }
}