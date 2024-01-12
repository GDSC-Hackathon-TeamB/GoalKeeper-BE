package com.goalkeeper.goalkeeperbe.domain.users.repository;

import com.goalkeeper.goalkeeperbe.domain.users.domain.Gender;
import com.goalkeeper.goalkeeperbe.domain.users.domain.Prefer;
import com.goalkeeper.goalkeeperbe.domain.users.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PreferRepository extends JpaRepository<Prefer, Long> {
    Optional<Prefer> findById(Long preferId);

    List<Prefer> findAll();

    Optional<Prefer> findByUser(User user);

    @Query("SELECT p.preferPersonal FROM prefer p WHERE p.user.userId = :userId")
    Optional<String> findPreferPersonalByUserId(@Param("userId") Long userId);

    @Query("SELECT p FROM prefer p INNER JOIN p.user u WHERE u.userGender = :gender")
    List<Prefer> findByUserGender(@Param("gender") Gender gender);


}