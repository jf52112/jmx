package com.zzff.jms.activemq.mini;

import org.apache.activemq.broker.BrokerService;

public class EmbedBroker {
    private final static String BROKERURL="tcp://localhost:61616";
    public static void main(String[] args) throws Exception {

        BrokerService brokerService=new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector(BROKERURL);
        brokerService.start();

    }
}
