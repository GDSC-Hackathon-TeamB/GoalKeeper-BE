package com.goalkeeper.goalkeeperbe.domain.users.dto;

public class GroupMatchRequest {
    private String category;

    // 생성자, getter, setter 등을 추가할 수 있음

    // 예시로 생성자와 getter를 추가한 경우
    public GroupMatchRequest(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}