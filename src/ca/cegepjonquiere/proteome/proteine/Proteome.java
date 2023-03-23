// Importation package
package ca.cegepjonquiere.proteome.proteine;

// Importations

import java.util.HashMap;
import java.util.TreeSet;
import java.util.regex.Pattern;

// Classe Protéome
public class Proteome {

    // Constructeur
    public Proteome(TreeSet<Proteine> proteome) {
        this.proteome = proteome;
    }

    // Attribut
    private TreeSet<Proteine> proteome;

    // Méthode ''rechercherParAcidesAmines''
    public Proteome rechercherParAcidesAmines(String chaineAcidesAmines) {

        // Initialisation des HasMap

        HashMap<AcidesAmines, Integer> chaineAcidesAminesMap = new HashMap<>();
        HashMap<AcidesAmines, Integer> Map;

        // Inititialisation du TreeSet qui contient les protéines similaires trouvées
        Proteome proteinesSimilaires = new Proteome(new TreeSet<Proteine>());

        if (chaineAcidesAmines != null) {

            // Splitage de la chaine d'acides aminés qui a été transformé en chaine de lettre sans nombre
            String[] sequenceString = transformationChaineAcidesAmines(chaineAcidesAmines).split("");

            // Transforme la chaine ''sequenceString '' dans un HasMap

            for (String s : sequenceString) {
                if (chaineAcidesAminesMap.containsKey(AcidesAmines.valueOf(s)))
                    chaineAcidesAminesMap.put(AcidesAmines.valueOf(s), chaineAcidesAminesMap.get(AcidesAmines.valueOf(s)) + 1);
                else
                    chaineAcidesAminesMap.put(AcidesAmines.valueOf(s), 1);
            }

            // Transforme toutes les protéines d'un organisme vivant dans un HasMap

            for (Proteine p : this.proteome) {
                Map = new HashMap<>();
                String[] sequenceXML = p.getAcidesAmines().split("");
                for (String s : sequenceXML) {
                    if (Map.containsKey(AcidesAmines.valueOf(s)))
                        Map.put(AcidesAmines.valueOf(s), Map.get(AcidesAmines.valueOf(s)) + 1);
                    else
                        Map.put(AcidesAmines.valueOf(s), 1);
                }
                if (Map.equals(chaineAcidesAminesMap)) // Compare les deux HasMap ensemble et ajoute les protéines similaires dans un TreeSet ''listePareille''
                    proteinesSimilaires.ajouterProteine(p);
            }
        }
        else
            throw new IllegalArgumentException("ERREUR: Veuillez entrer des caractères valides!");

        return proteinesSimilaires;
    }

    // Méthode ''transformationChaineAcidesAmines''
    private String transformationChaineAcidesAmines(String chaineAcidesAmines) throws IllegalArgumentException {

        // Splitage de la chaine d'acides aminés

        String[] chaineAcidesAminesSplit; // Tableau avec tous les caractères de la chaine à l'intérieur
        chaineAcidesAminesSplit = chaineAcidesAmines.split("");

        // Transforme la string de départ avec des nombres en une string avec uniquement des lettres

        // Attribut de départ
        boolean multiplicateurActif = false;

        // Initialisation de la String finale
        StringBuilder stringAcidesAmines = new StringBuilder(); // String vide qui va s'aggrandir d'acides aminés avec le temps

        Pattern patternNombre = Pattern.compile("[0123456789]"); // Les nombres disponibles (Multiplicateurs)
        Pattern patternLettre = Pattern.compile(AcidesAmines.getRegex(), Pattern.CASE_INSENSITIVE); // Les acides aminés disponibles au clavier

        int multiplicateur = 0; // Valeur du multiplicateur Initiale

        for (String acide : chaineAcidesAminesSplit) { // Boucle le tableau de string
            if (acide.matches(String.valueOf(patternNombre))) { // SI NOMBRE
                if (!multiplicateurActif) { // SI premier
                    multiplicateur = 0;
                    multiplicateur = Integer.parseInt(acide); // Le multiplicateur = nombre trouvé
                    multiplicateurActif = true;
                } else // SI pas premier
                    multiplicateur = multiplicateur * 10 + Integer.parseInt(acide); // Augmentation du multiplicateur
            } else {
                if (acide.matches(String.valueOf((patternLettre)))) { // SI LETTRE
                    if (!multiplicateurActif) { // SI n'a pas de multiplicateur
                        stringAcidesAmines.append(acide); // Ajoute la lettre à la fin de la string
                        multiplicateurActif = false;
                    } else { // SI a un multiplicateur
                        for (int i = 0; i < multiplicateur; i++)
                            stringAcidesAmines.append(acide); // Ajoute les lettres à la fin de la string
                        multiplicateurActif = false;
                    }
                } else
                    throw new IllegalArgumentException("ERREUR: Veuillez entrer des caractères valides!");
            }
        }
        return String.valueOf(stringAcidesAmines);
    }

    //Méthode ''rechercherParNomCourt''
    public Proteome rechercherParNomCourt(String chaineAcidesAmines) {


        // Initialisation d'un TreeSet qui contiendra toutes les protéines recherchées
        Proteome proteome = new Proteome(new TreeSet<>());
        if (chaineAcidesAmines != null) {
            // Recherche par nom court
            for (Proteine p : this.proteome) {
                if (p.getNom().contains(chaineAcidesAmines))
                    proteome.ajouterProteine(p);
            }

            // Recherche par nom long
            if (proteome.getProteome().isEmpty()) { // SI n'a aucun résultat dans les noms courts
                for (Proteine p : this.proteome) {
                    if (p.getNomLong().contains(chaineAcidesAmines))
                        proteome.ajouterProteine(p);
                }
            }
        }
        else
            throw new IllegalArgumentException("ERREUR: Veuillez entrer des caractères valides!");
        return proteome;
    }

    // Accesseur et Muttatteur

    public TreeSet<Proteine> getProteome() {
        return proteome;
    }

    public void ajouterProteine(Proteine p) {
        proteome.add(p);
    }
}