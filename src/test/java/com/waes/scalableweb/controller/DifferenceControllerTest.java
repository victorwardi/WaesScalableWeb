package com.waes.scalableweb.controller;

import com.waes.scalableweb.service.DifferenceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Victor Wardi - @victorwardi on 2/8/2020
 */
@WebMvcTest(DifferenceController.class)
class DifferenceControllerTest {

    private static final String BASE_URL = "/v1/diff";

    private static final String LEFT_SIDE_ENDPOINT = BASE_URL + "/1/left";

    private static final String RIGHT_SIDE_ENDPOINT = BASE_URL + "/1/right";

    private static final String INVALID_SIDE_ENDPOINT = BASE_URL + "/1/top";

    private static final String DIFF_ENDPOINT = BASE_URL + "/1";

    private static final String JSON_ENCODED_ON_BASE64 = "{ \"encodedData\" : \"ewoibmFtZSIgOiAiVmljdG9yIFdhcmRpIiwKImNvbXBhbnkiIDogIldBRVMiLAoicm9sZSIgOiAiSmF2YSBEZXZlbG9wZXIiCn0=\"}";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DifferenceService differenceService;


    // Test if all endpoints exist.

    @Test
    void shouldLeftEndpointReturn201IfContentIsValid() throws Exception {

        this.mockMvc.perform(post(LEFT_SIDE_ENDPOINT)
            .contentType(MediaType.APPLICATION_JSON)
            .content(JSON_ENCODED_ON_BASE64)).andDo(print())
            .andExpect(status().isCreated()).andExpect(content().string(""));

    }

    @Test
    void shouldRightEndpointReturn201IfContentIsValid() throws Exception {

        this.mockMvc.perform(post(RIGHT_SIDE_ENDPOINT)
            .contentType(MediaType.APPLICATION_JSON)
            .content(JSON_ENCODED_ON_BASE64)).andDo(print())
            .andExpect(status().isCreated()).andExpect(content().string(""));


    }


    @Test
    void shouldGetDiffEndpointReturn200IfRequestIsValid() throws Exception {
        this.mockMvc.perform(get(DIFF_ENDPOINT)).andExpect(status().isOk());
    }

    //Test invalid data on POST endpoints


    @Test
    void shouldPostSideEndpointReturn400IfSideIsInvalid() throws Exception {

        this.mockMvc.perform(post(INVALID_SIDE_ENDPOINT)
            .contentType(MediaType.APPLICATION_JSON)
            .content(""))
            .andExpect(status().isBadRequest());


    }


    @Test
    void shouldPostLeftSideEndpointReturn400IfRequestIsEmpty() throws Exception {

        this.mockMvc.perform(post(LEFT_SIDE_ENDPOINT)
            .contentType(MediaType.APPLICATION_JSON)
            .content(""))
            .andExpect(status().isBadRequest());


    }

    @Test
    void shouldPostRightSideEndpointReturn400IfRequestIsEmpty() throws Exception {

        this.mockMvc.perform(post(RIGHT_SIDE_ENDPOINT)
            .contentType(MediaType.APPLICATION_JSON)
            .content(""))
            .andExpect(status().isBadRequest());

    }




}

