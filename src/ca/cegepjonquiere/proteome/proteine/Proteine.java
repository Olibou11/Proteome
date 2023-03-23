// Importation package
package ca.cegepjonquiere.proteome.proteine;

// Classe ''Protéine''
public class Proteine implements Comparable<Proteine> {

    // Attributs

    private String nom;
    private String nomLong;
    private String acidesAmines;

    // Constructeur
    public Proteine(String nom, String nomLong, String acidesAmines) {
        this.nom = nom;
        this.nomLong = nomLong;
        this.acidesAmines = acidesAmines;
    }

    // Accesseurs

    public String getNom() {
        return nom;
    }

    public String getNomLong() { return nomLong; }

    public String getAcidesAmines() {
        return acidesAmines;
    }

    // toString

    public String toStringCourt() {
        return "[ " + nom + ", Nom long ==> " + nomLong + " ]";
    }

    public String toStringLong() {
        return "[ " + nom + ", Nom long ==> " + nomLong + ", Séquence " + acidesAmines + " ]";
    }

    // Comparable
    @Override
    public int compareTo(Proteine o) {
        return nom.compareTo(o.nom);
    }
}