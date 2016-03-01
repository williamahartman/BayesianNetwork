import java.util.Map;
import java.util.stream.IntStream;

public class Main {

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

        BayesianNet net = FileReader.readNetwork(args[0]);
        Node query = FileReader.readVariables(args[1], net);

        System.out.println("Rejection Sampling for " + numSamples + " samples: " + rejectionSample(net, query, numSamples));
        System.out.println("Likelihood Weighting Sampling for " + numSamples + " samples: " + likelihoodWeightingSampling(net, numSamples));
    }

    public static double rejectionSample(BayesianNet net, Node query,int samples){
        return (double)IntStream.range(0, samples).filter(i -> query.priorSample()).count() / (double)samples;
    }

    public static double likelihoodWeightingSampling(BayesianNet network, int numSamples) {
        return 0;
    }
}
