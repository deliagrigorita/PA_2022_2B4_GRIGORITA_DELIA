package com.networkroutingproblem;

import java.util.ArrayList;
import java.util.List;

public class Network implements Identifiable {
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
        if  (networkNodes.contains(node) == false)
        {
            networkNodes.add(node);
        }
    }

    public void addConnection(Node leftNode, Node rightNode, int cost)
    {
        int leftNodeIndex = networkNodes.indexOf(leftNode);
        int rightNodeIndex = networkNodes.indexOf(rightNode);

        networkNodes.get(leftNodeIndex).addConnection(rightNodeIndex, cost);
        networkNodes.get(rightNodeIndex).addConnection(leftNodeIndex, cost);
    }

    public void printNodes()
    {
        for (var node : this.networkNodes)
        {
            node.printNode();
        }
    }

    public void printConnections()
    {
        for (var node : this.networkNodes)
        {
            node.printConnections();
        }
    }

    public Node getNode(int index)
    {
        return this.networkNodes.get(index);
    }

    public void printIdentifiableNodes()
    {
        List<IdentifiableNode> identifiableNodes = new ArrayList<>();

        for (var node : networkNodes)
        {
            if (node instanceof IdentifiableNode)
            {
                identifiableNodes.add((IdentifiableNode)node);
            }
        }

        identifiableNodes.sort((leftNode, rightNode) -> (int)( leftNode.getIP().compareTo(rightNode.getIP()) ));

        for (var identifiableNode : identifiableNodes)
        {
            identifiableNode.printNode();
        }
    }

}
