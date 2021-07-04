package com.zzff.jms.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProduceTopic {
    private final static String BROKERURL="failover://tcp://192.168.56.10:61616";
    private final static String TOPICNAME="zzff_topic_test_01";

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
        Topic topic = session.createTopic(TOPICNAME);
        //5、创建生
        MessageProducer producer = session.createProducer(topic);
        //6、发送消息
        for (int i = 1; i <=3 ; i++) {
            TextMessage textMessage = session.createTextMessage("往" + TOPICNAME + "中发送消息,第"+i+"条");
            producer.send(textMessage);
            System.out.println("往" + TOPICNAME + "中发送消息,第"+i+"条");
        }
        //7、关闭连接
        producer.close();
        session.close();
        connection.close();
    }
}
