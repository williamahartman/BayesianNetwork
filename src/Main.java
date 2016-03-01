import java.util.Map;

public class Main {

    public static double rejectionSampling(Map<String, Node> network, int numSamples) {
        return 0;
    }

    public static double likelihoodWeightingSampling(Map<String, Node> network, int numSamples) {
        return 0;
    }

    public static void main(String[] args) {
        int numSamples = -1;
        if(args.length == 3) {
            try {
                numSamples = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.err.println("The input was not an integer");
                System.exit(-1);
            }
        } else {
            System.err.println("This program must be run with the number of samples as an argument");
            System.exit(-1);
        }

        Map<String, Node> net = FileReader.readNetwork(args[0]);
        FileReader.readVariables(args[1], net);

        System.out.println("Rejection Sampling for " + numSamples + " samples: " + rejectionSampling(net, numSamples));
        System.out.println("Likelihood Weighting Sampling for " + numSamples + " samples: " + likelihoodWeightingSampling(net, numSamples));
    }
}
