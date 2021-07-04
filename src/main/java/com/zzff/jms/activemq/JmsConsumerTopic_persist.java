package com.zzff.jms.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsConsumerTopic_persist {
    private final static String BROKERURL="failover://tcp://192.168.56.10:61616";
    private final static String QUEUENAME="persist_topic";

    public static void main(String[] args) throws JMSException, IOException {
        //1、创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKERURL);
        //2、创建连接
        Connection connection = connectionFactory.createConnection();
        connection.setClientID("zzff");//创建一个订阅者ID

        //3、创建会话，参数一个是事务 、一个是签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4、创建目的地
        Topic topic = session.createTopic(QUEUENAME);
        //5、创建订阅者，订阅者的id为connection.setClientID("zzff")
        TopicSubscriber subscriber = session.createDurableSubscriber(topic, "remark...");

        connection.start();

        Message message = subscriber.receive();
        while (message!=null){
            TextMessage textMessage=(TextMessage)message;
            System.out.println(textMessage.getText());
            message=subscriber.receive(5000L);
        }
        //等待读取
        //7、关闭连接
        subscriber.close();
        session.close();
        connection.close();
    }
}
