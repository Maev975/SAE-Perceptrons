import Algo.AlgoClassification;
import Algo.KNN;
import Stats.Statistiques;
import chargement.ChargementMNIST;
import chargement.Donnees;
import chargement.Imagette;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Imagette[] tab = ChargementMNIST.charger("train-images.idx3-ubyte", "train-labels.idx1-ubyte", 2);

        tab[0].sauverImage("test0.png");
        tab[1].sauverImage("test1.png");
        //System.out.println( tab[0].getLabel());

        Donnees train = new Donnees("train-images.idx3-ubyte", "train-labels.idx1-ubyte");
        Donnees test = new Donnees("t10k-images.idx3-ubyte", "t10k-labels.idx1-ubyte");

        // Création de l’algorithme PlusProche
        AlgoClassification algo = new KNN(train,5);

        // Calcul des statistiques
        Statistiques stats = new Statistiques(algo, test);
        stats.calculerTauxReussite();
        stats.calculerTauxParClasse();

    }
}
// PlusProche 1
//Résultats corrects : 828 / 1000 (82,80%)
//Chiffre 0 → 94,12%
//Chiffre 1 → 100,00%
//Chiffre 2 → 79,31%
//Chiffre 3 → 71,03%
//Chiffre 4 → 71,82%
//Chiffre 5 → 72,41%
//Chiffre 6 → 93,10%
//Chiffre 7 → 89,90%
//Chiffre 8 → 65,17%
//Chiffre 9 → 89,36%

// PlusProche 3
//Résultats corrects : 814 / 1000 (81,40%)
//Chiffre 0 → 96,47%
//Chiffre 1 → 100,00%
//Chiffre 2 → 69,83%
//Chiffre 3 → 73,83%
//Chiffre 4 → 71,82%
//Chiffre 5 → 77,01%
//Chiffre 6 → 91,95%
//Chiffre 7 → 87,88%
//Chiffre 8 → 59,55%
//Chiffre 9 → 85,11%