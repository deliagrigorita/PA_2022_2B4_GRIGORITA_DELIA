package com.networkroutingproblem;

public class Computer extends Node implements Storage{
    public Computer(String IP, String name, String MAC) {
        super(IP, name, MAC);
    }

    @Override
    public int GetStorageSize() {
        return 0;
    }

    @Override
    public void printNode() {
        System.out.println("<-- Computer -->");
        this.printBasicData();
    }
}
