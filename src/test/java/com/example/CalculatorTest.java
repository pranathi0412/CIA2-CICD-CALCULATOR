
package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddition() throws Exception {
        mockMvc.perform(get("/calculate")
                .param("a", "10")
                .param("b", "20")
                .param("operation", "add"))
                .andExpect(status().isOk())
                .andExpect(content().string("Result: 30.0"));
    }

    @Test
    public void testSubtraction() throws Exception {
        mockMvc.perform(get("/calculate")
                .param("a", "30")
                .param("b", "10")
                .param("operation", "subtract"))
                .andExpect(status().isOk())
                .andExpect(content().string("Result: 20.0"));
    }

    @Test
    public void testMultiplication() throws Exception {
        mockMvc.perform(get("/calculate")
                .param("a", "10")
                .param("b", "5")
                .param("operation", "multiply"))
                .andExpect(status().isOk())
                .andExpect(content().string("Result: 50.0"));
    }

    @Test
    public void testDivision() throws Exception {
        mockMvc.perform(get("/calculate")
                .param("a", "20")
                .param("b", "4")
                .param("operation", "divide"))
                .andExpect(status().isOk())
                .andExpect(content().string("Result: 5.0"));
    }
}

