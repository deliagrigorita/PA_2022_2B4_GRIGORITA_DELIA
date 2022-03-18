package com.networkroutingproblem;

public abstract class IdentifiableNode extends Node implements Identifiable {
    private String IP;

    public IdentifiableNode(String name, String MAC, Network network, String IP) {
        super(name, MAC, network);
        this.IP = IP;
    }

    @Override
    public String getIP() {
        return this.IP;
    }

    public abstract void printNode();

    protected void printBasicData()
    {
        System.out.println("IP : " + this.getIP());
        super.printBasicData();
    }
}
