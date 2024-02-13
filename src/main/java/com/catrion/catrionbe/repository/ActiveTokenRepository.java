package com.catrion.catrionbe.repository;

import com.catrion.catrionbe.entity.ActiveToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ActiveTokenRepository extends JpaRepository<ActiveToken, Long> {

    Boolean existsByToken(String token);

    void deleteAllByUsername(String username);

    void deleteAllByCreatedDateBefore(LocalDateTime dateTime);

}