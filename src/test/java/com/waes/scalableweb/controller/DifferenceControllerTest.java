package com.waes.scalableweb.controller;

import com.waes.scalableweb.service.DifferenceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Victor Wardi - @victorwardi on 2/8/2020
 */
@WebMvcTest(DifferenceController.class)
class DifferenceControllerTest {

    private static final String BASE_URL = "/v1/diff";

    private static final String LEFT_ENDPOINT = BASE_URL + "/1/left";

    private static final String RIGHT_ENDPOINT = BASE_URL + "/1/right";

    private static final String DIFF_ENDPOINT = BASE_URL + "/1";

    private static final String JSON_ENCODED_ON_BASE64 = "ewoibmFtZSIgOiAiVmljdG9yIFdhcmRpIiwKImNvbXBhbnkiIDogIldBRVMiLAoicm9sZSIgOiAiSmF2YSBEZXZlbG9wZXIiCn0=";
    private static final String JSON_ENCODED_ON_BASE642 = "ewoibmFtZSIgOiAiVmljdG9yIFdhcmRpIiwKImNvbXBhbnkiIDogIldBRVMiLAoicm9sZSIgOiAiSmF2YSBEZXZlbG9wZXIiCn0=";
    private static final String JSON_ENCODED_ON_BASE644 = "ewoibmFtZSIgOiAiVmljdG9yIFdhcmRpIiwKImNvbXBhbnkiIDogIldBRVMiLAoicm9sZSIgOiAiSmF2YSBEZXZlbG9wZXIiCn0=";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DifferenceService differenceService;

    @Test
    void leftEnpointIsReturning201() throws Exception {

        this.mockMvc.perform(post(LEFT_ENDPOINT)
            .content(JSON_ENCODED_ON_BASE64))
            .andExpect(status().isCreated());

    }


    @Test
    void leftEnpointIsValidatingEmptyBody() throws Exception {

        this.mockMvc.perform(post(LEFT_ENDPOINT)
            .content(""))
            .andExpect(status().isBadRequest());
    }


    @Test
    void checkIfEndpointToPostRightDataIsReturning201() throws Exception {
        this.mockMvc.perform(post(RIGHT_ENDPOINT)
            .content(JSON_ENCODED_ON_BASE64))
            .andExpect(status().isCreated());
    }

    @Test
    void checkIfEndpointToGetDifferenceIsReturning200() throws Exception {
        this.mockMvc.perform(get(DIFF_ENDPOINT)).andExpect(status().isOk());
    }

}

