package com.challenge.prodig.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PageRequestDTO {

    @Schema(example = "1", defaultValue = "1")
    private Integer page = 1;

    @Schema(example = "20", defaultValue = "20")
    private Integer size = 20;

}
