package com.goalkeeper.goalkeeperbe.domain.meeting.repository;

import com.goalkeeper.goalkeeperbe.domain.meeting.domain.OrganizationRoom;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRoomRepository extends JpaRepository<OrganizationRoom,Long> {
    Optional<OrganizationRoom> findByOrganizationRoomId(Long organizationRoomId);

}