package com.challenge.prodig.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.challenge.prodig.model.DocumentType;

@Data
public class PersonDTO {

    private Long id;

    @Schema(implementation = DocumentType.class, oneOf = DocumentType.class)
    @NotBlank(message = "invalid")
    private String documentType;

    @Schema(example = "20304061")
    @NotBlank
    @Size(min = 1, max = 12, message = "must be between 1 and 12 characters")
    private String documentNumber;

    @Schema(example = "1994-01-20")
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    @Past(message = "must be past")
    private Date birthDate;

    @Schema(example = "youremail@example.com")
    @Email(message = "should be valid")
    private String email;
}
