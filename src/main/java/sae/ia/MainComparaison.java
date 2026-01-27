package sae.ia;

import sae.KNN.Algo.KNN;
import sae.KNN.chargement.Donnees;
import sae.KNN.chargement.Imagette;
import java.io.IOException;

public class MainComparaison {

    public static void main(String[] args) throws IOException {
        //config
        int nbTrain = 2000;
        int nbTest = 500;

        System.out.println("Chargement des données MNIST...");
        Donnees trainData = new Donnees("src/main/java/sae/KNN/train-images.idx3-ubyte",
                "src/main/java/sae/KNN/train-labels.idx1-ubyte", nbTrain);
        Donnees testData = new Donnees("src/main/java/sae/KNN/t10k-images.idx3-ubyte",
                "src/main/java/sae/KNN/t10k-labels.idx1-ubyte", nbTest);

        //perceptron
        System.out.println("\n--- Phase MLP ---");
        int[] layers = {784, 128, 10}; // 128 neurones cachés
        MLP mlp = new MLP(layers, 0.1, new Sigmoide());

        long startMLP = System.currentTimeMillis();
        //relancement en fonction des erreurs précédentes
        double erreurFinale = mlp.apprentissageImg(30, trainData, 0.001);
        long endMLP = System.currentTimeMillis();

        double moyenneMLP = mlp.evaluer(testData);
        System.out.println("Apprentissage terminé (Erreur: " + String.format("%.4f", erreurFinale) + ")");
        System.out.println("Moyenne MLP: " + moyenneMLP + "%");
        System.out.println("Temps MLP: " + (endMLP - startMLP) + "ms");

        //KNN
        System.out.println("\n--- Phase KNN ---");
        int k = 3;
        KNN knn = new KNN(trainData, k);
        long startKNN = System.currentTimeMillis();
        int correctKNN = 0;
        Imagette[] testImages = testData.getImagettes();

        for (Imagette img : testImages) {
            if (knn.predire(img) == img.getLabel()) {
                correctKNN++;
            }
        }
        long endKNN = System.currentTimeMillis();

        double moyenneKNN = (double) correctKNN / testImages.length * 100.0;
        System.out.println("Accuracy KNN (k=" + k + "): " + moyenneKNN + "%");
        System.out.println("Temps KNN: " + (endKNN - startKNN) + "ms");

        //tout
        System.out.println("\n--- Résumé ---");
        System.out.printf("MLP: %.2f%% | KNN: %.2f%%\n", moyenneMLP, moyenneKNN);
    }
}