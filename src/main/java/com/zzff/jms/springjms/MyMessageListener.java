package com.zzff.jms.springjms;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if(message!=null&& message instanceof TextMessage){
            TextMessage textMessage=(TextMessage)message;
            try {
                System.out.println("MyMessageListener监听到信息"+textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
