package com.me;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import com.netflix.governator.guice.LifecycleInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MyBolt extends BaseRichBolt {
    private static final Logger logger = LoggerFactory.getLogger(MyBolt.class);
    private OutputCollector collector;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        LifecycleInjector.builder().usingBasePackages("com.me").build().createInjector();
        this.collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        //logger.error("GOT: {}", input);
        collector.ack(input);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        //no output
    }
}
