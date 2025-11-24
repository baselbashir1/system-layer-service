package com.task.systemlayer.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.systemlayer.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @ExceptionHandler(WebClientResponseException.NotFound.class)
    public ResponseEntity<ErrorResponse> handleNotFound(WebClientResponseException.NotFound e) throws JsonProcessingException {
        ErrorResponse response = objectMapper.readValue(e.getResponseBodyAsString(), ErrorResponse.class);
        return new ResponseEntity<>(new ErrorResponse(response.message(), 404, null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WebClientResponseException.Conflict.class)
    public ResponseEntity<ErrorResponse> handleConflict(WebClientResponseException.Conflict e) throws JsonProcessingException {
        ErrorResponse response = objectMapper.readValue(e.getResponseBodyAsString(), ErrorResponse.class);
        return new ResponseEntity<>(new ErrorResponse(response.message(), 409, null), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<ErrorResponse> handleWebClientException(WebClientResponseException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getResponseBodyAsString(), e.getStatusCode().value(), null), e.getStatusCode());
    }
}