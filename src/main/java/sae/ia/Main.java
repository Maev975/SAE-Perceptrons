package sae.ia;

import java.io.IOException;

import sae.KNN.Algo.KNN;
import sae.KNN.chargement.ChargementMNIST;
import sae.KNN.chargement.Donnees;
import sae.KNN.chargement.Imagette;

public class Main {

    // ======================= LES PARAMETRES =======================

    private static final String MODE_DEFAUT = "mlp";

    // KNN
    private static final int K_KNN = 3;
    private static final int NB_TRAIN_KNN = 10000;

    // Données de test
    private static final int NB_TEST = 100;

    // MLP
    private static final int NB_TRAIN_MLP = 10000;
    private static final double LEARNING_RATE = 0.01;
    private static final int[] COUCHES_MLP = {784,64, 10};

    public static void main(String[] args) {
        String mode = (args.length > 0) ? args[0].toLowerCase() : MODE_DEFAUT;

        try {
            if ("knn".equals(mode)) {
                executerKNN();
            } else {
                executerMLP();
            }
        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    // ======================= KNN =======================

    public static void executerKNN() throws IOException {
        System.out.println("=== MODE : KNN (k=" + K_KNN + ") ===");

        Donnees train = new Donnees("src/main/java/sae/KNN/train-images.idx3-ubyte", "src/main/java/sae/KNN/train-labels.idx1-ubyte",NB_TRAIN_KNN);
        Imagette[] testImages = ChargementMNIST.charger("src/main/java/sae/KNN/t10k-images.idx3-ubyte","src/main/java/sae/KNN/t10k-labels.idx1-ubyte",NB_TEST);

        KNN knn = new KNN(train, K_KNN);

        long startTime = System.currentTimeMillis();
        int compteurR = 0;

        System.out.println("Evaluation base de test ...");
        for (int i = 0; i < testImages.length; i++) {
            if (knn.predire(testImages[i]) == testImages[i].getLabel()) {
                compteurR++;
            }
        }

        long totalTime = System.currentTimeMillis() - startTime;
        double accuracy = (double) compteurR / testImages.length * 100;

        System.out.println("\n--- RESULTATS FINAUX KNN ---");
        System.out.printf("Taux de réussite :" + accuracy + "\n");
        System.out.println("Temps d'exécution : " + totalTime + " ms");
        System.out.println("------------------------------\n");
    }

    // ======================= MLP =======================

    public static void executerMLP() throws IOException {
        System.out.println("=== MODE : MLP (Sigmoide) ===");

        Donnees train = new Donnees("src/main/java/sae/KNN/train-images.idx3-ubyte","src/main/java/sae/KNN/train-labels.idx1-ubyte");
        Imagette[] testImages = ChargementMNIST.charger("src/main/java/sae/KNN/t10k-images.idx3-ubyte","src/main/java/sae/KNN/t10k-labels.idx1-ubyte",NB_TEST);

        MLP mlp = new MLP(COUCHES_MLP, LEARNING_RATE, new Sigmoide());

        long startTime = System.currentTimeMillis();

        System.out.println("Apprentissage (" + NB_TRAIN_MLP + " images)...");
        mlp.apprentissageImg(NB_TRAIN_MLP, train, LEARNING_RATE);

        System.out.println("Evaluation base de test ...");
        int reussites = 0;

        for (int i = 0; i < testImages.length; i++) {
            Imagette img = testImages[i];

            double[] input = flatten(img);
            double[] output = mlp.execute(input);

            check(img.getLabel(), output);

            if (getPrediction(output) == img.getLabel()) {
                reussites++;
            }
        }

        long totalTime = System.currentTimeMillis() - startTime;
        double accuracy = (double) reussites / testImages.length * 100;

        System.out.println("\n--- RÉSULTATS FINAUX MLP ---");
        System.out.printf("Taux de réussite :" + accuracy + "\n");
        System.out.println("Temps d'exécution : " + totalTime + " ms");
        System.out.println("------------------------------\n");
    }

    // ======================= OUTILS TECHNIQUES =======================

    public static double[] flatten(Imagette image){
        int[][] img = image.getTab();
        double[] input = new double[28 * 28];

        int k = 0;
        for (int x = 0; x < 28; x++) {
            for (int y = 0; y < 28; y++) {
                input[k++] = img[x][y] / 255.0;
            }
        }
        return input;
    }

    public static int getPrediction(double[] output) {
        int maxIndex = 0;
        for (int i = 1; i < output.length; i++) {
            if (output[i] > output[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void check(int expected, double[] actual) {
        boolean trouve = false;
        for(int i = 0; i < actual.length; i++){
            if(actual[i] >= 0.90){
                trouve = true;
            }
        }
        String res = "";
        for(int i = 0; i < actual.length; i++){
            res += actual[i] + "; ";
        }
        if(!trouve){
            System.out.println("PAS TROUVE : " + expected + " : " + res);
        }
    }
}
