// Importation package
package ca.cegepjonquiere.proteome.proteine;

// Enumération ''AcidesAmines''
public enum AcidesAmines {
    A("Ala", "Alanine", "A"),
    B("Asx", "Aspatate", "B"),
    R("Arg", "Arginine", "R"),
    N("Asn", "Asparagine", "N"),
    D("Asp", "Aspartate", "D"),
    C("Cys", "Cystéine", "C"),
    E("Glu", "Glutamate", "E"),
    Q("Gln", "Glutamine", "Q"),
    G("Gly", "Glycine", "G"),
    H("His", "Histidine", "H"),
    I("Ile", "Isoleucine", "I"),
    L("Leu", "Leucine", "L"),
    K("Lys", "Lysine", "K"),
    M("Met", "Méthionine", "M"),
    F("Phe", "Phénylalanine", "F"),
    P("Pro", "Proline", "P"),
    S("Ser", "Serine", "S"),
    T("Thr", "Thréonine", "T"),
    W("Trp", "Triptophane", "W"),
    Y("Tyr", "Tyrosine", "Y"),
    V("Val", "Valine", "V"),
    X("Xaa", "Unknow", "X"),
    U("SEC", "Sélénocystéine", "U"),
    O("Pyl", "Pyrrolysine", "O"),
    J("Xle", "Glutamine", "J"),
    Z("Glz", "Glutamane", "Z");

    // Attributs finaux

    private final String LONGUE;
    private final String NOM;
    private final String ABR;

    // Constructeur
    AcidesAmines(String longue, String nom, String abr) {
        LONGUE = longue;
        NOM = nom;
        ABR = abr;
    }

    // Regex qui contient toutes les acides aminés possible au clavier (Pas fait de bonus)
    public static String getRegex() {
        return "[BRNADCEQGHILKMFPSTWYVBZXJ]";
    }

    // toString
    @Override
    public String toString() {
        return "[ " + NOM + ", Abr. Longue ==> " + LONGUE + ", Abr. Courte ==> " + ABR + " ]";
    }
}