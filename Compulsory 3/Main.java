package com.networkroutingproblem;

public class Main {

    public static void main(String[] args) {
	    Network network = new Network("192.168.100.0");

        Switch networkSwitch = new Switch("100.0.0.0", "main", "abcAB");

        network.addNetworkNode(networkSwitch);

        network.printNodes();
    }
}
