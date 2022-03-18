package com.networkroutingproblem;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Node {
    private String name;
    private String MAC;
    private Network network;
    private Map<Integer, Integer> nodeCostMapping;

    public Node(String name, String MAC, Network network) {
        this.name = name;
        this.MAC = MAC;
        this.network = network;
        this.nodeCostMapping = new HashMap<Integer, Integer>();
    }

    public String getName() {
        return name;
    }

    public String getMAC() {
        return MAC;
    }

    public Map<Integer, Integer> getNodeCostMapping() {
        return nodeCostMapping;
    }

    public void addConnection(int neighbourIndex, int connectionCost)
    {
        this.nodeCostMapping.put(neighbourIndex, connectionCost);
    }

    public void printConnections()
    {
        for (var nodeCost : this.nodeCostMapping.entrySet())
        {
            String neighbourName = network.getNode(nodeCost.getKey()).name;

            if (name.compareTo(neighbourName) < 0)
            {
                System.out.println(name + "--" + neighbourName + " || Cost : " + nodeCost.getValue());
            }
        }
    }

    public abstract void printNode();

    protected void printBasicData()
    {
        System.out.println("Name : " + this.getName());
        System.out.println("MAC : " + this.getMAC());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return name.equals(node.name) && MAC.equals(node.MAC);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, MAC);
    }
}
