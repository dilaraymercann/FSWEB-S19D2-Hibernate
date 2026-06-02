package com.workintech.s18d4.dto;
import java.util.List;

public record CustomerResponse(
        Long id,
        String email,
        Double salary
) {}