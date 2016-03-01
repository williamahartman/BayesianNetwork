import java.io.FileInputStream;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by Akshay on 2/29/2016.
 */
public class FileReader {

    public static Map<String, Node> read(String fn){
        Map<String, Node> net = new HashMap<>();
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

        for(Node node:net.values()){
            System.out.println(node.getString());
        }

        return net;
    }

}
