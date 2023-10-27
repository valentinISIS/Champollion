package champollion;

import java.util.HashSet;
import java.util.Set;

public class UE {
    private final String myIntitule;

    private int heureCM;
    private int heureTD;
    private int heureTP;
    private Set<Intervention> interventions = new HashSet<>();

    public UE(String myIntitule) {
        this.myIntitule = myIntitule;
    }

    public String getIntitule() {
        return myIntitule;
    }
    
}
