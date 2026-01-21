package sae.KNN.chargement;

import java.io.IOException;

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
     * Accès aux imagettes chargées
     */
    public Imagette[] getImagettes() {
        return imagettes;
    }
}
