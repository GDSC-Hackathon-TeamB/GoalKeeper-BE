package com.goalkeeper.goalkeeperbe.domain.matching.domain;

import com.goalkeeper.goalkeeperbe.domain.users.domain.Prefer;
import com.goalkeeper.goalkeeperbe.domain.users.domain.User;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Man extends Person {
    private boolean engaged;  // 남자의 현재 매칭 상태
    private List<Woman> proposed; // 남자의 프로포즈한 여자 목록

    public Man(User user, Prefer prefer) {
        super(user, prefer);
        this.engaged = false;
        this.proposed = new ArrayList<>();
    }
}