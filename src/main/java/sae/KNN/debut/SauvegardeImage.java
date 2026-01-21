package debut;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class SauvegardeImage {

    /**
     * Sauvegarde un tableau de pixels en image (PNG, JPEG, etc.)
     *
     * @param pixels  tableau 2D contenant les niveaux de gris [0–255]
     * @param nomFichier  nom du fichier de sortie (ex: "image0.png")
     * @throws Exception si erreur d’écriture
     */
    public static void sauverImage(int[][] pixels, String nomFichier) throws Exception {
        int hauteur = pixels.length;
        int largeur = pixels[0].length;

        BufferedImage image = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                int niveauGris = pixels[i][j] & 0xFF;
                int rgb = (niveauGris << 16) | (niveauGris << 8) | niveauGris;
                image.setRGB(j, i, rgb);
            }
        }

        File fichier = new File(nomFichier);
        ImageIO.write(image, "png", fichier);
    }

    public static void main(String[] args) {
        try {
            Imagette[] images = LecteurIDX3.lireFichierImages("train-images.idx3-ubyte");
            int[] labels = LecteurIDX1.lireFichierLabels("train-labels.idx1-ubyte");

            // Sauvegarde de la première image
            String nom = "mnist_image_" + labels[0] + ".png";
            sauverImage(images[0].getPixels(), nom);
            System.out.println("Image sauvegardée sous " + nom);

            //sauvegarde de la dernière image
            nom = "mnist_image_" + labels[labels.length - 1] + ".png";
            sauverImage(images[images.length - 1].getPixels(), nom);
            System.out.println("Image sauvegardée sous " + nom);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
