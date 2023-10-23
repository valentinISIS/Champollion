package champollion;

import java.sql.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Enseignant extends Personne {

    private Map<UE, ServicePrevu> lesEnseignements = new HashMap<>();
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
                            lesEnseignements.get(key).getVolumeCM()*1.5 +
                            lesEnseignements.get(key).getVolumeTD() +
                            lesEnseignements.get(key).getVolumeTP()*0.75;
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
        Double heuresPrevuesPourUE = lesEnseignements.get(ue).getVolumeCM()*1.5 +
                                    lesEnseignements.get(ue).getVolumeTD() +
                                    lesEnseignements.get(ue).getVolumeTP()*0.75;
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
            ServicePrevu servicePrevu = lesEnseignements.get(ue);
            servicePrevu.addVolumeCM(volumeCM);
            servicePrevu.addVolumeTD(volumeTD);
            servicePrevu.addVolumeTP(volumeTP);
            return;
        }
        this.lesEnseignements.put(ue, new ServicePrevu(volumeCM, volumeTD, volumeTP));
    }

}
