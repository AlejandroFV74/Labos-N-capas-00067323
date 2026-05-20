package com.aafv.lab03.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSpecimenRequest {
    @NotBlank(message = "The specimen name cannot be empty.")
    private String name;

    @NotBlank(message = "The region of Hyrule must be specified.")
    private String region;

    @NotNull(message = "Danger level is required.")
    @Positive(message = "Danger level cannot be negative")
    private Integer dangerLevel;

    @NotNull(message = "You must specify if the specimen is friendly.")
    private Boolean isFriendly;
}
