package sae.TreeSearchAndGame.ia.algo.jeux;

import sae.TreeSearchAndGame.ia.framework.common.Action;
import sae.TreeSearchAndGame.ia.framework.common.ActionValuePair;
import sae.TreeSearchAndGame.ia.framework.common.State;
import sae.TreeSearchAndGame.ia.framework.jeux.Game;
import sae.TreeSearchAndGame.ia.framework.jeux.GameState;
import sae.TreeSearchAndGame.ia.framework.jeux.Player;

public class AlphaBeta extends Player {
    /**
     * Represente un joueur
     *
     * @param g          l'instance du jeux
     * @param player_one si joueur 1
     */
    public AlphaBeta(Game g, boolean player_one) {
        super(g, player_one);
    }

    @Override
    public Action getMove(GameState state) {
        ActionValuePair coup;
        if(this.player == PLAYER1){
            coup = maxValeur(state, Double.MIN_VALUE, Double.MAX_VALUE, 0);
        }else{
            coup = minValeur(state, Double.MIN_VALUE, Double.MAX_VALUE, 0);
        }
        return coup.getAction();
    }

    public ActionValuePair maxValeur(GameState state, double alpha, double beta, int profondeur){
        profondeur++;
        if(game.endOfGame(state)){
            return new ActionValuePair(null, state.getGameValue());
        }
        if(profondeur == 3){
//            return new ActionValuePair()
        }

        double V_MAX = Double.NEGATIVE_INFINITY;
        Action C_MAX = game.getActions(state).getFirst();

        for(Action a : this.game.getActions(state)){
            State nextState = this.game.doAction(state, a);
            ActionValuePair coup = minValeur((GameState) nextState, alpha, beta, profondeur);
            if(coup.getValue() > V_MAX){
                V_MAX = coup.getValue();
                C_MAX = a;

                if(V_MAX > alpha){
                    alpha = V_MAX;
                }
            }else if(V_MAX >= beta){
                return new ActionValuePair(C_MAX, V_MAX);
            }
        }

        return new ActionValuePair(C_MAX, V_MAX);
    }

    public ActionValuePair minValeur(GameState state, double alpha, double beta, int profondeur){
        profondeur++;
        if(game.endOfGame(state)){
            return new ActionValuePair(null, state.getGameValue());
        }

        double V_MIN = Double.MAX_VALUE;
        Action C_MIN = game.getActions(state).getFirst();

        for(Action a : this.game.getActions(state)){
            State nextState = this.game.doAction(state, a);
            ActionValuePair coup = maxValeur((GameState) nextState, alpha, beta, profondeur);
            if(coup.getValue() <= V_MIN){
                V_MIN = coup.getValue();
                C_MIN = a;

                if(V_MIN < beta){
                    beta = V_MIN;
                }
            }else if(V_MIN <= alpha){
                return new ActionValuePair(C_MIN, V_MIN);
            }
        }

        return new ActionValuePair(C_MIN, V_MIN);
    }
}
