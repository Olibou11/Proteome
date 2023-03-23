// Importation package
package ca.cegepjonquiere.proteome.main;

// Importations

import ca.cegepjonquiere.proteome.proteine.AcidesAmines;
import ca.cegepjonquiere.proteome.proteine.FabriquerProteine;
import ca.cegepjonquiere.proteome.proteine.Proteine;
import ca.cegepjonquiere.proteome.proteine.Proteome;
import java.util.Scanner;

// Classe ''Main''
public class Main {

    public static void main(String[] args) {

        // Initialisation du Scanner
        Scanner sc = new Scanner(System.in);

        // Variables de départ

        String choixAnimal = "Humain";
        boolean quitter = false;

        // Création du projet
        FabriquerProteine fabriquerProteine = new FabriquerProteine();

        // Création d'un protéome qui contient toutes les protéines du vivant sélectionné (Défault: Humain)
        Proteome dictionnaire = fabriquerProteine.fabriquerProteines();

        // Protéome qui contient les protéines réponses du projet
        Proteome reponse;

        //Menu

        while (!quitter) {

            try {

                System.out.println("•-----------------------Protéome----------------------•" + "\n");
                System.out.println("Choisisez votre option (" + choixAnimal + ") :");
                System.out.println("1 - Chercher une protéine par ses acides aminés");
                System.out.println("2 - Chercher une protéine par son nom-court");
                System.out.println("3 - Imprimer la liste des acides aminés");
                System.out.println("4 - Changer de vivant");
                System.out.println("5 - Imprimer les protéines observées");
                System.out.println("6 - Quitter le projet" + "\n");
                System.out.print(">> ");
                String optionProteome = sc.next().toUpperCase(); // Variable de choix

                switch (optionProteome) {

                    case "1":
                        System.out.println("•--------------------Acides Aminés--------------------•");
                        System.out.println("Veuillez entrer la chaine d'acides aminés:");
                        System.out.print(">> ");
                        String chaineAcidesAmines = sc.next().toUpperCase(); // Variable de la chaine

                        if (chaineAcidesAmines != null) {
                            reponse = dictionnaire.rechercherParAcidesAmines(chaineAcidesAmines);
                            if (!reponse.getProteome().isEmpty())
                                for (Proteine p : reponse.getProteome())
                                    System.out.println(p.toStringLong()); // Imprime les protéines trouvées
                            else
                                System.out.println("ERREUR: Aucun résultat n'a été trouvé!");
                        }
                        else // La chaine est null
                            System.out.println("ERREUR: Veuillez rentrer une chaine d'acides aminés conforme!");
                        break;

                    case "2":
                        System.out.println("•----------------------Nom court----------------------•");
                        System.out.println("Veuillez entrer le nom court:");
                        System.out.print(">> ");
                        String chaineNom = sc.next().toUpperCase();

                        if (chaineNom != null) {
                            reponse = dictionnaire.rechercherParNomCourt(chaineNom);
                            if (!reponse.getProteome().isEmpty()) {
                                if (reponse.getProteome().size() == 1) // Un résultat trouvé
                                    for (Proteine proteine : reponse.getProteome())
                                        System.out.println(proteine.toStringLong()); // Imprime une longue descrip.
                                else // Plusieurs résultats trouvés
                                    for (Proteine proteine : reponse.getProteome())
                                        System.out.println(proteine.toStringCourt()); // Imprime une courte descip.
                            }
                            else
                                System.out.println("ERREUR: Aucun résultat n'a été trouvé!");
                        }
                        else
                            System.out.println("ERREUR: Veuillez rentrer une chaine d'acides aminés conforme!");
                        break;

                    case "3":
                        System.out.println("•-------------------------Liste-----------------------•");
                        System.out.println("Voici la liste des acides aminés:");

                        for (AcidesAmines acide : AcidesAmines.values())
                            System.out.println(acide.toString());
                        break;

                    case "4":
                        System.out.println("•---------------------Choix vivant--------------------•");
                        System.out.println("Choisisez votre option (" + choixAnimal + ") :");
                        System.out.println("H - Protéines humaines");
                        System.out.println("Z - Protéines du chimpanzé");
                        System.out.println("C - Protéines du chien");
                        System.out.println("T - Protéines du théier");
                        System.out.println("V - Protéines de la variole");
                        System.out.println("S - Protéines de la covid" + "\n");
                        System.out.print(">> ");

                        String optionAnimal = sc.next().toUpperCase(); // Variable de choix

                        switch (optionAnimal) {
                            case "H":
                                if (!fabriquerProteine.getFichier().equals("humain.xml")) {
                                    choixAnimal = "Humain"; // Décoration menue
                                    fabriquerProteine.setFicher("humain.xml");
                                    dictionnaire = fabriquerProteine.fabriquerProteines();
                                }
                                break;

                            case "Z":
                                if (!fabriquerProteine.getFichier().equals("chimpanze.xml")) {
                                    fabriquerProteine.setFicher("chimpanze.xml");
                                    choixAnimal = "Chimpanzé"; // Décoration menue
                                    dictionnaire = fabriquerProteine.fabriquerProteines();
                                }
                                break;

                            case "C":
                                if (!fabriquerProteine.getFichier().equals("chien.xml")) {
                                    fabriquerProteine.setFicher("chien.xml");
                                    choixAnimal = "Chien"; // Décoration menue
                                    dictionnaire = fabriquerProteine.fabriquerProteines();
                                }
                                break;

                            case "T":
                                if (!fabriquerProteine.getFichier().equals("theier.xml")) {
                                    fabriquerProteine.setFicher("theier.xml");
                                    choixAnimal = "Théier"; // Décoration menue
                                    dictionnaire = fabriquerProteine.fabriquerProteines();
                                }
                                break;

                            case "V":
                                if (!fabriquerProteine.getFichier().equals("variole.xml")) {
                                    fabriquerProteine.setFicher("variole.xml");
                                    choixAnimal = "Variole"; // Décoration menue
                                    dictionnaire = fabriquerProteine.fabriquerProteines();
                                }
                                break;

                            case "S":
                                if (!fabriquerProteine.getFichier().equals("sars-cov.xml")) {
                                    fabriquerProteine.setFicher("sars-cov.xml");
                                    choixAnimal = "Covid-19"; // Décoration menue
                                    dictionnaire = fabriquerProteine.fabriquerProteines();
                                }
                                break;

                            default:
                                System.out.println("ERREUR: Veuillez rentrer une option valide!");
                        }
                        break;

                    // Bonus

                    case "5":
                        System.out.println("•-----------------Protéines observées-----------------•");
                        System.out.println("Voici les protéines observées:");

                        for (Proteine p : fabriquerProteine.getType())
                            System.out.println(p.toStringCourt());
                        break;

                    case "6":
                        System.out.println("•-----------------------------------------------------•");
                        System.out.println("Bonne journée! :)");

                        quitter = true; // Arrête la boucle
                        break;

                    default:
                        System.out.println("ERREUR: Veuillez rentrer une option valide!");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                e.printStackTrace();
            }
        }
        sc.close(); // Fermeture du Scanner
    }
}
