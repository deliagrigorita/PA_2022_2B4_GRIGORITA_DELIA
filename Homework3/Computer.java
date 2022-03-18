package com.networkroutingproblem;

public class Computer extends IdentifiableNode implements Storage{
    private int storageSize;

    public Computer(String name, String MAC, Network network, String IP, int storageSize) {
        super(name, MAC, network, IP);
        this.storageSize = storageSize;
    }

    @Override
    public int getStorageSize() {
        return storageSize;
    }

    @Override
    public void printNode() {
        System.out.println("<-- Computer -->");
        this.printBasicData();
    }
}
