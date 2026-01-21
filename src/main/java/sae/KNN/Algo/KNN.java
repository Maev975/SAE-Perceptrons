package sae.KNN.Algo;

import sae.KNN.chargement.Donnees;
import sae.KNN.chargement.Imagette;
import java.util.*;

/**
 * Algorithme k-Plus Proches Voisins (k-NN)
 */
public class KNN extends AlgoClassification {

    private int k; // nombre de voisins

    public KNN(Donnees donnees, int k) {
        super(donnees);
        this.k = k;
    }

    @Override
    public int predire(Imagette test) {
        // Liste pour stocker toutes les distances
        List<DistanceLabel> distances = new ArrayList<>();

        // Calculer la distance entre test et chaque imagette d’entraînement
        for (Imagette trainImg : donnees.getImagettes()) {
            double dist = calculerDistance(test, trainImg);
            distances.add(new DistanceLabel(dist, trainImg.getLabel()));
        }

        // Trier par distance croissante
        distances.sort(Comparator.comparingDouble(d -> d.distance));

        // Prendre les k plus proches voisins
        Map<Integer, Integer> votes = new HashMap<>();
        for (int i = 0; i < k && i < distances.size(); i++) {
            int label = distances.get(i).label;
            votes.put(label, votes.getOrDefault(label, 0) + 1);
        }

        //Trouver le label majoritaire
        int labelMax = -1;
        int maxVote = -1;
        for (Map.Entry<Integer, Integer> entry : votes.entrySet()) {
            if (entry.getValue() > maxVote) {
                maxVote = entry.getValue();
                labelMax = entry.getKey();
            }
        }

        return labelMax;
    }

    /**
     * Calcul de la distance euclidienne entre deux imagettes
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

    /**
     * Petite classe interne pour stocker une distance et un label
     */
    private static class DistanceLabel {
        double distance;
        int label;

        DistanceLabel(double distance, int label) {
            this.distance = distance;
            this.label = label;
        }
    }
}
