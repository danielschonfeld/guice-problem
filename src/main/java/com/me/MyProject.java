package com.me;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;

public class MyProject {
    public static void main(String[] args) {
        LocalCluster localCluster = new LocalCluster();
        Config config = new Config();
        config.setDebug(false);

        TopologyBuilder topologyBuilder = new TopologyBuilder();

        topologyBuilder.setSpout("spout", new MySpout());

        topologyBuilder.setBolt("bolt", new MyBolt()).shuffleGrouping("spout");

        localCluster.submitTopology("topology", config, topologyBuilder.createTopology());

    }
}
