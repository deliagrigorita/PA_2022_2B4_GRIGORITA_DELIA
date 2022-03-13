package com.lab_ag_1_homework;

import java.util.Arrays;

public class Main {

    private char[] characters;
    private String[] generatedStrings;
    private int n, p;

    private RandomStringGenerator randomStringGenerator;
    private GraphRepresentation graphRepresentation;

    public Main()
    {
        this.characters = new char[0];
        this.generatedStrings = new String[0];
        this.n = this.p = 0;
        this.randomStringGenerator = new RandomStringGenerator();
        this.graphRepresentation = new GraphRepresentation();
    }

    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();

        Main mainProgram = new Main();
        mainProgram.parseArguments(args);
        mainProgram.generateStrings();

        if (mainProgram.n * mainProgram.p < 1000)
        {
            mainProgram.smallExample();
        }
        else
        {
            mainProgram.largeExample();
        }

        SubsetSolver subsetSolver = new SubsetSolver(mainProgram.graphRepresentation.GetAdjacencyList(),
                mainProgram.generatedStrings);

        subsetSolver.FindSubset();

        long endTime = System.currentTimeMillis();

        System.out.println("Elapsed time ".concat(String.valueOf(endTime - startTime)));
    }

    public void smallExample()
    {
        this.displayRandomStrings();
        this.graphRepresentation.represent(this.generatedStrings);
        this.graphRepresentation.printListDataStructure();
    }

    public void largeExample()
    {
        this.graphRepresentation.represent(this.generatedStrings);
    }

    public void parseArguments(String[] args)
    {
        if (args.length < 4)
        {
            System.out.print("Invalid number of arguments");
            System.exit(-1);
        }

        this.n = Integer.parseInt(args[0]);
        this.p = Integer.parseInt(args[1]);

        for (int it = 2; it < args.length; it++)
        {
            this.characters = Arrays.copyOf(this.characters, this.characters.length + 1);
            this.characters[this.characters.length - 1] = args[it].charAt(0);
        }
    }

    public void generateStrings()
    {
        for (int it = 0; it < this.n; it++)
        {
            this.generatedStrings = Arrays.copyOf(this.generatedStrings, this.generatedStrings.length + 1);
            this.generatedStrings[this.generatedStrings.length - 1] =
                    randomStringGenerator.Generate(this.characters, this.p);
        }
    }

    public void displayRandomStrings()
    {
        System.out.println("<---- Generated Strings ---->");

        for (int it = 0; it < this.generatedStrings.length; it++)
        {
            System.out.println(this.generatedStrings[it]);
        }

        System.out.println("<--- --- --- --- --- --- --->");
    }
}
