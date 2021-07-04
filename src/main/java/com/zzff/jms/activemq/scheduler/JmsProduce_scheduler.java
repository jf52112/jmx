package com.zzff.jms.activemq.scheduler;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;

public class JmsProduce_scheduler {
    private final static String BROKERURL="tcp://192.168.56.10:61618";
    private final static String QUEUENAME="mess_shceduler";

    public static void main(String[] args) throws JMSException {
        createJmsProducer();
    }

    private static void createJmsProducer() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKERURL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUENAME);
        MessageProducer producer = session.createProducer(queue);
        Long delay=3*1000L;
        Long period=2*1000L;
        int repeat=2;
        for (int i = 1; i <=3 ; i++) {
            TextMessage textMessage = session.createTextMessage("message===第"+i+"条");
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,delay);
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD,period);
            textMessage.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT,repeat);
            producer.send(textMessage);
            System.out.println("message===第"+i+"条");

        }
        //7、关闭连接
        producer.close();
        session.close();
        connection.close();
    }
}
