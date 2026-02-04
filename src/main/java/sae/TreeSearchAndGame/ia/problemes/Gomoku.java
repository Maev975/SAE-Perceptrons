package sae.TreeSearchAndGame.ia.problemes;

import java.util.Arrays;
import java.util.ArrayList;

sae.TreeSearchAndGame.iaframework.common.Action;
sae.TreeSearchAndGame.iaframework.common.State;
sae.TreeSearchAndGame.iaframework.jeux.GameState;

/**
 * Représente un état du jeu du Gomoku 
 *
 */

public class Gomoku extends AbstractMnkGame {
    

    public Gomoku() {
        super(15,15,5);
    }

    /**
     * {@inheritDoc}
     * <p>Crée une grille vide</p>
     */
    public GameState init(){
        GomokuState s = new GomokuState();
        s.updateGameValue();
        return s;
    }

}
