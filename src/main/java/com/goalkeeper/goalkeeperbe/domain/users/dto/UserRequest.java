package com.goalkeeper.goalkeeperbe.domain.users.dto;

public class UserRequest {
    private String id;
    private String nickname;
    private String pw;
    private String birthdate;
    private String gender;

    // 생성자, getter, setter 등을 추가할 수 있음

    // 예시로 생성자와 getter를 추가한 경우
    public UserRequest(String id, String nickname, String pw, String birthdate, String gender) {
        this.id = id;
        this.nickname = nickname;
        this.pw = pw;
        this.birthdate = birthdate;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}