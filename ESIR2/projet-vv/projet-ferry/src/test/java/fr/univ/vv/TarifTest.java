package fr.univ.vv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TarifTest {

    static class TarifConcret extends Tarif {
        public TarifConcret(int tFixe, int tVar) {
            super(tFixe, tVar);
        }

        @Override
        public int calculerTarif(IVehicule v) {
            return 0;
        }
    }

    @Test
    @DisplayName("Etat Global : Gestion du tarif passager (static)")
    void testTarifPassagerStatic() {
        int ancienTarif = Tarif.getTarifPass();

        try {
            Tarif.setTarifPass(10);
            assertEquals(10, Tarif.getTarifPass());

            TarifConcret t = new TarifConcret(0, 0);
            assertEquals(10, Tarif.getTarifPass(), "La modification statique doit être visible partout");

        } finally {
            Tarif.setTarifPass(ancienTarif);
        }
    }

    @Test
    @DisplayName("Encapsulation : Accesseurs des tarifs fixes et variables")
    void testAccesseurs() {
        TarifConcret tarif = new TarifConcret(100, 50);

        assertEquals(100, tarif.getTarifFixe());
        assertEquals(50, tarif.getTarifVariable());

        tarif.setTarifFixe(200);
        assertEquals(200, tarif.getTarifFixe());
    }
}