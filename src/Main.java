import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/*
CS4341 - Artificial Intelligence - WPI - Project 6
Akshay Thejaswi
William Hartman
 */

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
            System.err.println("This program must be run with 3 arguments:");
            System.err.println("    1) The file describing the bayesian net");
            System.err.println("    2) The file describing the query on the bayesian net");
            System.err.println("    3) The number of samples to use when evaluating the query");
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
        return (double) IntStream.range(0, numSamples).filter(samplingMethod).count() / (double) numSamples;
    }

//    /*
//    Main for generating data used in the writeup.
//     */
//    public static void main(String[] args){
//        final int numSamples = Integer.parseInt(args[2]);
//        BayesianNet net = FileReader.readNetwork(args[0]);
//        Node query = FileReader.readVariables(args[1], net);
//
//        for(int i = 0; i < 10; i++){
//            System.out.print(rejectionSample(query, 50)+" ");
//            for(int n = 200; n <= 1000; n+= 200){
//                System.out.print(rejectionSample(query, n) + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println();
//
//        for(int i = 0; i < 10; i++){
//
//            System.out.print(likelihoodWeightingSampling(query, 50)+ " ");
//            for(int n = 200; n <= 1000; n+= 200){
//                System.out.print(likelihoodWeightingSampling(query, n) + " ");
//            }
//            System.out.println();
//        }
//    }
}
