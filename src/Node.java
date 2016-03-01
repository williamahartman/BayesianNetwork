import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by will on 2/29/16.
 */
public class Node {
    public static final int EVIDENCE_FALSE = 0;
    public static final int EVIDENCE_TRUE = 1;
    public static final int QUERY = 2;
    public static final int NIETHER = 3;


    private String name;
    private List<Node> parents = new ArrayList<>();
    private List<Node> children = new ArrayList<>();
    private double[] cpts;
    private int variableInfo;

    public Node(String name) {
        this.name = name;
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

    public void addNextNode(Node n) {
        children.add(n);
    }

    public int getVariableInfo() {
        return variableInfo;
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

    @Override
    public String toString() {
        return name;
    }

    public String getString(){
        return String.format("%s: %s %s", name, parents.toString(), Arrays.toString(cpts));
    }
}
