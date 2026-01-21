package sae.ia;

public class TangenteH implements TransferFunction {

    @Override
    public double evaluate(double value) {
        double sigma = Math.tanh(value);
        return sigma;
    }

    @Override
    public double evaluateDer(double value) {
        double sigmader = 1- Math.pow(evaluate(value),2);
        return sigmader;
    }

}