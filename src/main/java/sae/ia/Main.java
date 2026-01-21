package sae.ia;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        int[] layer = new int[2];
        layer[0] = 2;
        layer[1] = 1;
        TransferFunction transferFunction = new Sigmoide();
        MLP mlp = new MLP(layer, 0.01, transferFunction);
        System.out.println(mlp.apprentissage(200,new double[]{0.0,1.0,1.0}));
    }
}
