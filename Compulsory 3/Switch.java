package com.networkroutingproblem;

public class Switch extends Node {
    public Switch(String IP, String name, String MAC) {
        super(IP, name, MAC);
    }

    @Override
    public void printNode() {
        System.out.println("<-- Switch -->");
        this.printBasicData();
    }
}
