package fr.univ.vv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AmbulanceTest {

    @Test
    @DisplayName("Héritage : Une ambulance est une Auto")
    void testHeritage() {
        Ambulance ambul = new Ambulance(2, "AM-112-PL");

        assertInstanceOf(Auto.class, ambul, "L'Ambulance doit hériter de Auto");
        assertEquals(2, ambul.getLongueur(), "L'Ambulance hérite la longueur 2 de la classe Auto");
    }

    @Test
    @DisplayName("Spécificité : Constructeurs TT et Normal")
    void testConstructeurs() {
        Ambulance normale = new Ambulance(1, "A");
        assertFalse(normale.isTT());

        Ambulance toutTerrain = new Ambulance(1, "B", true);
        assertTrue(toutTerrain.isTT());
    }
}