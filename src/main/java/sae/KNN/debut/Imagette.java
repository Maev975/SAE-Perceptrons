package sae.KNN.debut;

public class Imagette {
    private int[][] pixels;

    public Imagette(int lignes, int colonnes) {
        pixels = new int[lignes][colonnes];
    }

    public void setPixel(int i, int j, int valeur) {
        pixels[i][j] = valeur;
    }

    public int[][] getPixels() {
        return pixels;
    }
}
