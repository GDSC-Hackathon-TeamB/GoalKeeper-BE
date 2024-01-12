package com.goalkeeper.goalkeeperbe.domain.matching.domain;

import com.goalkeeper.goalkeeperbe.domain.users.domain.Gender;
import com.goalkeeper.goalkeeperbe.domain.users.domain.Prefer;
import com.goalkeeper.goalkeeperbe.domain.users.domain.User;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    private Gender gender;
    private User user;
    private Prefer prefer;
    private String name;  // 이름
    private String preferPersonal;  // 선호하는 다짐
    private List<String> preferences;  // 선호도 목록
    private int year; // 나이

    public Person(User user, Prefer prefer) {
        this.user = user;
        this.prefer = prefer;
        this.gender = user.getUserGender();
        this.name = user.getUserNickname();
        this.preferPersonal = prefer.getPreferPersonal();
        this.year = user.getYear();
        this.preferences = new ArrayList<>();
    }
}