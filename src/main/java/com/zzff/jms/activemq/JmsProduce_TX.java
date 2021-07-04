package com.zzff.jms.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProduce_TX {
    private final static String BROKERURL="failover://tcp://192.168.56.10:61616";
    private final static String QUEUENAME="queue_tx";

    public static void main(String[] args) throws JMSException {
        createJmsProducer();
    }

    private static void createJmsProducer() throws JMSException {
        //1、创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKERURL);
        //2、创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3、创建会话，参数一个是事务 、一个是签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4、创建目的地
        Queue queue = session.createQueue(QUEUENAME);
        //5、创建生
        MessageProducer producer = session.createProducer(queue);
        //producer.setDeliveryMode(DeliveryMode.PERSISTENT);//默默人就是持久化的
        //6、发送消息
        for (int i = 1; i <=3 ; i++) {
            TextMessage textMessage = session.createTextMessage("发送TX_textMessage消息,第"+i+"条");
            producer.send(textMessage);

        }
        //7、关闭连接
        producer.close();
        //session.commit();
        session.close();
        connection.close();
        System.out.println("消息发送成功JmsProduce_TX");
    }
}
