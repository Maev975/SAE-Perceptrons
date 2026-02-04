package sae.TreeSearchAndGame.ia.algo.recherche;

import sae.TreeSearchAndGame.ia.framework.common.Action;
import sae.TreeSearchAndGame.ia.framework.common.State;
import sae.TreeSearchAndGame.ia.framework.recherche.SearchNode;
import sae.TreeSearchAndGame.ia.framework.recherche.SearchProblem;
import sae.TreeSearchAndGame.ia.framework.recherche.TreeSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AStar extends TreeSearch {

    public AStar(SearchProblem p, State s) {
        super(p, s);
    }

    @Override
    public boolean solve() {

        ArrayList<SearchNode> frontier = new ArrayList<>();
        Map<State, Double> bestG = new HashMap<>();

        SearchNode root = SearchNode.makeRootSearchNode(this.initial_state);
        frontier.add(root);
        bestG.put(root.getState(), root.getCost());

        while (!frontier.isEmpty()) {

            int minIndex = 0;
            double minF = frontier.get(0).getCost() + frontier.get(0).getHeuristic();

            for (int i = 1; i < frontier.size(); i++) {
                double f = frontier.get(i).getCost() + frontier.get(i).getHeuristic();
                if (f < minF) {
                    minF = f;
                    minIndex = i;
                }
            }

            SearchNode current = frontier.remove(minIndex);
            State currentState = current.getState();
            double currentG = current.getCost();

            if (problem.isGoalState(currentState)) {
                this.end_node = current;
                return true;
            }

            if (currentG <= bestG.getOrDefault(currentState, Double.MAX_VALUE)) {

                bestG.put(currentState, currentG);

                for (Action action : problem.getActions(currentState)) {

                    SearchNode child =
                            SearchNode.makeChildSearchNode(problem, current, action);

                    State childState = child.getState();
                    double childG = child.getCost();

                    double knownG =
                            bestG.getOrDefault(childState, Double.MAX_VALUE);

                    if (childG < knownG) {
                        frontier.add(child);
                    }
                }
            }
        }

        return false;
    }
}
