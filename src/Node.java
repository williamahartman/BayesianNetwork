import java.util.ArrayList;
import java.util.List;

/**
 * Created by will on 2/29/16.
 */
public class Node {

    private String name;
    private List<Node> parents = new ArrayList<>();
    private List<Node> children = new ArrayList<>();
    private double[] cpts;
    private boolean isQueryVariable;
    private boolean isEvidenceVariable;

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

    public boolean isQueryVariable() {
        return isQueryVariable;
    }

    public boolean isEvidenceVariable() {
        return isEvidenceVariable;
    }

    public void setQueryVariable(boolean queryVariable) {
        isQueryVariable = queryVariable;
    }

    public void setEvidenceVariable(boolean evidenceVariable) {
        isEvidenceVariable = evidenceVariable;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getString(){
        return String.format("%s: %s %s", name, parents.toString(), children.toString());
    }
}
