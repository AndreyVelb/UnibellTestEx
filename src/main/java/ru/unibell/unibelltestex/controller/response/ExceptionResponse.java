package ru.unibell.unibelltestex.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExceptionResponse {

    @JsonProperty(value = "exceptionMessage")
    private final String exceptionMessage;

}
