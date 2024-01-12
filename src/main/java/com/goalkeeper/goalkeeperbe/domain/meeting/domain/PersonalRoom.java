package com.goalkeeper.goalkeeperbe.domain.meeting.domain;

import com.goalkeeper.goalkeeperbe.domain.users.domain.Users;
import com.goalkeeper.goalkeeperbe.global.shared.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PersonalRoom extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personal_room_id")
    private Long personalRoomId;

    @ManyToOne
    @JoinColumn(name = "user1_id", nullable = false)
    private Users user1;

    @ManyToOne
    @JoinColumn(name = "user2_id", nullable = false)
    private Users user2;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
