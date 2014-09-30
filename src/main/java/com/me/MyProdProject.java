package com.me;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.SubmitOptions;
import backtype.storm.generated.TopologyInitialStatus;
import backtype.storm.topology.TopologyBuilder;

public class MyProdProject {
    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
        Config c = new Config();

        c.setNumWorkers(4);

        TopologyBuilder topologyBuilder = new TopologyBuilder();

        topologyBuilder.setSpout("spout", new MySpout(), 2);

        topologyBuilder.setBolt("bolt", new MyBolt(), 10).shuffleGrouping("spout");

        SubmitOptions so = new SubmitOptions(TopologyInitialStatus.INACTIVE);

        StormSubmitter.submitTopologyWithProgressBar("log-test-topology", c, topologyBuilder.createTopology(), so);

    }
}
