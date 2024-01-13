//package com.goalkeeper.goalkeeperbe.domain.users.application;
//
//import com.goalkeeper.goalkeeperbe.domain.users.repository.PreferRepository;
//import com.goalkeeper.goalkeeperbe.domain.users.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class MyPageService {
//
//    private final UserRepository userRepository;
//    private final PreferRepository preferRepository;
//
////    public MyPageResponse getMyPage(String authorizationHeader) {
////        String token = jwtProvider.getTokenFromAuthorizationHeader(authorizationHeader);
////        Long userId = jwtProvider.getUserIdFromToken(token);
////
////        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException());
////        Prefer prefer = preferRepository.findByUser(user).orElseThrow(() -> new IllegalArgumentException());
////
////        return MyPageResponse.builder()
////                .userGender(user.getUserGender())
////                .userNickname(user.getUserNickname())
////                .year(user.getYear())
////                .preferPersonal(prefer.getPreferPersonal())
////                .preferOrganization(prefer.getPreferOrganization())
////                .build();
////    }
//}
