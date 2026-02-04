package sae.TreeSearchAndGame.ia.algo.jeux;


import sae.TreeSearchAndGame.ia.framework.common.Action;
import sae.TreeSearchAndGame.ia.framework.jeux.Game;
import sae.TreeSearchAndGame.ia.framework.jeux.GameState;
import sae.TreeSearchAndGame.ia.framework.jeux.Player;

/**
 * Définie un joueur Humain
 *
 */

public class HumanPlayer extends Player {

    /**
     * Crée un joueur human
     * @param g l'instance du jeux
     * @param p1 vrai si joueur 1
     */
    public HumanPlayer(Game g, boolean p1){
        super(g, p1);
        name = "Human";
    }
    
    /**
     * {@inheritDoc}
     * <p>Demande un coup au joueur humain</p>
     */
    public Action getMove(GameState state){
        return game.getHumanMove(state);
    }


}
