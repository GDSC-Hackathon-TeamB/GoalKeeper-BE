package com.goalkeeper.goalkeeperbe.domain.matching.api;

import com.goalkeeper.goalkeeperbe.domain.matching.application.MatchingService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MatchingController {
    private final MatchingService matchingService;
    @Scheduled(cron = "0 0 20 * * *", zone = "Asia/Seoul")
    public void runMatchingAlgorithm() {
        System.out.println("Matching algorithm starts");
        matchingService.match();
        matchingService.grouping();
    }
}
