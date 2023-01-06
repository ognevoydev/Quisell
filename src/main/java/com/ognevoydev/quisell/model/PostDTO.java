package com.ognevoydev.quisell.model;

import jakarta.validation.constraints.NotBlank;

public record PostDTO(
        @NotBlank
        String title,
        @NotBlank
        String description,
        Integer price,
        Boolean used){}