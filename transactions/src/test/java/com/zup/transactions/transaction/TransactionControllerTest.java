package com.zup.transactions.transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@Transactional
class TransactionControllerTest {

    @Autowired
    private EntityManager manager;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveriaRetornar200() throws Exception {
        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/transaction/credit-card/1234-5678-9876-5432")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());


        TransactionListResponse responseDto = mapper.readValue(response.andReturn().getResponse().getContentAsString(), TransactionListResponse.class);

        assertEquals(10, responseDto.getTransactions().size());
        assertEquals("1234-5678-9876-5432", responseDto.getCardNumber());
    }

    @Test
    void deveriaRetornar404() throws Exception {
        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/transaction/credit-card/9999-9999-9999-9999")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());

    }
}