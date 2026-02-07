package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RootControllerTests {

    @Test
    void healthReturnsMessage() {
        RootController controller = new RootController();

        String response = controller.health();

        assertEquals("Backend is running. Try /api/products", response);
    }
}
