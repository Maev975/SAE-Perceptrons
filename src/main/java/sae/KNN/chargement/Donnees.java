package sae.KNN.chargement;

import java.io.IOException;
import java.util.Random;

public class Donnees {
    private Imagette[] imagettes;

    /**
     * Constructeur principal : charge les fichiers MNIST complets
     */
    public Donnees(String fichierImages, String fichierLabels) throws IOException {
        this.imagettes = ChargementMNIST.charger(fichierImages, fichierLabels,1000);
    }

    /**
     * Constructeur optionnel : charge seulement nb imagettes (pour tests rapides)
     */
    public Donnees(String fichierImages, String fichierLabels, int nb) throws IOException {
        this.imagettes = ChargementMNIST.charger(fichierImages, fichierLabels, nb);
    }

    /**
     * Mélange aléatoirement les imagettes (Fisher-Yates)
     */
    public void melanger() {
        if (this.imagettes == null) return;
        Random rnd = new Random();
        for (int i = this.imagettes.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            Imagette tmp = this.imagettes[i];
            this.imagettes[i] = this.imagettes[j];
            this.imagettes[j] = tmp;
        }
    }

    /**
     * Accès aux imagettes chargées
     */
    public Imagette[] getImagettes() {
        return imagettes;
    }
}
