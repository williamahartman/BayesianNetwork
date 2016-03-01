import java.util.List;

/**
 * Created by will on 2/29/16.
 */
public class Node {
    private List<Node> parents;
    private List<Node> nextNodes;
    private double[] cpts;
    private boolean isQueryVariable;
    private boolean isEvidenceVariable;

    public Node(List<Node> parents, List<Node> nextNodes, double[] cpts) {
        this.parents = parents;
        this.nextNodes = nextNodes;
        this.cpts = cpts;
    }

    public void addNextNode(Node n) {
        nextNodes.add(n);
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
}
