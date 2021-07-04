package com.zzff.jms.springjms;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@Service
public class SpringMQ_Produce {
    @Autowired
    private JmsTemplate jmsTemplate;
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQ_Produce produce =(SpringMQ_Produce) context.getBean("springMQ_Produce");
        produce.jmsTemplate.send((session -> {
            TextMessage textMessage = session.createTextMessage("这是一个spring整合jms的消息");
            return textMessage;
        }));
        System.out.println("***********send take over");
    }
}
