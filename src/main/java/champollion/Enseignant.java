package champollion;

import java.sql.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Enseignant extends Personne {

    private Map<UE, Integer[]> lesEnseignements = new HashMap<>();
    public Enseignant(String nom, String email) {
        super(nom, email);
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        Double heuresPrevues = 0.0;
        for (UE key:lesEnseignements.keySet()) {
            heuresPrevues = heuresPrevues +
                            lesEnseignements.get(key)[0]*1.5 +
                            lesEnseignements.get(key)[1] +
                            lesEnseignements.get(key)[2]*0.75;
        }
        return heuresPrevues.intValue();
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        Double heuresPrevuesPourUE = lesEnseignements.get(ue)[0]*1.5 +
                lesEnseignements.get(ue)[1] +
                lesEnseignements.get(ue)[2]*0.75;
        return heuresPrevuesPourUE.intValue();
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        if (this.lesEnseignements.containsKey(ue)){
            Integer [] mesHeuresUe = lesEnseignements.get(ue);
            this.lesEnseignements.put(ue, new Integer[]{volumeCM + mesHeuresUe[0], volumeTD + mesHeuresUe[1], volumeTP + mesHeuresUe[2]});
            return;
        }
        this.lesEnseignements.put(ue, new Integer[]{volumeCM, volumeTD, volumeTP});
    }

}
