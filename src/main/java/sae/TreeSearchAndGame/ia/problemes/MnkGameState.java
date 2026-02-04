package sae.TreeSearchAndGame.ia.problemes;

import java.util.Arrays;
import java.util.ArrayList;


/**
 * Représente un état d'un jeu générique m,n,k Game
 */

public class MnkGameState extends AbstractMnkGameState {


    /**
     * Construire une grille vide de la bonne taille
     *
     * @param r nombre de lignes
     * @param c nombre de colonnes
     */
    public MnkGameState(int r, int c, int s) {
        super(r,c,s);
    }

    public MnkGameState cloneState() {
        MnkGameState new_s = new MnkGameState(this.rows, this.cols, this.streak);
        new_s.board = this.board.clone();
        new_s.player_to_move = player_to_move;
        new_s.game_value = game_value;
        if(this.last_action != null)
            new_s.last_action = this.last_action.clone();
        for (Pair p: this.winning_move)
            new_s.winning_move.add(p.clone());
        return new_s;
    }
    /**
     * Un fonction d'évaluation pour cet état du jeu.
     * Permet de comparer différents états dans le cas ou on ne
     * peut pas développer tout l'arbre. Le joueur 1 (X) choisira les
     * actions qui mènent au état de valeur maximal, Le joueur 2 (O)
     * choisira les valeurs minimal.
     *
     * Cette fonction dépend du jeu.
     *
     * @return la valeur du jeux
     **/
    /**
     * {@inheritDoc}
     *
     * <p>Une fonction d'évaluation simple pour le morpion.
     * La valeur = le nombre de lignes possibles pour X moins
     * le nombre de lignes possibles pour O</p>
     *
     * </p>Une valeur nulle indique que le jeux est équilibré, une valeur positif
     * indique une situation favorable pour X et inversement pour une valeur
     * négative.</p>
     *
     * @return la valeur du jeu
     **/

    /** Ancienne version
    protected double evaluationFunction(){

        int pos_x = this.possibleLines(X);
        int pos_o = this.possibleLines(O);

        double value = pos_x-pos_o;

        return value;
    }

    // API privée

    // compte le nombre de lignes possibles pour player
    private int possibleLines(int player){

        return this.possibleVerticalLines(player) +
                this.possibleHorizontalLines(player) +
                this.possibleDiagonalLines(player) ;

    }


    private int possibleVerticalLines(int player){

        int res = 0;
        for(int c=0; c<this.cols; c++)
            for(int r=0; r<=this.rows-this.streak; r++){
                int counter = 0;
                for(int k=0; k<this.streak; k++){
                    if( this.getValueAt(r+k,c) == this.otherPlayer(player) ) {
                        counter = 0;
                        break;
                    }
                    else if( this.getValueAt(r+k,c) == player )
                        counter ++;
                    else // vide
                        counter ++;
                }
                if( counter > 0 )
                    res ++;
            }

        return res;
    }

    private int possibleHorizontalLines(int player){

        int res = 0;
        for(int r=0; r<this.rows;r++)
            for(int c=0; c<=this.cols-this.streak; c++){
                int counter = 0;
                for(int k=0; k<this.streak; k++){
                    if( this.getValueAt(r,c+k) == this.otherPlayer(player) ) {
                        counter = 0;
                        break;
                    }
                    else if( this.getValueAt(r,c+k) == player )
                        counter ++;
                    else
                        counter ++;
                }
                if( counter > 0 )
                    res ++;
            }
        return res;
    }

    // compte le nombre de lignes diagonales possibles pour player
    private int possibleDiagonalLines(int player){


        int res = 0;

        // 45 deg
        for(int c=0; c<=this.cols-this.streak; c++)
            for(int r=0; r<=this.rows-this.streak; r++){
                int counter = 0;
                for(int k=0; k<this.streak; k++){
                    if( this.getValueAt(r+k,c+k) == this.otherPlayer(player) ) {
                        counter = 0;
                        break; // plus besoin de continuer
                    }
                    else if( this.getValueAt(r+k,c+k) == player )
                        counter ++;
                    else // vide
                        counter ++;
                }
                if( counter > 0 )
                    res ++; // une ligne possible de plus
            }

        // -45 deg
        for(int c=0; c<=this.cols-this.streak; c++)
            for(int r=this.streak-1; r<this.rows; r++){
                int counter = 0;
                for(int k=0; k<this.streak; k++){
                    if( this.getValueAt(r-k,c+k) == this.otherPlayer(player) ) {
                        counter = 0;
                        break; // plus besoin de continuer
                    }
                    else if( this.getValueAt(r-k,c+k) == player )
                        counter ++;
                    else // vide
                        counter ++;
                }
                if( counter > 0 )
                    res ++;
            }
        return res;
    }


**/

    /** Nouvelle version **/
    protected double evaluationFunction() {
    if (this.isFinalState()) {
        return this.getGameValue();
    }

    double totalScore = 0;
// Évaluer les lignes
    for (int r = 0; r < rows; r++) {
        for (int c = 0; c <= cols - streak; c++) {
            totalScore += evalLine(r, c, 0, 1);
        }
    }
// Évaluer les colonnes
    for (int r = 0; r <= rows - streak; r++) {
        for (int c = 0; c < cols; c++) {
            totalScore += evalLine(r, c, 1, 0);
        }
    }

 // Évaluer les diagonales descendantes
    for (int r = 0; r <= rows - streak; r++) {
        for (int c = 0; c <= cols - streak; c++) {
            totalScore += evalLine(r, c, 1, 1);
        }
    }
// Évaluer les diagonales montantes
    for (int r = streak - 1; r < rows; r++) {
        for (int c = 0; c <= cols - streak; c++) {
            totalScore += evalLine(r, c, -1, 1);
        }
    }

    return totalScore;
}

    /**
     * Évalue une ligne spécifique sur le plateau.
     * @param r : ligne de départ
     * @param c : colonne de départ
     * @param dr : direction ligne
     * @param dc: direction colonne
     * @return la valeur de la ligne
     */
    private double evalLine(int r, int c, int dr, int dc) {
        int countP1 = 0;
        int countP2 = 0;

        // Compter les X et O dans la ligne
        for (int i = 0; i < streak; i++) {
            char val = getValueAt(r + (i * dr), c + (i * dc));
            if (val == X) {
                countP1++;
            } else if (val == O) {
                countP2++;
            }
        }


        if (countP1 > 0 && countP2 > 0) return 0; // Ligne bloquée donc ne compte pas

        if (countP1 > 0) return Math.pow(10, countP1); // Plus le nombre de pièces est grand, plus la valeur est élevée
        if (countP2 > 0) return -Math.pow(10, countP2); // La même chose pour le joueur 2

        return 0;
    }

}
