package sae.TreeSearchAndGame.ia.algo.jeux;

import sae.TreeSearchAndGame.ia.framework.common.Action;
import sae.TreeSearchAndGame.ia.framework.common.ActionValuePair;
import sae.TreeSearchAndGame.ia.framework.common.State;
import sae.TreeSearchAndGame.ia.framework.jeux.Game;
import sae.TreeSearchAndGame.ia.framework.jeux.GameState;
import sae.TreeSearchAndGame.ia.framework.jeux.Player;

import java.util.ArrayList;

public class MinMaxPlayer extends Player {
    /**
     * Represente un joueur
     *
     * @param g          l'instance du jeux
     * @param player_one si joueur 1
     */
    public MinMaxPlayer(Game g, boolean player_one) {
        super(g, player_one);
    }

    @Override
    public Action getMove(GameState state) {
        ActionValuePair coup;
        if(this.player == PLAYER1){
            coup = maxValeur(state);
        }else{
            coup = minValeur(state);
        }
        return coup.getAction();
    }

    public ActionValuePair maxValeur(GameState state){
        if(game.endOfGame(state)){
            return new ActionValuePair(null, state.getGameValue());
        }

        double V_MAX = Double.NEGATIVE_INFINITY;
        Action C_MAX = null;

        for(Action a : this.game.getActions(state)){
            State nextState = this.game.doAction(state, a);
            ActionValuePair coup = minValeur((GameState) nextState);
            if(coup.getValue() > V_MAX){
                V_MAX = coup.getValue();
                C_MAX = a;
            }
        }

        return new ActionValuePair(C_MAX, V_MAX);
    }

    public ActionValuePair minValeur(GameState state){
        if(game.endOfGame(state)){
            return new ActionValuePair(null, state.getGameValue());
        }

        double V_MIN = Double.MAX_VALUE;
        Action C_MAX = null;

        for(Action a : this.game.getActions(state)){
            State nextState = this.game.doAction(state, a);
            ActionValuePair coup = maxValeur((GameState) nextState);
            if(coup.getValue() < V_MIN){
                V_MIN = coup.getValue();
                C_MAX = a;
            }
        }

        return new ActionValuePair(C_MAX, V_MIN);
    }
}

