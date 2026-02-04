package sae.TreeSearchAndGame.ia.algo.jeux;

import sae.TreeSearchAndGame.ia.framework.common.Action;
import sae.TreeSearchAndGame.ia.framework.common.ActionValuePair;
import sae.TreeSearchAndGame.ia.framework.jeux.Game;
import sae.TreeSearchAndGame.ia.framework.jeux.GameState;
import sae.TreeSearchAndGame.ia.framework.jeux.Player;

import java.util.ArrayList;

public class MinMaxPlayer extends Player {

    private int d;

    public MinMaxPlayer(Game g, boolean player_one, int depth) {
        super(g, player_one);
        this.d = depth;
    }

    @Override
    public Action getMove(GameState state) {
        ActionValuePair coup;
        if (this.player == PLAYER1) {
            coup = maxValeur(state, 0);
        } else {
            coup = minValeur(state, 0);
        }
        return coup.getAction();
    }

    public ActionValuePair maxValeur(GameState state, int profondeur) {
        this.incStateCounter();
        if (state.isFinalState() || profondeur >= d) {
            return new ActionValuePair(null, state.getGameValue());
        }
        double vMax = Double.NEGATIVE_INFINITY;
        ArrayList<Action> actions = this.game.getActions(state);
        Action cMax = actions.get(0);
        for (Action a : actions) {
            GameState nextState = (GameState) this.game.doAction(state, a);
            ActionValuePair coup = minValeur(nextState, profondeur + 1);
            if (coup.getValue() > vMax) {
                vMax = coup.getValue();
                cMax = a;
            }
        }
        return new ActionValuePair(cMax, vMax);
    }

    public ActionValuePair minValeur(GameState state, int profondeur) {
        this.incStateCounter();
        if (state.isFinalState() || profondeur >= d) {
            return new ActionValuePair(null, state.getGameValue());
        }
        double vMin = Double.POSITIVE_INFINITY;
        ArrayList<Action> actions = this.game.getActions(state);
        Action cMin = actions.get(0);
        for (Action a : actions) {
            GameState nextState = (GameState) this.game.doAction(state, a);
            ActionValuePair coup = maxValeur(nextState, profondeur + 1);
            if (coup.getValue() < vMin) {
                vMin = coup.getValue();
                cMin = a;
            }
        }
        return new ActionValuePair(cMin, vMin);
    }
}