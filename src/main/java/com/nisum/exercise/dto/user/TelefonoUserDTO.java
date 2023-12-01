package com.nisum.exercise.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelefonoUserDTO {

    private String number;
    @JsonProperty("citycode")
    private String cityCode;
    @JsonProperty("contrycode")
    private String contryCode;
}
