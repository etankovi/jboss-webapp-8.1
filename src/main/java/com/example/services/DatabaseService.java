package com.example.services;

import com.example.entities.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class DatabaseService {

    @PersistenceContext(unitName = "testMessagePU")
    private EntityManager em;

    public void saveMessage(String messageContent) {
        TestMessage message = new TestMessage(messageContent);
        em.persist(message);
    }

}