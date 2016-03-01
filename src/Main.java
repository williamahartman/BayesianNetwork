import java.util.Arrays;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<String, Node> net = FileReader.readNetwork("network_option_b.txt");
        FileReader.readVariables("query1.txt", net);

        System.out.println(net.get("node1").getProbabilityGivenParents(Arrays.asList(net.get("node4"))));
        System.out.println(net.get("node6").getVariableInfo());
    }
}
