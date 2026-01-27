package sae.ia;

import sae.KNN.Algo.KNN;
import sae.KNN.chargement.Donnees;
import sae.KNN.chargement.Imagette;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;


public class MainCSV {

    static final String PATH_TRAIN_IMG = "src/main/java/sae/KNN/train-images.idx3-ubyte";
    static final String PATH_TRAIN_LBL = "src/main/java/sae/KNN/train-labels.idx1-ubyte";
    static final String PATH_TEST_IMG = "src/main/java/sae/KNN/t10k-images.idx3-ubyte";
    static final String PATH_TEST_LBL = "src/main/java/sae/KNN/t10k-labels.idx1-ubyte";

    public static void main(String[] args) throws IOException {
        //Paramètres
        int[] taillesEntrainement = {5000, 10000, 15000, 20000, 25000, 30000, 35000, 40000, 45000, 50000,55000, 60000};
        int nbTest = 5000;

        String csvFile = "resultats_comparaisonT.csv";

        System.out.println("début");

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            writer.println("TailleTrain;Algo;Parametre;TauxReussite;TempsApprentissageMS;TempsPredictionMS");

            for (int nbTrain : taillesEntrainement) {
                System.out.println("Test avec " + nbTrain + " images d'entrainement");

                Donnees trainData = new Donnees(PATH_TRAIN_IMG, PATH_TRAIN_LBL, nbTrain);
                Donnees testData = new Donnees(PATH_TEST_IMG, PATH_TEST_LBL, nbTest);

                //MLP
                System.out.print("MLP en cours");
                int neuronesCaches = 128;
                MLP mlp = new MLP(new int[]{784, neuronesCaches, 10}, 0.01, new TangenteH());

                //temps apprentissage
                long t0_MLP = System.currentTimeMillis();
                mlp.apprentissageImg(30, trainData, 0.001);
                long t1_MLP = System.currentTimeMillis();
                long tempsAppMLP = t1_MLP - t0_MLP;

                //temps prédiction
                long t2_MLP = System.currentTimeMillis();
                double accMLP = mlp.evaluer(testData);
                long t3_MLP = System.currentTimeMillis();
                long tempsPredMLP = t3_MLP - t2_MLP;

                writer.printf(Locale.US, "%d;MLP;%d_caches;%.2f;%d;%d\n",
                        nbTrain, neuronesCaches, accMLP, tempsAppMLP, tempsPredMLP);
                System.out.println("\nTerminé (Acc: " + accMLP + "%)");

                //knn
                System.out.print("KNN en cours");
                int k = 3;

                //temps apprentissage donc presque null
                long t0_KNN = System.currentTimeMillis();
                KNN knn = new KNN(trainData, k);
                long t1_KNN = System.currentTimeMillis();
                long tempsAppKNN = t1_KNN - t0_KNN;

                //prédiction
                long t2_KNN = System.currentTimeMillis();
                int correct = 0;
                Imagette[] imagesTest = testData.getImagettes();
                for (Imagette im : imagesTest) {
                    if (knn.predire(im) == im.getLabel()) {
                        correct++;
                    }
                }
                long t3_KNN = System.currentTimeMillis();
                long tempsPredKNN = t3_KNN - t2_KNN;
                double accKNN = (double) correct / nbTest * 100.0;

                writer.printf(Locale.US, "%d;KNN;k=%d;%.2f;%d;%d\n",
                        nbTrain, k, accKNN, tempsAppKNN, tempsPredKNN);
                System.out.println("\nTerminé (Acc: " + accKNN + "%)");

                writer.flush();
            }
        } catch (Exception e) {
            System.err.println("Erreur durant l'exécution : " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\nfin");
    }
}