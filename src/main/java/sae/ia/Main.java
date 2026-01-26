package sae.ia;

import sae.KNN.Algo.AlgoClassification;
import sae.KNN.Algo.KNN;
import sae.KNN.Stats.Statistiques;
import sae.KNN.chargement.ChargementMNIST;
import sae.KNN.chargement.Donnees;
import sae.KNN.chargement.Imagette;

import java.io.IOException;

public class Main {
    static void main() throws IOException {
        main2();
    }

    private static void extracted() {
        int[] layer = new int[3];
        layer[0] = 2;
        layer[1] = 2;
        layer[2] = 10;


        double[] ou1 = new double[]{0.0, 1.0, 0.0, 1.0};
        double[] ou2 = new double[]{1.0, 0.0, 0.0, 1.0};
        double[] ou3 = new double[]{0.0, 0.0, 1.0, 0.0};
        double[] ou4 = new double[]{1.0, 1.0, 0.0, 1.0};

        double[][] tableOU = new double[4][];
        tableOU[0] = ou1;
        tableOU[1] = ou2;
        tableOU[2] = ou3;
        tableOU[3] = ou4;

        double[] et1 = new double[]{0.0, 1.0, 1.0, 0.0};
        double[] et2 = new double[]{1.0, 0.0, 1.0, 0.0};
        double[] et3 = new double[]{0.0, 0.0, 1.0, 0.0};
        double[] et4 = new double[]{1.0, 1.0, 0.0, 1.0};

        double[][] tableET = new double[4][];
        tableET[0] = et1;
        tableET[1] = et2;
        tableET[2] = et3;
        tableET[3] = et4;

        double[] xor1 = new double[]{0.0, 1.0, 0.0, 1.0};
        double[] xor2 = new double[]{1.0, 0.0, 0.0, 1.0};
        double[] xor3 = new double[]{0.0, 0.0, 1.0, 0.0};
        double[] xor4 = new double[]{1.0, 1.0, 1.0, 0.0};

        double[][] tableXOR = new double[4][];
        tableXOR[0] = xor1;
        tableXOR[1] = xor2;
        tableXOR[2] = xor3;
        tableXOR[3] = xor4;

        double[][] inputs = new double[4][2];
        double[] input1 = new double[]{0, 0};
        double[] input2 = new double[]{0, 1};
        double[] input3 = new double[]{1, 0};
        double[] input4 = new double[]{1, 1};

        inputs[0] = input1;
        inputs[1] = input2;
        inputs[2] = input3;
        inputs[3] = input4;


        double seuil = 0.01;
        TransferFunction transferFunction = new Sigmoide();
        MLP mlp = new MLP(layer, 0.01, transferFunction);

        //Apprentissage
//        mlp.apprentissage(100000, tableXOR, seuil);


        for (double[] input : inputs) {
            double[] res = mlp.execute(input);
            System.out.println(
                    "Execute sur " + input[0] + " ; " + input[1] +
                            " : [" + res[0] + ", " + res[1] + "]"
            );
        }
    }

    public static void main2() throws IOException {

        Donnees train = new Donnees("src/main/java/sae/KNN/train-images.idx3-ubyte", "src/main/java/sae/KNN/train-labels.idx1-ubyte");
        Donnees test = new Donnees("src/main/java/sae/KNN/t10k-images.idx3-ubyte", "src/main/java/sae/KNN/t10k-labels.idx1-ubyte");


        int[] layer = new int[3];
        layer[0] = 28 * 28;
        layer[1] = 184;
        layer[2] = 10;

        double seuil = 0.01;
        TransferFunction transferFunction = new TangenteH();
        MLP mlp = new MLP(layer, 0.01, transferFunction);

        double resApp = mlp.apprentissageImg(10000, train, seuil);

        double moy = 0;
        for (int i = 0; i < train.getImagettes().length; i++) {
            double[] input = flatten(train.getImagettes()[i]);
            double[] res = mlp.execute(input);
            check(train.getImagettes()[i].getLabel(), res);
        }
        System.out.println(moy/1000);
    }

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

    public static void check(int expected, double[] actual){
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
