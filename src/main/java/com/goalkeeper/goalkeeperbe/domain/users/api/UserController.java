package com.goalkeeper.goalkeeperbe.domain.users.api;

import com.goalkeeper.goalkeeperbe.domain.users.domain.Prefer;
import com.goalkeeper.goalkeeperbe.domain.users.domain.User;
import com.goalkeeper.goalkeeperbe.domain.users.dto.BadgeCollectionResponse;
import com.goalkeeper.goalkeeperbe.domain.users.dto.GroupMatchRequest;
import com.goalkeeper.goalkeeperbe.domain.users.dto.GroupMeetingInfoResponse;
import com.goalkeeper.goalkeeperbe.domain.users.dto.IndividualMatchRequest;
import com.goalkeeper.goalkeeperbe.domain.users.dto.IndividualMeetingResponse;
import com.goalkeeper.goalkeeperbe.domain.users.dto.LoginRequest;
import com.goalkeeper.goalkeeperbe.domain.users.dto.MeetingRulesRequest;
import com.goalkeeper.goalkeeperbe.domain.users.dto.MeetingRulesResponse;
import com.goalkeeper.goalkeeperbe.domain.users.dto.UserInfoResponse;
import com.goalkeeper.goalkeeperbe.domain.users.dto.UserRequest;
import com.goalkeeper.goalkeeperbe.domain.users.dto.VerificationRequest;
import com.goalkeeper.goalkeeperbe.global.error.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "사용자")
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Operation(summary = "회원가입", description = "새로운 회원을 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "로그인", description = "회원 로그인을 수행합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "이웃사랑(단체) 매칭하기", description = "단체 매칭을 요청합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "매칭 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/group-match")
    public ResponseEntity<Void> groupMatch(@RequestBody GroupMatchRequest groupMatchRequest) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "이웃사랑(단체) 매칭 취소", description = "이웃사랑(단체) 매칭을 취소합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "매칭 취소 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/cancel-group-match")
    public ResponseEntity<Void> cancelGroupMatch() {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "이웃사랑(단체) 모임 규칙/목표 설정", description = "이웃사랑(단체) 모임의 규칙/목표를 설정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "규칙/목표 설정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/group-meeting/rules")
    public ResponseEntity<Void> setGroupMeetingRules(@RequestBody MeetingRulesRequest meetingRulesRequest) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "이웃사랑(단체) 모임 규칙/목표 조회", description = "이웃사랑(단체) 모임의 규칙/목표를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "규칙/목표 조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/group-meeting/rules")
    public ResponseEntity<MeetingRulesResponse> getGroupMeetingRules() {
        return ResponseEntity.ok(new MeetingRulesResponse());
    }

    @Operation(summary = "이웃사랑(단체) 모임 정보 조회", description = "이웃사랑(단체) 모임의 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 정보 조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/group-meeting/info")
    public ResponseEntity<GroupMeetingInfoResponse> getGroupMeetingInfo() {
        return ResponseEntity.ok(new GroupMeetingInfoResponse());
    }

    @Operation(summary = "이웃사랑(단체) 모임 인증하기", description = "이웃사랑(단체) 모임에 인증을 합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/group-meeting/verify")
    public ResponseEntity<Void> verifyGroupMeeting(@RequestBody VerificationRequest verificationRequest) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "자기계발(개인) 매칭하기", description = "개인 매칭을 요청합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "매칭 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/individual-match")
    public ResponseEntity<Void> individualMatch(@RequestBody IndividualMatchRequest individualMatchRequest) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "자기계발(개인) 매칭 취소", description = "자기계발(개인) 매칭을 취소합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "매칭 취소 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/cancel-individual-match")
    public ResponseEntity<Void> cancelIndividualMatch() {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "자기계발(개인) 모임 조회", description = "자기계발(개인) 모임을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/individual-meeting")
    public ResponseEntity<IndividualMeetingResponse> getIndividualMeeting() {
        return ResponseEntity.ok(new IndividualMeetingResponse());
    }

    @Operation(summary = "자기계발(개인) 모임 인증하기", description = "자기계발(개인) 모임에 인증을 합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/individual-meeting/verify")
    public ResponseEntity<Void> verifyIndividualMeeting(@RequestBody VerificationRequest verificationRequest) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "회원정보 조회", description = "회원 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원정보 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/user-info")
    public ResponseEntity<UserInfoResponse> getUserInfo() {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        return ResponseEntity.ok(userInfoResponse);
    }

    @Operation(summary = "로그아웃", description = "쿠키에 저장된 토큰을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그아웃 성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@CookieValue("refreshToken") String oldToken, HttpServletResponse res) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "모은배지 조회", description = "사용자가 모은 배지를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "배지 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/badge-collection")
    public ResponseEntity<BadgeCollectionResponse> getBadgeCollection() {
        BadgeCollectionResponse badgeCollectionResponse = new BadgeCollectionResponse();
        return ResponseEntity.ok(badgeCollectionResponse);
    }
}
