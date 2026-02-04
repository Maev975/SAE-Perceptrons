package sae.TreeSearchAndGame.ia.algo.recherche;

import sae.TreeSearchAndGame.ia.framework.common.Action;
import sae.TreeSearchAndGame.ia.framework.common.State;
import sae.TreeSearchAndGame.ia.framework.recherche.SearchNode;
import sae.TreeSearchAndGame.ia.framework.recherche.SearchProblem;
import sae.TreeSearchAndGame.ia.framework.recherche.TreeSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

public class BFS extends TreeSearch {

    ArrayList<SearchNode> noeudExistant;
    ArrayList<SearchNode> noeudVisite;

    /**
     * Crée un algorithme de recherche
     *
     * @param p Le problème à résoudre
     * @param s L'état initial
     */
    public BFS(SearchProblem p, State s) {
        super(p, s);
        this.frontier = new LinkedList<>();
    }

    @Override
    public boolean solve() {

        LinkedList<SearchNode> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        SearchNode root = SearchNode.makeRootSearchNode(initial_state);
        queue.addLast(root);
        visited.add(root.getState());

        while (!queue.isEmpty()) {

            SearchNode current = queue.removeFirst();
            State currentState = current.getState();

            if (problem.isGoalState(currentState)) {
                this.end_node = current;
                return true;
            }

            for (Action a : problem.getActions(currentState)) {
                SearchNode child =
                        SearchNode.makeChildSearchNode(problem, current, a);

                State childState = child.getState();

                if (!visited.contains(childState)) {
                    visited.add(childState);
                    queue.addLast(child);
                }
            }
        }

        return false;
    }
}
