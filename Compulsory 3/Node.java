package com.networkroutingproblem;

public abstract class Node implements Identifiable {
    private String IP;
    private String name;
    private String MAC;

    public Node(String IP, String name, String MAC) {
        this.IP = IP;
        this.name = name;
        this.MAC = MAC;
    }

    @Override
    public String getIP() {
        return this.IP;
    }

    public String getName() {
        return name;
    }

    public String getMAC() {
        return MAC;
    }

    public abstract void printNode();

    protected void printBasicData()
    {
        System.out.println("IP : " + this.getIP());
        System.out.println("Name : " + this.getName());
        System.out.println("MAC : " + this.getMAC());
    }
}
