package champollion;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;
		
	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");		
	}
	

	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(),
                        "Un nouvel enseignant doit avoir 0 heures prévues");
	}
	
	@Test
	public void testAjouteHeures() {
                // 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		assertEquals(10, untel.heuresPrevuesPourUE(uml),
                        "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

                // 20h TD pour UML
                untel.ajouteEnseignement(uml, 0, 20, 0);
                
		assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                         "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");

		assertThrows(IllegalArgumentException.class, () -> untel.ajouteEnseignement(uml, -1, -1, -1), "On ne peut pas ajouter de valeur négative");
	}

	@Test
	public void testEnseignantEnSousService() {
		assertTrue(untel.enSousService(), "Une personne doit avoir plus de Enseignant.HEURES_PREVUES_MINIMUM pour ne plus être en sousService");
		untel.ajouteEnseignement(uml, 0, Enseignant.HEURES_PREVUES_MINIMUM, 0);
		assertFalse(untel.enSousService(), "Une personne doit avoir moins de Enseignant.HEURES_PREVUES_MINIMUM pour  être en sousService");
	}
	
}
