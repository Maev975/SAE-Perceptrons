package Algo;

import chargement.Donnees;
import chargement.Imagette;

public abstract class AlgoClassification {

    protected Donnees donnees;

    // Constructeur
    public AlgoClassification(Donnees donnees) {
        this.donnees = donnees;
    }

    public abstract int predire(Imagette img);

    // (Optionnel) : méthode pour évaluer la qualité du modèle
    public double tauxDeReussite(Donnees test) {
        int correct = 0;
        Imagette[] testImgs = test.getImagettes();
        for (Imagette imgTest : testImgs) {
            int prediction = predire(imgTest);
            if (prediction == imgTest.getLabel()) {
                correct++;
            }
        }
        return 100.0 * correct / testImgs.length;
    }
}
