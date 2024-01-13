package com.goalkeeper.goalkeeperbe.domain.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationRequest {

    @Schema(description = "Photo URL for verification", required = true)
    private String photoUrl;

    @Schema(description = "Verification details or comments", required = true)
    private String verificationDetails;

    // Add any other fields as needed for your specific use case
}