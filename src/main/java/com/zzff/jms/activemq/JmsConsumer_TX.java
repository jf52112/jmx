package com.zzff.jms.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsConsumer_TX {
    private final static String BROKERURL="failover://tcp://192.168.56.10:61616";
    private final static String QUEUENAME="queue_tx";

    public static void main(String[] args) throws JMSException, IOException {
        //1、创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKERURL);
        //2、创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3、创建会话，参数一个是事务 、一个是签收
        //Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
        //Session session = connection.createSession(false, Session.DUPS_OK_ACKNOWLEDGE);

        //4、创建目的地
        Queue queue = session.createQueue(QUEUENAME);
        //5、创建
        MessageConsumer consumer = session.createConsumer(queue);

        //6、接收,同步阻塞
        while(true){
            TextMessage textMessage = (TextMessage)consumer.receive(4000L);
            if(null!=textMessage){
                textMessage.acknowledge();//手动签收 CLIENT_ACKNOWLEDGE
                System.out.println("消费者接收到的消息"+textMessage.getText());
            }else{
                break;
            }
        }

        //7、关闭连接
        consumer.close();
        session.commit();
        session.close();
        connection.close();
    }
}
