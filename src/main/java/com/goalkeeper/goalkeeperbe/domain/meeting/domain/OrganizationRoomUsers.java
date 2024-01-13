package com.goalkeeper.goalkeeperbe.domain.meeting.domain;

import com.goalkeeper.goalkeeperbe.domain.users.domain.User;
import com.goalkeeper.goalkeeperbe.global.shared.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Entity(name = "organization_room_users")
public class OrganizationRoomUsers extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_room_users_id")
    private Long organizationRoomUsersId;

    @OneToOne
    @JoinColumn(name = "organization_room_id", referencedColumnName = "organization_room_id")
    private OrganizationRoom organizationRoom;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
