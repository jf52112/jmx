package com.zzff.jms.springjms;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@Service
public class SpringMQ_Consumer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQ_Consumer consumer =(SpringMQ_Consumer) context.getBean("springMQ_Consumer");
        String res=(String)consumer.jmsTemplate.receiveAndConvert();
        System.out.println("***********consumer接收消息="+res);
    }
}
