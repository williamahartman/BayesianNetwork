import java.util.function.IntPredicate;
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

        System.out.println("Rejection Sampling for " + numSamples + " samples: " + rejectionSample(query, numSamples));
        System.out.println("Likelihood Weighting Sampling for " + numSamples + " samples: " + likelihoodWeightingSampling(query, numSamples));
    }

    public static double rejectionSample(Node query, int numSamples){
        return sample(numSamples, i -> query.priorSample());
    }

    public static double likelihoodWeightingSampling(Node query, int numSamples) {
        return sample(numSamples, i -> query.weightedSample());
    }

    public static double sample(int numSamples, IntPredicate samplingMethod) {
        return (double)IntStream.range(0, numSamples).filter(samplingMethod).count() / (double)numSamples;
    }
}
