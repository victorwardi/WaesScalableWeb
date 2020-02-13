package com.waes.victor.api.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * The type Waes api error response.
 *
 * @author Victor Wardi - @victorwardi on 2/9/2020
 */
@Data
@AllArgsConstructor
@Builder
public class WaesApiErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    private HttpStatus status;
    private String message;
    private List<String> errors;
    private String trace;


}

