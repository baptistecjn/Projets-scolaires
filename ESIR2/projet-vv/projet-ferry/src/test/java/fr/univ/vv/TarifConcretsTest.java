package fr.univ.vv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TarifsConcretsTest {

    @Mock
    IVehicule vehiculeMock;

    @BeforeEach
    void resetSingletons() throws Exception {
        resetSingleton(TarifAuto.class);
        resetSingleton(TarifBus.class);
        resetSingleton(TarifCycle.class);
        resetSingleton(TarifAutoTT.class);
        resetSingleton(TarifAmbulance.class);

        Tarif.setTarifPass(1);
    }

    // Méthode pour remettre le champ 't' à null via la réflexion
    private void resetSingleton(Class<?> clazz) throws Exception {
        Field instance = clazz.getDeclaredField("t");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    @DisplayName("TarifAuto : Calcul (Fixe + Passagers)")
    void testTarifAuto() {
        // Arrange
        Tarif.setTarifPass(10);
        TarifAuto.createSingleton(100, 0); 
        Tarif tarif = TarifAuto.getInstance();

        when(vehiculeMock.getPassagers()).thenReturn(3);

        // Act
        int prix = tarif.calculerTarif(vehiculeMock);

        // Assert
        assertEquals(130, prix);
    }

    @Test
    @DisplayName("TarifBus : Calcul (Fixe + Longueur*Var + Passagers)")
    void testTarifBus() {
        // Arrange
        Tarif.setTarifPass(2);
        TarifBus.createSingleton(200, 5);
        Tarif tarif = TarifBus.getInstance();

        when(vehiculeMock.getPassagers()).thenReturn(50);
        when(vehiculeMock.getLongueur()).thenReturn(12); 

        // Act
        int prix = tarif.calculerTarif(vehiculeMock);

        // Assert
        assertEquals(360, prix);
    }

    @Test
    @DisplayName("TarifAmbulance : Gratuité")
    void testTarifAmbulance() {
        // Arrange
        TarifAmbulance.createSingleton(500, 500);
        Tarif tarif = TarifAmbulance.getInstance();

        // Act
        int prix = tarif.calculerTarif(vehiculeMock);

        // Assert : Doit toujours être 0
        assertEquals(0, prix);
    }

    @Test
    @DisplayName("TarifCycle : Coût fixe + 1 passager (Indépendant du nb réel)")
    void testTarifCycle() {
        // Arrange
        Tarif.setTarifPass(5);
        TarifCycle.createSingleton(20, 0);
        Tarif tarif = TarifCycle.getInstance();

        // Act
        int prix = tarif.calculerTarif(vehiculeMock);

        // Assert : 20 + 5 = 25
        assertEquals(25, prix);
    }

    @Test
    @DisplayName("TarifAutoTT : Même logique que Auto")
    void testTarifAutoTT() {
        // Arrange
        Tarif.setTarifPass(10);
        TarifAutoTT.createSingleton(500, 0);
        Tarif tarif = TarifAutoTT.getInstance();

        when(vehiculeMock.getPassagers()).thenReturn(2);

        // Act
        int prix = tarif.calculerTarif(vehiculeMock);

        // Assert : 500 + (10 * 2) = 520
        assertEquals(520, prix);
    }

    @Test
    @DisplayName("Singleton : Vérification de l'unicité")
    void testSingletonPattern() {
        Tarif t1 = TarifAuto.createSingleton(10, 10);
        Tarif t2 = TarifAuto.createSingleton(999, 999);

        assertSame(t1, t2, "createSingleton ne doit pas recréer l'instance si elle existe déjà");
        assertEquals(10, t1.getTarifFixe(), "Les valeurs initiales doivent être conservées");
    }
}