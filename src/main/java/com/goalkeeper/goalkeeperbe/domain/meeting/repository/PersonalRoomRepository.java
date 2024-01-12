package com.goalkeeper.goalkeeperbe.domain.meeting.repository;

import com.goalkeeper.goalkeeperbe.domain.meeting.domain.PersonalRoom;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonalRoomRepository extends JpaRepository<PersonalRoom, Long> {
    Optional<PersonalRoom> findByPersonalRoomId(Long personalRoomId);

    @Query("SELECT pr FROM personal_room pr WHERE (pr.user1.userId = :userId OR pr.user2.userId = :userId)")
    Optional<PersonalRoom> findPersonalRoomByUserId(@Param("userId") Long userId);
}
