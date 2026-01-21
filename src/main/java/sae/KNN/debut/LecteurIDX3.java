package debut;

import java.io.*;

public class LecteurIDX3 {

    public static Imagette[] lireFichierImages(String cheminFichier) throws IOException {
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(cheminFichier)))) {

            //entête
            int magicNumber = dis.readInt();
            if (magicNumber != 2051) {
                throw new IOException("Erreur :  nombre = " + magicNumber + ")");
            }

            int nbImages = dis.readInt();
            int nbLignes = dis.readInt();
            int nbColonnes = dis.readInt();

            Imagette[] images = new Imagette[nbImages];

            // Lecture de chaque image
            for (int n = 0; n < nbImages; n++) {
                Imagette img = new Imagette(nbLignes, nbColonnes);
                for (int i = 0; i < nbLignes; i++) {
                    for (int j = 0; j < nbColonnes; j++) {
                        int pixel = dis.readUnsignedByte();
                        img.setPixel(i, j, pixel);
                    }
                }
                images[n] = img;
            }

            return images;
        }
    }

    public static void main(String[] args) {
        try {
            String chemin = "train-images.idx3-ubyte";
            Imagette[] images = lireFichierImages(chemin);
            System.out.println("Nombre d'images chargées : " + images.length);
            System.out.println("Taille d'une image : " + images[0].getPixels().length + "x" + images[0].getPixels()[0].length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
