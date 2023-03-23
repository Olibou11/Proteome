// Importation package
package ca.cegepjonquiere.proteome.test;

// Importations

import ca.cegepjonquiere.proteome.proteine.FabriquerProteine;
import ca.cegepjonquiere.proteome.proteine.Proteine;
import ca.cegepjonquiere.proteome.proteine.Proteome;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// classe ''TestUnitaire''
public class TestUnitaire {

    @Test
    void rechercherNomTest() {

        // Création de toutes les protéines humaines

        FabriquerProteine fabriquerProteine = new FabriquerProteine();
        Proteome dictionnaireHumain = fabriquerProteine.fabriquerProteines();

        // Recherche avec une partie de nom court

        Proteome reponseNomCourt = dictionnaireHumain.rechercherParNomCourt("RRAGB");
        Proteine p = new Proteine("RRAGB_HUMAN", "Ras-related GTP-binding protein B", "MEESDSEKTTEKENLGPRMDPPLGEPEGSLGWVLPNTAMKKKVLLMGKSGSGKTSMRSIIFANYIARDTRRLGATILDRIHSLQINSSLSTYSLVDSVGNTKTFDVEHSHVRFLGNLVLNLWDCGGQDTFMENYFTSQRDNIFRNVEVLIYVFDVESRELEKDMHYYQSCLEAILQNSPDAKIFCLVHKMDLVQEDQRDLIFKEREEDLRRLSRPLECSCFRTSIWDETLYKAWSSIVYQLIPNVQQLEMNLRNFAEIIEADEVLLFERATFLVISHYQCKEQRDAHRFEKISNIIKQFKLSCSKLAASFQSMEVRNSNFAAFIDIFTSNTYVMVVMSDPSIPSAATLINIRNARKHFEKLERVDGPKQCLLMR");
        for (Proteine proteine : reponseNomCourt.getProteome()) {
            assertEquals(p.toStringLong(), proteine.toStringLong(), String.valueOf(0.1));
        }

        // Recherche avec un nom long

        Proteome reponseNomLong = dictionnaireHumain.rechercherParNomCourt("DNA-directed RNA polymerase III subunit RPC7-like");
        p = new Proteine("RPC7L_HUMAN", "DNA-directed RNA polymerase III subunit RPC7-like", "MASRGGGRGRGRGQLTFNVEAVGIGKGDALPPPTLQPSPLFPPLEFRPVPLPSGEEGEYVLALKQELRGAMRQLPYFIRPAVPKRDVERYSDKYQMSGPIDNAIDWNPDWRRLPRELKIRVRKLQKERITILLPKRPPKTTEDKEETIQKLETLEKKEEEVTSEEDEEKEEEEEKEEEEEEEYDEEEHEEETDYIMSYFDNGEDFGGDSDDNMDEAIY");
        for (Proteine proteine : reponseNomLong.getProteome()) {
            assertEquals(p.toStringLong(), proteine.toStringLong(), String.valueOf(0.1));
        }

        // Exeption ''String null''
        assertThrows(IllegalArgumentException.class, () -> reponseNomCourt.rechercherParNomCourt(null));
    }

    @Test
    void rechercherAcidesTest() {

        // Création de toutes les protéines humaines

        FabriquerProteine fabriquerProteine = new FabriquerProteine();
        Proteome proteome = fabriquerProteine.fabriquerProteines();

        // Recherche avec une chaine d'acides aminés

        Proteome reponseChaineAcidesAmines = proteome.rechercherParAcidesAmines("MKGQEGIRGEGCTDPEIKASPQMWAARFRGMRSRFSPLFSQATEMGPRVSAGWCLSGGGRKVSSLQGDFPPGGFWALSNDSALSLPPLSLPHPHPLRPPGLGVNEFTQGLHPPLHPAASVFQTCFYRKPHYCSTLRPTTT");
        Proteine p = new Proteine("NTAS1_HUMAN", "Putative uncharacterized protein NTM-AS1", "MKGQEGIRGEGCTDPEIKASPQMWAARFRGMRSRFSPLFSQATEMGPRVSAGWCLSGGGRKVSSLQGDFPPGGFWALSNDSALSLPPLSLPHPHPLRPPGLGVNEFTQGLHPPLHPAASVFQTCFYRKPHYCSTLRPTTT");
        for(Proteine proteine : reponseChaineAcidesAmines.getProteome()){
            assertEquals(p.toStringLong(), proteine.toStringLong(), String.valueOf(0.1));
        }

        // Recherche avec une chaine d'acides aminés, mais avec des nombres

        Proteome reponseChaineAcidesAminesNombres = proteome.rechercherParAcidesAmines("8F9A18P5H10R4C3W14S2N4M4K5E6Q8T13L3D4V16G2I2Y");
        p = new Proteine("NTAS1_HUMAN", "Putative uncharacterized protein NTM-AS1", "MKGQEGIRGEGCTDPEIKASPQMWAARFRGMRSRFSPLFSQATEMGPRVSAGWCLSGGGRKVSSLQGDFPPGGFWALSNDSALSLPPLSLPHPHPLRPPGLGVNEFTQGLHPPLHPAASVFQTCFYRKPHYCSTLRPTTT");
        for(Proteine proteine : reponseChaineAcidesAminesNombres.getProteome()){
            assertEquals(p.toStringLong(), proteine.toStringLong(), String.valueOf(0.1));
        }

        //Exeptions ''Mauvais caractères'' et ''String null''

        assertThrows(IllegalArgumentException.class, () -> proteome.rechercherParAcidesAmines("!:ÈÈ:..:::@@@@@@"));
        assertThrows(IllegalArgumentException.class, () -> proteome.rechercherParAcidesAmines(null));
    }
}