
    package com.example.entities;

    import jakarta.persistence.*;

    @Entity
    @Table(name = "test_message")
    public class TestMessage {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "message")
        private String message;

        public TestMessage() {}

        public TestMessage(String message) {
            this.message = message;
        }

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }