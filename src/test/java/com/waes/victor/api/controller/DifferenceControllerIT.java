package com.waes.victor.api.controller;

import com.waes.victor.api.service.DifferenceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Victor Wardi - @victorwardi on 2/8/2020
 */
@WebMvcTest(DifferenceController.class)
class DifferenceControllerIT {

    private static final String BASE_URL = "/v1/diff";

    private static final String DEFAULT_DIFF_ID_ENDPOINT = "/1";

    private static final String DIFF_ENDPOINT = BASE_URL + DEFAULT_DIFF_ID_ENDPOINT;

    private static final String SIDE_ENDPOINT_VALID = BASE_URL + DEFAULT_DIFF_ID_ENDPOINT + "/left";

    private static final String JSON_ENCODED_DATA = "{ \"encodedData\" : \"ewoibmFtZSIgOiAiVmljdG9yIFdhcmRpIiwKImNvbXBhbnkiIDogIldBRVMiLAoicm9sZSIgOiAiSmF2YSBEZXZlbG9wZXIiCn0=\"}";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DifferenceService differenceService;

    @ParameterizedTest(name = "Test valid side \"{arguments}\" - endpoint: " + DIFF_ENDPOINT + "/{arguments}")
    @ValueSource(strings = {"LEFT", "Left", "left", "RIGHT", "Right", "right"})
    void shouldSidePostEndpointReturn201IfSideAndContentAreValid(String side) throws Exception {

        this.mockMvc.perform(post(DIFF_ENDPOINT + "/" + side)
            .contentType(MediaType.APPLICATION_JSON)
            .content(JSON_ENCODED_DATA))
            .andExpect(status().isAccepted());
    }

    @ParameterizedTest(name = "Test invalid side \"{arguments}\" - endpoint: " + DIFF_ENDPOINT + "/{arguments}")
    @ValueSource(strings = {"LETF", "TOP", "new", "rihtg"})
    void shouldSidePostEndpointReturn400AndErrorIfSideIsInvalid(String side) throws Exception {

        this.mockMvc.perform(post(DIFF_ENDPOINT + "/" + side)
            .contentType(MediaType.APPLICATION_JSON)
            .content(JSON_ENCODED_DATA))
            .andExpect(status().isBadRequest());


    }

    @Test
    void shouldGetDiffEndpointReturn200IfRequestIsValid() throws Exception {
        this.mockMvc.perform(get(DIFF_ENDPOINT)).andExpect(status().isOk());
    }

    @Test
    void shouldGetDiffEndpointReturn404IfIdWasNotFound() throws Exception {

        when(differenceService.getDifference(anyString())).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        this.mockMvc.perform(get(DIFF_ENDPOINT)).andExpect(status().isNotFound());
    }


    @Test
    void shouldPostSideEndpointReturn400IfRequestContentIsEmpty() throws Exception {

        this.mockMvc.perform(post(SIDE_ENDPOINT_VALID)
            .contentType(MediaType.APPLICATION_JSON)
            .content(""))
            .andExpect(status().isBadRequest());
    }


    @ParameterizedTest(name = "Test invalid content type: {arguments}")
    @ValueSource(strings = {MediaType.APPLICATION_OCTET_STREAM_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_PDF_VALUE })
    void shouldPostSideEndpointReturn415IfRequestContentTypeIsNotJson(String contentType) throws Exception {

        this.mockMvc.perform(post(SIDE_ENDPOINT_VALID)
            .contentType(contentType))
            .andExpect(status().isUnsupportedMediaType());

    }

}

