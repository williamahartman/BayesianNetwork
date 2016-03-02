CS4341 - Artificial Intelligence - WPI - Project 6

Akshay Thejaswi
William Hartman

General Information:
- The "Option B" file format was used for the input network.
- The project was written in Java. Java 8 features were used, so a Java 8 JVM and JDK are needed.
- No external libraries were used. All data structures were implemented in the provided code.
    - This decision was made since there were few special requirements for the network structure.
    The network could be implemented simply as a class that kept references of its parents and children.
    - The network is encapsulated with a Map data structure that allows us to retrieve nodes without
    traversing the tree
    - A simple tree traversal was implemented for sampling nodes with dependencies.

Compiling and running:
­ Run the project with "java ­jar BayesianNetwork.jar arg1 arg2 arg3”
    ­ A Java 8 JVM is required to run the project.
    - Three arguments are needed for the program:
        1) The file describing the bayesian net
        2) The file describing the query on the bayesian net
        3) The number of samples to use when evaluating the query
    - Ex.
        java ­jar BayesianNetwork.jar network_option_b.txt query1.txt 100

­ Compile the project as a standard java project in any ide or on the command line with
the Java 8 JDK. No libraries or unusual configurations were used for this project.
