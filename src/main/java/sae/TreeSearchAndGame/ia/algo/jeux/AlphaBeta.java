package sae.TreeSearchAndGame.ia.algo.jeux;

import sae.TreeSearchAndGame.ia.framework.jeux.Game;
import sae.TreeSearchAndGame.ia.framework.jeux.GameState;
import sae.TreeSearchAndGame.ia.framework.jeux.Player;
import sae.TreeSearchAndGame.ia.framework.common.Action;
import sae.TreeSearchAndGame.ia.framework.common.ActionValuePair;
import java.util.ArrayList;

public class AlphaBeta extends Player {

    private int d;

    public AlphaBeta(Game g, boolean player_one, int depth) {
        super(g, player_one);
        this.d = depth;
    }

    @Override
    public Action getMove(GameState state) {
        ActionValuePair coup;
        if (this.player == PLAYER1) {
            coup = maxValeur(state, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
        } else {
            coup = minValeur(state, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
        }

        if (coup.getAction() == null) {
            ArrayList<Action> actions = this.game.getActions(state);
            return actions.get(0);
        }

        return coup.getAction();
    }

    private ActionValuePair maxValeur(GameState state, double alpha, double beta, int profondeur) {
        this.incStateCounter();

        if (state.isFinalState() || profondeur >= d) {
            return new ActionValuePair(null, state.getGameValue());
        }

        double vMax = Double.NEGATIVE_INFINITY;
        ArrayList<Action> actions = this.game.getActions(state);
        Action cMax = actions.get(0);

        for (Action a : actions) {
            GameState nextState = (GameState) this.game.doAction(state, a);
            ActionValuePair coup = minValeur(nextState, alpha, beta, profondeur + 1);

            if (coup.getValue() > vMax) {
                vMax = coup.getValue();
                cMax = a;
            }

            if (vMax >= beta) {
                return new ActionValuePair(cMax, vMax);
            }
            alpha = Math.max(alpha, vMax);
        }
        return new ActionValuePair(cMax, vMax);
    }

    private ActionValuePair minValeur(GameState state, double alpha, double beta, int profondeur) {
        this.incStateCounter();

        if (state.isFinalState() || profondeur >= d) {
            return new ActionValuePair(null, state.getGameValue());
        }

        double vMin = Double.POSITIVE_INFINITY;
        ArrayList<Action> actions = this.game.getActions(state);
        Action cMin = actions.get(0);

        for (Action a : actions) {
            GameState nextState = (GameState) this.game.doAction(state, a);
            ActionValuePair coup = maxValeur(nextState, alpha, beta, profondeur + 1);

            if (coup.getValue() < vMin) {
                vMin = coup.getValue();
                cMin = a;
            }

            if (vMin <= alpha) {
                return new ActionValuePair(cMin, vMin);
            }
            beta = Math.min(beta, vMin);
        }
        return new ActionValuePair(cMin, vMin);
    }
}