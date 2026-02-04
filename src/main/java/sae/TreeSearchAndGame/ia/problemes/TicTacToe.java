package sae.TreeSearchAndGame.ia.problemes;

import java.util.ArrayList;
import java.util.Scanner;

import sae.TreeSearchAndGame.ia.framework.common.Action;
import sae.TreeSearchAndGame.ia.framework.common.State;
import sae.TreeSearchAndGame.ia.framework.jeux.Game;
import sae.TreeSearchAndGame.ia.framework.jeux.GameState;

/**
 * Classe qui represente le jeu du morpion 
 */
public class TicTacToe extends MnkGame {

    public TicTacToe() {
        super(3,3,3);
    }

    /**
     * {@inheritDoc}
     * <p>Crée une grille de morpion vide</p>
     */
    public GameState init(){
        TicTacToeState s = new TicTacToeState();
        s.updateGameValue();
        return s;
    }
    
    
    
}
