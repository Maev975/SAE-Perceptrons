package sae.TreeSearchAndGame.ia.algo.recherche;

import sae.TreeSearchAndGame.ia.framework.common.Action;
import sae.TreeSearchAndGame.ia.framework.common.State;
import sae.TreeSearchAndGame.ia.framework.recherche.SearchNode;
import sae.TreeSearchAndGame.ia.framework.recherche.SearchProblem;
import sae.TreeSearchAndGame.ia.framework.recherche.TreeSearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class DFS extends TreeSearch {

    ArrayList<SearchNode> noeudExistant;
    ArrayList<SearchNode> noeudVisite;

    /**
     * Crée un algorithme de recherche
     *
     * @param p Le problème à résoudre
     * @param s L'état initial
     */
    public DFS(SearchProblem p, State s) {
        super(p, s);
        this.frontier = new LinkedList<>();
    }


    @Override
    public boolean solve() {

        LinkedList<SearchNode> stack = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        SearchNode root = SearchNode.makeRootSearchNode(initial_state);
        stack.addLast(root);

        while (!stack.isEmpty()) {

            SearchNode current = stack.removeLast();
            State currentState = current.getState();

            if (problem.isGoalState(currentState)) {
                this.end_node = current;
                return true;
            }

            if (visited.contains(currentState)) {
                continue;
            }

            visited.add(currentState);

            for (Action a : problem.getActions(currentState)) {
                SearchNode child =
                        SearchNode.makeChildSearchNode(problem, current, a);

                if (!visited.contains(child.getState())) {
                    stack.addLast(child);
                }
            }
        }

        return false;
    }
}
