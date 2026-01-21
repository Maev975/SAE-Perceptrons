package Algo;

import chargement.Donnees;
import chargement.Imagette;

/**
 * Algorithme du plus proche voisin :
 * cherche l'imagette d'entraînement la plus proche
 * et renvoie son étiquette.
 */
public class PlusProche extends AlgoClassification {

    /**
     * Constructeur : on passe les données d'entraînement
     */
    public PlusProche(Donnees donnees) {
        super(donnees);
    }

    /**
     * Prédit l'étiquette d'une imagette test
     */
    @Override
    public int predire(Imagette test) {
        double distanceMin = Double.MAX_VALUE;
        int labelPred = -1;

        // Parcours de toutes les imagettes d'entraînement
        for (Imagette trainImg : donnees.getImagettes()) {
            double dist = calculerDistance(test, trainImg);

            if (dist < distanceMin) {
                distanceMin = dist;
                labelPred = trainImg.getLabel();
            }
        }

        return labelPred;
    }

    /**
     * Calcule la distance euclidienne entre deux imagettes
     */
    private double calculerDistance(Imagette a, Imagette b) {
        double somme = 0;

        for (int i = 0; i < a.getLignes(); i++) {
            for (int j = 0; j < a.getColonnes(); j++) {
                double diff = a.getValeur(i, j) - b.getValeur(i, j);
                somme += diff * diff;
            }
        }

        return Math.sqrt(somme);
    }
}
