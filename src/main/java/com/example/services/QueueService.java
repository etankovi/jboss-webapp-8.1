package com.example.services;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.Queue;

@Stateless
public class QueueService {

    @Resource(lookup = "java:jboss/jms/ivt/JMS2CF")
    private ConnectionFactory factory;

    @Resource(lookup = "java:jboss/jms/ivt/IVTQueue")
    private Queue queue;

    public String  sendMessage(String message) throws NamingException, JMSException {
        
        try (JMSContext context = factory.createContext()) {
            context.createProducer().send(queue, message);
        }

        return "v3 (Still using statnard JAVA EE JMS APIs but simplified.)";
    }

}