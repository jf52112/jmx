package com.zzff.jms.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsConsumerTopic {
    private final static String BROKERURL="failover://tcp://192.168.56.10:61616";
    private final static String TOPICNAME="zzff_topic_test_01";

    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("我是1号消费者");
        //1、创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKERURL);
        //2、创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3、创建会话，参数一个是事务 、一个是签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4、创建目的地
        Topic topic = session.createTopic(TOPICNAME);
        //5、创建
        MessageConsumer consumer = session.createConsumer(topic);


        //接收，监听 异步非阻塞
       consumer.setMessageListener((message -> {
          if(message!=null&&message instanceof TextMessage){
              TextMessage textMessage=(TextMessage)message;
              try {
                  System.out.println(textMessage.getText());
              } catch (JMSException e) {
                  e.printStackTrace();
              }
          }
       }));
        //等待读取
        System.in.read();
        //7、关闭连接
        consumer.close();
        session.close();
        connection.close();
    }
}
