package champollion;

public class UE {
    private final String myIntitule;

    private int heureCM;
    private int heureTD;
    private int heureTP;

    public UE(String myIntitule) {
        this.myIntitule = myIntitule;
    }

    public String getIntitule() {
        return myIntitule;
    }

    public boolean ajoutValide(int heureCM, int heureTD, int heureTP){
        return false;
    }
    
}
