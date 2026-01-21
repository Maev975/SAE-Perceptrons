package debut;

import java.io.*;

public class LecteurIDX1 {

    public static int[] lireFichierLabels(String cheminFichier) throws IOException {
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(cheminFichier)))) {

            int magicNumber = dis.readInt();
            if (magicNumber != 2049) {
                throw new IOException("Type de fichier incorrect (magic number = " + magicNumber + ")");
            }

            int nbLabels = dis.readInt();
            int[] labels = new int[nbLabels];

            // Lecture des labels
            for (int i = 0; i < nbLabels; i++) {
                labels[i] = dis.readUnsignedByte(); // valeur entre 0 et 9
            }

            return labels;
        }
    }

    public static void main(String[] args) {
        try {
            String chemin = "train-labels.idx1-ubyte"; // chemin du fichier
            int[] labels = lireFichierLabels(chemin);
            System.out.println("Nombre de labels lus : " + labels.length);
            System.out.println("Premier label : " + labels[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
