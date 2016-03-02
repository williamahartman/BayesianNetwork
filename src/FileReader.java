import java.io.FileInputStream;
import java.util.*;
import java.util.stream.IntStream;

/*
CS4341 - Artificial Intelligence - WPI - Project 6
Akshay Thejaswi
William Hartman
 */

/**
 * This class build and populates a bayesian network based on files conforming to the
 * given spec.
 */
public class FileReader {

    public static BayesianNet readNetwork(String fn){
        BayesianNet net = new BayesianNet();
        Scanner sc = null;
        try {
            sc = new Scanner(new FileInputStream(fn));
        } catch (Exception e){
            e.printStackTrace();
        }

        if(sc == null) return net;

        List<String[]> lines = new ArrayList<>();
        while(sc.hasNextLine()){
            String[] line = sc.nextLine().split(":");
            lines.add(line);
            net.put(line[0], new Node(line[0]));
        }

        for(String[] line:lines){
            String name = line[0];
            int firstOpenBracket = line[1].indexOf('[');
            int firstCloseBracket = line[1].indexOf(']');

            String[] parents = line[1].substring(firstOpenBracket+1, firstCloseBracket).split(" ");
            for(String parent:parents){
                if(net.containsKey(parent)) {
                    net.get(name).addParent(net.get(parent));
                    net.get(parent).addChild(net.get(name));
                }
            }

            String cptArrayStr = line[1].substring(firstCloseBracket + 1);
            String[] cptStr = cptArrayStr.substring(cptArrayStr.indexOf('[') + 1, cptArrayStr.indexOf(']'))
                    .split(" ");
            double[] cpt = IntStream.range(0, cptStr.length).filter(i -> !cptStr[i].isEmpty()).mapToDouble(i -> Double.parseDouble(cptStr[i])).toArray();
            net.get(name).setTable(cpt);


        }

        return net;
    }

    public static Node readVariables(String filename, BayesianNet network) {
        Node query = null;
        Scanner sc = null;
        try {
            sc = new Scanner(new FileInputStream(filename));
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

        if(sc.hasNextLine()) {
            String[] variableTypes = sc.nextLine().split(",");
            List<Node> nodeList = new ArrayList<>(network.values());

            if(variableTypes.length != nodeList.size()) {
                throw new RuntimeException("The given list cannot apply to the network");
            }

            for(int i = 0; i < variableTypes.length; i++) {
                switch (variableTypes[i]) {
                    case "t":
                        nodeList.get(i).setVariableInfo(Node.EVIDENCE_TRUE);
                        break;

                    case "f":
                        nodeList.get(i).setVariableInfo(Node.EVIDENCE_FALSE);
                        break;

                    case "-":
                        nodeList.get(i).setVariableInfo(Node.QUERY);
                        query = nodeList.get(i);
                        break;

                    case "?":
                        nodeList.get(i).setVariableInfo(Node.NEITHER);
                        break;
                }
            }
        }
        return query;
    }
}
