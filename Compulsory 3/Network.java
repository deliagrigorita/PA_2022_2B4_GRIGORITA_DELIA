package com.networkroutingproblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Network implements Identifiable{
    private String IP;
    private List<Node> networkNodes;

    public Network(String IP) {
        this.IP = IP;
        networkNodes = new ArrayList<>();
    }

    @Override
    public String getIP() {
        return this.IP;
    }

    public void addNetworkNode(Node node)
    {
        networkNodes.add(node);
    }

    public void printNodes()
    {
        for (var node : this.networkNodes)
        {
            node.printNode();
        }
    }
}
