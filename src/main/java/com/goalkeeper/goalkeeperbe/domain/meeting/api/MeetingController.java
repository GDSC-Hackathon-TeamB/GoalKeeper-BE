package com.goalkeeper.goalkeeperbe.domain.meeting.api;

import com.goalkeeper.goalkeeperbe.domain.matching.application.MatchingService;
import com.goalkeeper.goalkeeperbe.domain.meeting.application.MeetingService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MeetingController {
        private final MeetingService meetingService;
       @Scheduled(cron = "00 05 20 * * *", zone = "Asia/Seoul")
        public void runMatchingAlgorithm() {
            System.out.println("meeting algorithm starts");
            meetingService.match();
            meetingService.grouping();
        }
}