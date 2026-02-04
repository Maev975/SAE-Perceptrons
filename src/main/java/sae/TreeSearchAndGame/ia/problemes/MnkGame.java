package sae.TreeSearchAndGame.ia.problemes;

import java.util.ArrayList;
import java.util.Scanner;

import sae.TreeSearchAndGame.ia.framework.common.Action;
import sae.TreeSearchAndGame.ia.framework.common.State;
import sae.TreeSearchAndGame.ia.framework.jeux.Game;
import sae.TreeSearchAndGame.ia.framework.jeux.GameState;

/**
 * Représente un jeux du m,n,k (cf. https://en.wikipedia.org/wiki/M,n,k-game)
 *
 */

public class MnkGame extends AbstractMnkGame {
        
    public MnkGame(int r, int c, int s) {
        super(r,c,s);
    }

    /* {@inheritDoc}
     * <p>Crée une grille vide</p>
     */
    public GameState init(){
        MnkGameState s = new MnkGameState(this.rows, this.cols, this.streak);
        s.updateGameValue();
        return s;
    }
    
}
