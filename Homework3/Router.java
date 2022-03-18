package com.networkroutingproblem;

public class Router extends IdentifiableNode {

    public Router(String name, String MAC, Network network, String IP) {
        super(name, MAC, network, IP);
    }

    @Override
    public void printNode() {
        System.out.println("<-- Router -->");
        this.printBasicData();
    }
}
