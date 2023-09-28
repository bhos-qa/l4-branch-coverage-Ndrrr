package com.qa.lab4.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTaskRequest {

    @NotNull
    private Long id;

    @NotBlank
    private String name;
    private Boolean done = Boolean.FALSE;

}
