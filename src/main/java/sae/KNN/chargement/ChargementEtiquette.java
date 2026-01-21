package sae.KNN.chargement;

import java.io.IOException;

import sae.KNN.debut.LecteurIDX1;

public class ChargementEtiquette {
    /**
     * donne le nom de fichier, retourne un tableau d'etiquettes
     * @param labelFile nom du fichier avec les labels
     * @param max nombre de valeurs maximales a charger
     * @return tableau des etiquettes
     * @throws IOException probleme de lecture
     */
    public static int[] charger(String labelFile, int max) throws IOException {
        return LecteurIDX1.lireFichierLabels(labelFile);
    }
}
