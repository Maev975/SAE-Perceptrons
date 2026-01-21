package Stats;

import Algo.AlgoClassification;
import chargement.Donnees;
import chargement.Imagette;

public class Statistiques {

    private AlgoClassification algo;
    private Donnees donneesTest;

    /**
     * Constructeur : initialise avec un algorithme et un jeu de test
     */
    public Statistiques(AlgoClassification algo, Donnees donneesTest) {
        this.algo = algo;
        this.donneesTest = donneesTest;
    }

    /**
     * Calcule le taux global de réussite (%)
     */
    public double calculerTauxReussite() {
        int nbCorrects = 0;
        Imagette[] tests = donneesTest.getImagettes();

        for (Imagette imgTest : tests) {
            int prediction = algo.predire(imgTest);
            if (prediction == imgTest.getLabel()) {
                nbCorrects++;
            }
        }

        double taux = (100.0 * nbCorrects) / tests.length;
        System.out.printf("Résultats corrects : %d / %d (%.2f%%)%n", nbCorrects, tests.length, taux);
        return taux;
    }

    /**
     * (Optionnel) Calcule le taux de réussite pour chaque chiffre (0–9)
     */
    public double[] calculerTauxParClasse() {
        int nbClasses = 10;
        int[] totalParClasse = new int[nbClasses];
        int[] correctParClasse = new int[nbClasses];

        for (Imagette imgTest : donneesTest.getImagettes()) {
            int vraiLabel = imgTest.getLabel();
            int prediction = algo.predire(imgTest);

            totalParClasse[vraiLabel]++;
            if (prediction == vraiLabel) {
                correctParClasse[vraiLabel]++;
            }
        }

        double[] tauxParClasse = new double[nbClasses];
        for (int i = 0; i < nbClasses; i++) {
            if (totalParClasse[i] > 0)
                tauxParClasse[i] = 100.0 * correctParClasse[i] / totalParClasse[i];
            else
                tauxParClasse[i] = 0.0;
        }

        // Affiche les résultats
        for (int i = 0; i < nbClasses; i++) {
            System.out.printf("Chiffre %d → %.2f%%%n", i, tauxParClasse[i]);
        }

        return tauxParClasse;
    }
}
