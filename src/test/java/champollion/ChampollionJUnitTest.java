package champollion;

import org.junit.jupiter.api.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;
	Intervention inter_uml_cm;
	Salle uneSalle;
		
	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");
		uneSalle = new Salle("uneSalle", 999);
		inter_uml_cm = new Intervention(new Date(), 12, 8, untel, uml, TypeIntervention.CM, uneSalle);
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

	@Test
	public void testResteAPlanifier() {
		assertEquals(untel.resteAPlanifier(uml, TypeIntervention.CM), 0, "Le reste à planifié doit être égal à 0 pour un enseignement non planifié d'un enseignant");
		untel.ajouteEnseignement(uml, 12, 0, 0);
		assertEquals(untel.resteAPlanifier(uml, TypeIntervention.CM), 12, "Il doit rester les heures à planifié ajouté précédement");
		untel.ajouteIntervention(inter_uml_cm);
		assertEquals(untel.resteAPlanifier(uml, TypeIntervention.CM), 0, "Le calcul des heures déjà planifié n'est pas bon");
	}

	@Test
	public void testAjouteIntervention(){
		assertThrows(IllegalArgumentException.class, () -> untel.ajouteIntervention(inter_uml_cm), "Ajout invalide");
	}
}
