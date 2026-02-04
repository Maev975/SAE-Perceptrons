package sae.TreeSearchAndGame.ia.algo.recherche;

import sae.TreeSearchAndGame.ia.framework.common.Action;
import sae.TreeSearchAndGame.ia.framework.common.State;
import sae.TreeSearchAndGame.ia.framework.recherche.SearchNode;
import sae.TreeSearchAndGame.ia.framework.recherche.SearchProblem;
import sae.TreeSearchAndGame.ia.framework.recherche.TreeSearch;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class UCS extends TreeSearch {

    /**
     * Crée un algorithme de recherche
     *
     * @param p Le problème à résoudre
     * @param s L'état initial
     */
    public UCS(SearchProblem p, State s) {
        super(p, s);
    }

    @Override
    public boolean solve() {
        ArrayList<SearchNode> frontier = new ArrayList<>();
        Map<State, Double> meilleurCout = new HashMap<>();
        SearchNode root = SearchNode.makeRootSearchNode(this.initial_state);
        frontier.add(root);
        meilleurCout.put(root.getState(), 0.0);

        while(!frontier.isEmpty()){

            int minIndex = 0;
            double minCost = frontier.get(0).getCost();

            for (int i = 1; i < frontier.size(); i++) {
                double c = frontier.get(i).getCost();
                if (c < minCost) {
                    minCost = c;
                    minIndex = i;
                }
            }

            SearchNode current = frontier.remove(minIndex);
            State currentState = current.getState();
            double currentCost = current.getCost();

            if(problem.isGoalState(currentState)){
                this.end_node = current;
                return true;
            }

            if(currentCost > meilleurCout.get(currentState)){
                continue;
            }

            for (Action action : problem.getActions(currentState)) {

                SearchNode child =
                        SearchNode.makeChildSearchNode(problem, current, action);

                State childState = child.getState();
                double childCost = child.getCost();

                double knownCost =
                        meilleurCout.getOrDefault(childState, Double.MAX_VALUE);

                if (childCost < knownCost) {
                    meilleurCout.put(childState, childCost);
                    frontier.add(child);
                }
            }

        }

        return false;
    }
}
