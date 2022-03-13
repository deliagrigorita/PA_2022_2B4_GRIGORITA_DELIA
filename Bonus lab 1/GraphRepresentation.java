package com.lab_ag_1_homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphRepresentation {

    private boolean[][] adjacencyMatrix;
    private List<List<Integer>> adjacencyList;
    private String[] words;
    private int len;

    public GraphRepresentation() { len = 0; }

    public void represent(String[] inputStrings)
    {
        this.words = inputStrings;
        this.len = inputStrings.length;
        this.adjacencyMatrix = new boolean[len][len];
        this.adjacencyList = new ArrayList<>();

        //Initialising Adjacency Data Structures

        for (int it = 0; it < this.len; it++)
        {
            Arrays.fill(this.adjacencyMatrix[it], false);
            this.adjacencyList.add(new ArrayList<>());
        }

        //Creating Adjacency Matrix and Adjacency List

        for (int it = 0; it < this.len; it++)
        {
            List<Integer> adjacentNeighbours = this.adjacencyList.get(it);

            for (int jt = 0; jt < this.len; jt++)
            {
                if (it != jt && areNeighbours(this.words[it], this.words[jt]))
                {
                    this.adjacencyMatrix[it][jt] = true;
                    this.adjacencyMatrix[jt][it] = true;
                    adjacentNeighbours.add(jt);
                }
            }
        }
    }

    public void printListDataStructure()
    {
        System.out.println("<----- Generated Neighbouring Strings List ----->");

        for (int it = 0; it < this.len; it++)
        {
            System.out.println("*** *** *** *** *** *** *** *** *** *** *** ***");
            System.out.println("String : ".concat(this.words[it]));
            System.out.println("### ### ### ### ### ### ### ### ### ### ### ###");

            List<Integer> adjacentNeighbours = this.adjacencyList.get(it);

            for (int jt = 0; jt < adjacentNeighbours.size(); jt++)
            {
                System.out.println("Neighbour : ".concat(this.words[adjacentNeighbours.get(jt)]));
            }
        }
        System.out.println("<--- --- --- --- --- --- --- --- --- --- --- --->");
    }

    public static boolean areNeighbours(String lh, String rh)
    {
        for (int it = 0; it < lh.length(); it++)
        {
            for (int jt = 0; jt < rh.length(); jt++)
            {
                if (lh.charAt(it) == rh.charAt(jt))
                {
                    return true;
                }
            }
        }

        return false;
    }

    public List<List<Integer>> GetAdjacencyList()
    {
        return this.adjacencyList;
    }
}
