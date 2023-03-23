// Importation package
package ca.cegepjonquiere.proteome.proteine;

// Importations

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.TreeSet;

// Classe ''FabriquerProteine''
public class FabriquerProteine {

    // Création de la liste qui contient toutes les protéines dans le document XML
    private Proteome proteome = new Proteome(new TreeSet<>());

    // Fichier par défault / Fichier lue
    private String fichier = "humain.xml";

    // Liste de protéines observées dans la nature
    private TreeSet<Proteine> type;

    // Méthode ''fabriquerProteines''
    public Proteome fabriquerProteines() {

        // Insertion de toutes les protéines du dossier XML dans un ''Proteome''

        try {

            // S'assure de vider la liste
            proteome.getProteome().clear();

            // Liste de protéines observées (Bonus)
            TreeSet<Proteine> type = new TreeSet<>();

            File fXmlFile = new File(fichier);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("entry"); // Va chercher tous les éléments entre le mot ''entry'' qui sont des protéines avec toutes leurs caractéristiques

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Proteine p = new Proteine(eElement.getElementsByTagName("name").item(0).getTextContent(), eElement.getElementsByTagName("fullName").item(0).getTextContent(), eElement.getElementsByTagName("sequence").item(0).getTextContent());
                    proteome.getProteome().add(p); // Ajoute une protéines dans la liste avec les caractéristiques choisites

                    // Bonus

                    NodeList proteinExistenceList = eElement.getElementsByTagName("proteinExistence");
                    for (int i = 0; i < proteinExistenceList.getLength(); i++) {
                        Node node = proteinExistenceList.item(i);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element proteinExistence = (Element) node;
                            if (proteinExistence.getAttribute("type").equals("evidence at protein level"))
                                type.add(p);
                        }
                    }
                }
            }
            setType(type);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return proteome;
        }
    }

    // Mutateurs et Accesseurs

    public void setFicher(String choixFichier) {
        fichier = choixFichier;
    }

    public String getFichier() {
        return fichier;
    }

    public TreeSet<Proteine> getType() {
        return type;
    }

    private void setType(TreeSet<Proteine> type) {
        this.type = type;
    }
}