package com.lab_ag_1_homework;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class SubsetSolver
{
    private List<List<Integer>> adjacencyList;
    private String[] words;
    private boolean[] visited;
    private int maxSolutionLen;
    private int[] bestSolution;
    private Stack<Integer> dfsStack;

    public SubsetSolver(List<List<Integer>> adjacencyList, String[] words)
    {
        this.adjacencyList = adjacencyList;
        this.words = words;
        this.maxSolutionLen = 0;
        this.bestSolution = new int[0];
        this.dfsStack = new Stack<>();

        this.visited = new boolean[adjacencyList.size()];
        Arrays.fill(this.visited, false);
    }

    public void FindSubset()
    {
        for (int it = 0; it < this.adjacencyList.size(); it++)
        {
            if (this.visited[it] == false)
            {
                this.visited[it] = true;
                this.DFS(it);

                if (this.bestSolution.length > 3)
                {
                    break;
                }
            }
        }

        this.printBestSolution();
    }

    public void printBestSolution()
    {
        if (this.bestSolution.length == 0)
        {
            System.out.println("Did not find solution");
            return;
        }

        System.out.println("<--- ---".concat(String.valueOf(this.maxSolutionLen)).
                concat(" Words subset that fit propriety --- --->"));

        for (int it = 0; it < this.bestSolution.length; it++)
        {
            System.out.print(this.words[this.bestSolution[it]].concat(" -> "));
        }
        System.out.print("\n");
    }

    public void DFS(int wordIndex)
    {
        this.visited[wordIndex] = true;
        this.dfsStack.push(wordIndex);

        List<Integer> adjacentNeighbors = this.adjacencyList.get(wordIndex);

        for (int it = 0; it < adjacentNeighbors.size(); it++)
        {
            if (this.visited[adjacentNeighbors.get(it)] == false)
            {
                this.DFS(adjacentNeighbors.get(it));
            }
            else if (this.dfsStack.contains(adjacentNeighbors.get(it)))
            {
                this.saveSolution(adjacentNeighbors.get(it));
            }
        }

        this.dfsStack.pop();
    }

    public void saveSolution(int wordIndex)
    {
        int[] currentSolution = new int[1];
        currentSolution[0] = wordIndex;

        int it = this.dfsStack.size() - 1;

        while (this.dfsStack.get(it) != wordIndex)
        {
            currentSolution = Arrays.copyOf(currentSolution, currentSolution.length + 1);
            currentSolution[currentSolution.length - 1] = this.dfsStack.get(it);

            it--;
        }

        currentSolution = Arrays.copyOf(currentSolution, currentSolution.length + 1);
        currentSolution[currentSolution.length - 1] = wordIndex;

        if (currentSolution.length > this.maxSolutionLen)
        {
            this.maxSolutionLen = currentSolution.length;
            this.bestSolution = currentSolution;
        }
    }
}
