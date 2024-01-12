package com.goalkeeper.goalkeeperbe.domain.users.domain;

import com.goalkeeper.goalkeeperbe.global.shared.domain.BaseTimeEntity;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Email
    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_gender", nullable = true)
    private Gender userGender;

    @Column(name = "user_nickname", nullable = true)
    private String userNickname;

    @Column(name = "year", nullable = true)
    private Integer year;

    @Column()
    private LocalDateTime deletedAt;

    @PreDestroy()
    public void preDestroy() {
        this.deletedAt = LocalDateTime.now();
    }
}
