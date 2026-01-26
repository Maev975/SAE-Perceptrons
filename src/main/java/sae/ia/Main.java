package sae.ia;

public class Main {
    static void main() {

        int[] layer = new int[3];
        layer[0] = 2;
        layer[1] = 2;
        layer[2] = 2;


        double[] ou1 = new double[]{0.0, 1.0, 0.0,1.0};
        double[] ou2 = new double[]{1.0, 0.0, 0.0,1.0};
        double[] ou3 = new double[]{0.0, 0.0, 1.0,0.0};
        double[] ou4 = new double[]{1.0, 1.0, 0.0,1.0};

        double[][] tableOU = new double[4][];
        tableOU[0] = ou1;
        tableOU[1] = ou2;
        tableOU[2] = ou3;
        tableOU[3] = ou4;

        double[] et1 = new double[]{0.0, 1.0,1.0, 0.0};
        double[] et2 = new double[]{1.0, 0.0,1.0, 0.0};
        double[] et3 = new double[]{0.0, 0.0,1.0, 0.0};
        double[] et4 = new double[]{1.0, 1.0,0.0, 1.0};

        double[][] tableET = new double[4][];
        tableET[0] = et1;
        tableET[1] = et2;
        tableET[2] = et3;
        tableET[3] = et4;

        double[] xor1 = new double[]{0.0, 1.0, 0.0,1.0};
        double[] xor2 = new double[]{1.0, 0.0, 0.0,1.0};
        double[] xor3 = new double[]{0.0, 0.0,1.0, 0.0};
        double[] xor4 = new double[]{1.0, 1.0,1.0, 0.0};

        double[][] tableXOR = new double[4][];
        tableXOR[0] = xor1;
        tableXOR[1] = xor2;
        tableXOR[2] = xor3;
        tableXOR[3] = xor4;

        double[][] inputs = new double[4][2];
        double[] input1 = new double[]{0,0};
        double[] input2 = new double[]{0,1};
        double[] input3 = new double[]{1,0};
        double[] input4 = new double[]{1,1};

        inputs[0] = input1;
        inputs[1] = input2;
        inputs[2] = input3;
        inputs[3] = input4;


        double seuil = 0.01;
        TransferFunction transferFunction = new Sigmoide();
        MLP mlp = new MLP(layer, 0.01, transferFunction);

        //Apprentissage
        mlp.apprentissage(100000, tableXOR, seuil);


        for(double[] input : inputs){
            double[] res = mlp.execute(input);
            System.out.println(
                    "Execute sur " + input[0] + " ; " + input[1] +
                            " : [" + res[0] + ", " + res[1] + "]"
            );
        }
    }
}
