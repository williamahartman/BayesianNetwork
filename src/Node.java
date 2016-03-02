import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
CS4341 - Artificial Intelligence - WPI - Project 6
Akshay Thejaswi
William Hartman
 */

/**
 * This class represents a node in a bayesian network. It offers methods for creating
 * the node, updating information about the node, and sampling the node.
 */
public class Node {
    public static final int EVIDENCE_FALSE = 0;
    public static final int EVIDENCE_TRUE = 1;
    public static final int QUERY = 2;
    public static final int NEITHER = 3;

    private String name;
    private List<Node> parents = new ArrayList<>();
    private List<Node> children = new ArrayList<>();
    private double[] cpts;
    private int variableInfo;

    private boolean sampledValue = false;

    public Node(String name) {
        this.name = name;
    }

    public boolean getSampledValue(){
        return sampledValue;
    }

    public void addParent(Node parent){
        parents.add(parent);
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public void setTable(double[] cpt){
        cpts = cpt;
    }

    public void setVariableInfo(int variableInfo) {
        this.variableInfo = variableInfo;
    }

    public double getProbabilityGivenParents(List<Node> trueParents) {

        int index = 0;

        for(Node n: trueParents) {
            int parentIndex = parents.indexOf(n);

            index = index ^ (1 << parentIndex);
        }

        return cpts[index];
    }

    public boolean priorSample(){
        if(parents.isEmpty()){
            return sampledValue = Math.random() < cpts[0];
        } else {
            parents.forEach(p -> p.priorSample());
            List<Node> given = parents.stream().filter(p -> p.getSampledValue()).collect(Collectors.toList());
            return sampledValue = Math.random() < getProbabilityGivenParents(given);
        }
    }

    public boolean weightedSample(){
        if(variableInfo == EVIDENCE_TRUE) {
            return sampledValue = true;
        } else if(variableInfo == EVIDENCE_FALSE) {
            return sampledValue = false;
        }

        return priorSample();
    }

    @Override
    public String toString() {
        return name;
    }
}
