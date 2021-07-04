package com.zzff.jms.activemq.asyn;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;

import javax.jms.*;
import java.util.UUID;

public class JMSProduce_AsyncSeed {
    //private final static String BROKERURL="nio://192.168.56.10:61618";
    private final static String BROKERURL="tcp://192.168.56.10:61618";
    private final static String QUEUENAME="transport";

    public static void main(String[] args) throws JMSException {
        createJmsProducer();
    }

    private static void createJmsProducer() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKERURL);
        connectionFactory.setUseAsyncSend(true);//开启异步
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUENAME);
        //MessageProducer producer = session.createProducer(queue);
        ActiveMQMessageProducer producer = (ActiveMQMessageProducer) session.createProducer(queue);
        for (int i = 1; i <=3 ; i++) {
            TextMessage textMessage = session.createTextMessage("message===第"+i+"条");

            textMessage.setJMSMessageID(UUID.randomUUID().toString()+"----orderByzzff");
            String msgID=textMessage.getJMSMessageID();

            producer.send(textMessage, new AsyncCallback() {
                @Override
                public void onSuccess() {
                    System.out.println(msgID+" send ok");
                }

                @Override
                public void onException(JMSException e) {
                    System.out.println(msgID+" send failer");
                }
            });

            System.out.println("往" + QUEUENAME + "中发送textMessage消息,第"+i+"条");

        }
        producer.close();
        session.close();
        connection.close();
    }
}
