import org.junit.jupiter.api.Test;
import sae.ia.Sigmoide;
import sae.ia.TangenteH;
import sae.ia.TransferFunction;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFonction {

    private static double[] input = new double[]{-1,-0.5,0,0.5,1};

    @Test
    void testSigmoideFonction(){
        TransferFunction transferFunction = new Sigmoide();
        double[] expected = new double[]{0.26894,0.37754,0.5,0.62246,0.73106};
        double[] res = new double[5];
        for(int i = 0; i < input.length; i++){
            res[i] = transferFunction.evaluate(input[i]);
        }
        assertAll(
                () -> assertEquals(expected[0], res[0],0.1),
                () -> assertEquals(expected[1], res[1],0.1),
                () -> assertEquals(expected[2], res[2],0.1),
                () -> assertEquals(expected[3], res[3],0.1),
                () -> assertEquals(expected[4], res[4],0.1)
        );
    }

    @Test
    void testSigmoideFonctionDer(){
        TransferFunction transferFunction = new Sigmoide();
        double[] expected = new double[]{0.19661,0.235,0.25,0.235,0.19661};
        double[] res = new double[5];
        for(int i = 0; i < input.length; i++){
            double ev = transferFunction.evaluate(input[i]);
            res[i] = transferFunction.evaluateDer(ev);
        }
        assertAll(
                () -> assertEquals(expected[0], res[0],0.1),
                () -> assertEquals(expected[1], res[1],0.1),
                () -> assertEquals(expected[2], res[2],0.1),
                () -> assertEquals(expected[3], res[3],0.1),
                () -> assertEquals(expected[4], res[4],0.1)
        );
    }

    @Test
    void testTanhFonction(){
        TransferFunction transferFunction = new TangenteH();
        double[] expected = new double[]{-0.76159,-0.46212,0,0.46212, 0.76159 };
        double[] res = new double[5];
        for(int i = 0; i < input.length; i++){
            res[i] = transferFunction.evaluate(input[i]);
        }
        assertAll(
                () -> assertEquals(expected[0], res[0],0.1),
                () -> assertEquals(expected[1], res[1],0.1),
                () -> assertEquals(expected[2], res[2],0.1),
                () -> assertEquals(expected[3], res[3],0.1),
                () -> assertEquals(expected[4], res[4],0.1)
        );
    }

    @Test
    void testTanhFonctionDer(){
        TransferFunction transferFunction = new TangenteH();
        double[] expected = new double[]{0.41997 ,0.78645,1,0.78645,0.41997 };
        double[] res = new double[5];
        for(int i = 0; i < input.length; i++){
            double ev = transferFunction.evaluate(input[i]);
            res[i] = transferFunction.evaluateDer(ev);
        }
        assertAll(
                () -> assertEquals(expected[0], res[0],0.1),
                () -> assertEquals(expected[1], res[1],0.1),
                () -> assertEquals(expected[2], res[2],0.1),
                () -> assertEquals(expected[3], res[3],0.1),
                () -> assertEquals(expected[4], res[4],0.1)
        );
    }
}
