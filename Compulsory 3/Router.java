package com.networkroutingproblem;

public class Router extends Node {

    public Router(String IP, String name, String MAC) {
        super(IP, name, MAC);
    }

    @Override
    public void printNode() {
        System.out.println("<-- Router -->");
        this.printBasicData();
    }
}
