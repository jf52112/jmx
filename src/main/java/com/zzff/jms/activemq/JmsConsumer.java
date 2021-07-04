package com.zzff.jms.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsConsumer {
    //private final static String BROKERURL="nio://192.168.56.10:61618";
    private final static String BROKERURL="tcp://192.168.56.10:61618";
    private final static String QUEUENAME="transport";

    public static void main(String[] args) throws JMSException, IOException {
        //1、创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKERURL);
        //2、创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3、创建会话，参数一个是事务 、一个是签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4、创建目的地
        Queue queue = session.createQueue(QUEUENAME);
        //5、创建
        MessageConsumer consumer = session.createConsumer(queue);

        //6、接收,同步阻塞
        /*while(true){
            TextMessage textMessage = (TextMessage)consumer.receive(4000L);
            if(textMessage.getText()!=null){
                System.out.println("消费者接收到的消息"+textMessage);
            }else{
                break;
            }
        }*/
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
