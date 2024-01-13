package com.goalkeeper.goalkeeperbe.domain.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndividualMatchRequest {

    @Schema(description = "Category (e.g., 운동, 공부, 독서, 데일리)", required = true)
    private String category;

    @Schema(description = "User's goal text", required = true)
    private String goalText;

    // Add any other fields as needed for your specific use case
}