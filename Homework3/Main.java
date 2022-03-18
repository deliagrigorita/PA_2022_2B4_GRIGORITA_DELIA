package com.networkroutingproblem;

public class Main {

    public static void main(String[] args) {
	    Main program = new Main();
        //program.printConnections();
        //program.storageUnitsConversion();
        program.printIdentifiableNodes();
    }

    public void printConnections()
    {
        Network network = new Network("192.168.100.0");

        Node v1 = new Computer("v1", "A", network, "192.168.100.1", 100);
        Node v2 = new Router("v2", "A", network, "192.168.100.2");
        Node v3 = new Switch("v3", "A", network);
        Node v4 = new Switch("v4", "B", network);
        Node v5 = new Router("v5", "B", network, "192.168.100.3");
        Node v6 = new Computer("v6", "B", network, "192.168.100.4", 100);

        network.addNetworkNode(v1);
        network.addNetworkNode(v2);
        network.addNetworkNode(v3);
        network.addNetworkNode(v4);
        network.addNetworkNode(v5);
        network.addNetworkNode(v6);

        network.addConnection(v1, v2, 10);
        network.addConnection(v1, v3, 50);
        network.addConnection(v2, v3, 20);
        network.addConnection(v2, v4, 20);
        network.addConnection(v2, v5, 10);
        network.addConnection(v3, v4, 20);
        network.addConnection(v4, v5, 30);
        network.addConnection(v4, v6, 10);
        network.addConnection(v5, v6, 20);

        network.printConnections();
    }

    public void storageUnitsConversion()
    {
        Network network = new Network("192.168.100.0");
        Computer computer = new Computer("PC", "A", network, "192.168.100.0", 10);
        System.out.println(computer.getStorageSize());
        System.out.println(computer.getStorageSizeInUnits(StorageUnit.Gigabytes));
    }

    public void printIdentifiableNodes()
    {
        Network network = new Network("192.168.100.0");

        Node v1 = new Router("v1", "A", network, "192.168.100.1");
        Node v2 = new Router("v2", "B", network, "192.168.10.2");
        Node v3 = new Switch("v3", "C", network);
        Node v4 = new Switch("v4", "D", network);

        network.addNetworkNode(v1);
        network.addNetworkNode(v2);
        network.addNetworkNode(v3);
        network.addNetworkNode(v4);

        network.printIdentifiableNodes();
    }
}
