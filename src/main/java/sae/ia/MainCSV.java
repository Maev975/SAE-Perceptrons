package sae.ia;

import sae.KNN.Algo.KNN;
import sae.KNN.chargement.Donnees;
import sae.KNN.chargement.Imagette;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainCSV {

    static final String PATH_TRAIN_IMG = "src/main/java/sae/KNN/train-images.idx3-ubyte";
    static final String PATH_TRAIN_LBL = "src/main/java/sae/KNN/train-labels.idx1-ubyte";
    static final String PATH_TEST_IMG = "src/main/java/sae/KNN/t10k-images.idx3-ubyte";
    static final String PATH_TEST_LBL = "src/main/java/sae/KNN/t10k-labels.idx1-ubyte";

    public static void main(String[] args) throws IOException {
        //Paramètres
        int[] taillesEntrainement = {500, 1000, 2000, 3000, 4000, 5000};
        int nbTest = 500;

        String csvFile = "resultats_comparaison.csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {

            writer.println("TailleTrain;Algo;Parametre;tauxR;TempsMS");

            for (int nbTrain : taillesEntrainement) {
                System.out.println("\nTest avec " + nbTrain + " images d'entrainement");

                Donnees trainData = new Donnees(PATH_TRAIN_IMG, PATH_TRAIN_LBL, nbTrain);
                Donnees testData = new Donnees(PATH_TEST_IMG, PATH_TEST_LBL, nbTest);

                //MLP
                int neuronesCaches = 128;
                MLP mlp = new MLP(new int[]{784, neuronesCaches, 10}, 0.1, new Sigmoide());

                long t0 = System.currentTimeMillis();
                mlp.apprentissageImg(30, trainData, 0.001);
                long t1 = System.currentTimeMillis();

                double accMLP = mlp.evaluer(testData);
                writer.println(nbTrain + ";MLP;" + neuronesCaches + "hidden;" + accMLP + ";" + (t1 - t0));
                System.out.println("MLP terminé: " + accMLP + "%");

                //knn
                int k = 3;
                long t2 = System.currentTimeMillis();
                KNN knn = new KNN(trainData, k);

                int correct = 0;
                for (Imagette im : testData.getImagettes()) {
                    if (knn.predire(im) == im.getLabel()) correct++;
                }
                long t3 = System.currentTimeMillis();

                double accKNN = (double) correct / nbTest * 100.0;
                writer.println(nbTrain + ";KNN;k=" + k + ";" + accKNN + ";" + (t3 - t2));
                System.out.println("KNN fin : " + accKNN + "%");

                writer.flush();
            }
        }
        System.out.println("\nfini dans :  " + csvFile);
    }
}