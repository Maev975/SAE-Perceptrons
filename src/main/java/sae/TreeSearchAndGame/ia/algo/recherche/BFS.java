package sae.TreeSearchAndGame.ia.algo.recherche;

import sae.TreeSearchAndGame.ia.framework.common.Action;
import sae.TreeSearchAndGame.ia.framework.common.State;
import sae.TreeSearchAndGame.ia.framework.recherche.SearchNode;
import sae.TreeSearchAndGame.ia.framework.recherche.SearchProblem;
import sae.TreeSearchAndGame.ia.framework.recherche.TreeSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

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
        SearchNode searchNode = SearchNode.makeRootSearchNode(this.initial_state);
        this.noeudVisite = new ArrayList<>();
        this.noeudExistant = new ArrayList<>();
        this.noeudVisite.add(searchNode);
        this.noeudExistant.add(searchNode);
        SearchNode current = null;

        while(noeudExistant != null && !noeudExistant.isEmpty()){
            current = noeudExistant.getFirst();
            noeudExistant.remove(current);
            if(problem.isGoalState(current.getState())){
                this.end_node = current;
                return true;
            }else{
                this.noeudVisite.add(current);
                for(Action a : this.problem.getActions(current.getState())){
                    State enfant = this.problem.doAction(current.getState(), a);
                    if(problem.isGoalState(enfant)){
                        this.end_node = SearchNode.makeChildSearchNode(this.problem, current, a);
                        return true;
                    }else if(!noeudExistant.contains(enfant) && !this.noeudVisite.contains(enfant)){
                        noeudExistant.add(SearchNode.makeChildSearchNode(this.problem, current, a));
                    }
                }
            }
        }

        return false;
    }
}
