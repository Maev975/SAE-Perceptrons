package sae.TreeSearchAndGame.ia.algo.recherche;

import sae.TreeSearchAndGame.ia.framework.common.Action;
import sae.TreeSearchAndGame.ia.framework.common.State;
import sae.TreeSearchAndGame.ia.framework.recherche.SearchNode;
import sae.TreeSearchAndGame.ia.framework.recherche.SearchProblem;
import sae.TreeSearchAndGame.ia.framework.recherche.TreeSearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GFS extends TreeSearch {

    public GFS(SearchProblem p, State s) {
        super(p, s);
    }

    @Override
    public boolean solve() {

        ArrayList<SearchNode> frontier = new ArrayList<>();
        Set<State> visited = new HashSet<>();

        SearchNode root = SearchNode.makeRootSearchNode(initial_state);
        frontier.add(root);

        while (!frontier.isEmpty()) {

            int minIndex = 0;
            double minH = frontier.get(0).getHeuristic();

            for (int i = 1; i < frontier.size(); i++) {
                double h = frontier.get(i).getHeuristic();
                if (h < minH) {
                    minH = h;
                    minIndex = i;
                }
            }

            SearchNode current = frontier.remove(minIndex);
            State currentState = current.getState();

            if (problem.isGoalState(currentState)) {
                this.end_node = current;
                return true;
            }

            if (visited.contains(currentState)) {
                continue;
            }

            visited.add(currentState);

            for (Action action : problem.getActions(currentState)) {
                SearchNode child =
                        SearchNode.makeChildSearchNode(problem, current, action);

                if (!visited.contains(child.getState())) {
                    frontier.add(child);
                }
            }
        }

        return false;
    }
}
