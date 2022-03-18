package com.networkroutingproblem;

public class Switch extends Node {
    public Switch(String name, String MAC, Network network) {
        super(name, MAC, network);
    }

    @Override
    public void printNode() {
        System.out.println("<-- Switch -->");
        this.printBasicData();
    }
}
