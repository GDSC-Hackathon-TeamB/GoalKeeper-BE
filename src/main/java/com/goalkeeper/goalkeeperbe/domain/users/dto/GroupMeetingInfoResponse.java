package com.goalkeeper.goalkeeperbe.domain.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupMeetingInfoResponse {

    @Schema(description = "Number of participants in the group meeting", required = true)
    private int participantsCount;

}