package com.waes.victor.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * The type Index controller test.
 *
 * @author Victor Wardi - @victorwardi on 2/11/2020
 */
@WebMvcTest(IndexController.class)
class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Should return index page.
     *
     * @throws Exception the exception
     */
    @Test
    public void shouldReturnIndexPage() throws Exception {

        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("index.html"));
    }

    /**
     * Should return javadocs page.
     *
     * @throws Exception the exception
     */
    @Test
    public void shouldReturnJavaDocsPage() throws Exception {

        mockMvc.perform(get("/javadocs"))
            .andExpect(status().isOk())
            .andExpect(view().name("/javadocs/index.html"));
    }
}