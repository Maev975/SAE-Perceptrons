import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sae.ia.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMLP {
    @Test
    public void testOUSigmoide() {
        double[][] tableOU = new double[4][];
        tableOU[0] = new double[]{0.0, 1.0, 1.0};
        tableOU[1] = new double[]{1.0, 0.0, 1.0};
        tableOU[2] = new double[]{0.0, 0.0, 0.0};
        tableOU[3] = new double[]{1.0, 1.0, 1.0};

        MLP mlp = new MLP(new int[]{2, 3, 1
}, 0.1, new sae.ia.Sigmoide());
        double res = mlp.apprentissage(10000, tableOU, 0.01);

        for (int i = 0; i < tableOU.length; i++) {
            double[] input = new double[]{tableOU[i][0], tableOU[i][1]};
            double[] output = mlp.execute(input);
            double expected = tableOU[i][2];
            assertEquals(expected, output[0], 0.1);
        }
    }

    @Test
    public void testOUTanh() {
        double[][] tableOU = new double[4][];
        tableOU[0] = new double[]{0.0, 1.0, 1.0};
        tableOU[1] = new double[]{1.0, 0.0, 1.0};
        tableOU[2] = new double[]{0.0, 0.0, 0.0};
        tableOU[3] = new double[]{1.0, 1.0, 1.0};

        MLP mlp = new MLP(new int[]{2, 3, 1
}, 0.1, new sae.ia.TangenteH());
        double res = mlp.apprentissage(10000, tableOU, 0.01);

        for (int i = 0; i < tableOU.length; i++) {
            double[] input = new double[]{tableOU[i][0], tableOU[i][1]};
            double[] output = mlp.execute(input);
            double expected = tableOU[i][2];
            assertEquals(expected, output[0], 0.1);
        }
    }

    @Test
    public void testETSigmoide() {
        double[][] tableET = new double[4][];
        tableET[0] = new double[]{0.0, 1.0, 0.0};
        tableET[1] = new double[]{1.0, 0.0, 0.0};
        tableET[2] = new double[]{0.0, 0.0, 0.0};
        tableET[3] = new double[]{1.0, 1.0, 1.0};

        MLP mlp = new MLP(new int[]{2, 3, 1}, 0.1, new sae.ia.Sigmoide());
        double res = mlp.apprentissage(10000, tableET, 0.01);

        for (int i = 0; i < tableET.length; i++) {
            double[] input = new double[]{tableET[i][0], tableET[i][1]};
            double[] output = mlp.execute(input);
            double expected = tableET[i][2];
            assertEquals(expected, output[0], 0.1);
        }
    }

    @Test
    public void testETTanh() {
        double[][] tableET = new double[4][];
        tableET[0] = new double[]{0.0, 1.0, 0.0};
        tableET[1] = new double[]{1.0, 0.0, 0.0};
        tableET[2] = new double[]{0.0, 0.0, 0.0};
        tableET[3] = new double[]{1.0, 1.0, 1.0};
        MLP mlp = new MLP(new int[]{2, 3, 1
}, 0.1, new sae.ia.TangenteH());
        double res = mlp.apprentissage(10000, tableET, 0.01);
        for (int i = 0; i < tableET.length; i++) {
            double[] input = new double[]{tableET[i][0], tableET[i][1]};
            double[] output = mlp.execute(input);
            double expected = tableET[i][2];
            assertEquals(expected, output[0], 0.1);
        }
    }

    @Test
    public void testXORSigmoide() {
        double[][] tableXOR = new double[4][];
        tableXOR[0] = new double[]{-1.0, 1.0, 1.0};
        tableXOR[1] = new double[]{1.0, -1.0, 1.0};
        tableXOR[2] = new double[]{-1.0, -1.0, 0.0};
        tableXOR[3] = new double[]{1.0, 1.0, 0.0};

        MLP mlp = new MLP(new int[]{2, 50, 1}, 0.1, new sae.ia.Sigmoide());
        double res = mlp.apprentissage(20000, tableXOR, 0.01);

        for (int i = 0; i < tableXOR.length; i++) {
            double[] input = new double[]{tableXOR[i][0], tableXOR[i][1]};
            double[] output = mlp.execute(input);
            double expected = tableXOR[i][2];
            assertEquals(expected, output[0], 0.1);
        }
    }

    @Test
    public void testXORTanh() {
        double[][] tableXOR = new double[4][];
        tableXOR[0] = new double[]{-1.0, 1.0, 1.0};
        tableXOR[1] = new double[]{1.0, -1.0, 1.0};
        tableXOR[2] = new double[]{-1.0, -1.0, 0.0};
        tableXOR[3] = new double[]{1.0, 1.0, 0.0};
        MLP mlp = new MLP(new int[]{2, 3, 1}, 0.1, new sae.ia.TangenteH());
        double res = mlp.apprentissage(20000, tableXOR, 0.01);
        for (int i = 0; i < tableXOR.length; i++) {
            double[] input = new double[]{tableXOR[i][0], tableXOR[i][1]};
            double[] output = mlp.execute(input);
            double expected = tableXOR[i][2];
            assertEquals(expected, output[0], 0.1);
        }
    }
}