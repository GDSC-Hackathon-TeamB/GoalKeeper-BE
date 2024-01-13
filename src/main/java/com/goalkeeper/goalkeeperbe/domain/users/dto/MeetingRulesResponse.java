package com.goalkeeper.goalkeeperbe.domain.users.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeetingRulesResponse {

    @Schema(description = "List of rules for the group meeting", required = true)
    private List<String> rules;

    @Schema(description = "Meeting goals for the group", required = true)
    private String goals;

    // Add any other fields as needed for your specific use case
}