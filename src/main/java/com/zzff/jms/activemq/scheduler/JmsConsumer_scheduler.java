package com.zzff.jms.activemq.scheduler;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsConsumer_scheduler {

    private final static String BROKERURL="tcp://192.168.56.10:61618";
    private final static String QUEUENAME="mess_shceduler";


    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKERURL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUENAME);
        MessageConsumer consumer = session.createConsumer(queue);


        //接收，监听 异步非阻塞
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {

                if(message!=null&&message instanceof TextMessage){
                    TextMessage textMessage=(TextMessage)message;
                    try {
                        System.out.println(textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //等待读取
        System.in.read();
        //7、关闭连接
        consumer.close();
        session.close();
        connection.close();
    }
}
