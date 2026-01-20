package sae.ia;

public class Sigmoide implements TransferFunction {
    @Override
    public double evaluate(double value) {
        double sig = 1 / (1 + Math.exp(-value));
        return sig;
    }

    public double evaluateDer(double value) {
         double derO = value - Math.pow(value, 2) ;
         return derO;
    }


}
