package com.goalkeeper.goalkeeperbe.domain.users.dto;

public class LoginRequest {
    private String id;
    private String pw;

    // 생성자, getter, setter 등을 추가할 수 있음

    // 예시로 생성자와 getter를 추가한 경우
    public LoginRequest(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}