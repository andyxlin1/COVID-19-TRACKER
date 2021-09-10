package com.example.covidtracker;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoronaRepository extends JpaRepository<Corona,Long> {
    List<Corona> findByLastUpdateBetween(LocalDateTime from, LocalDateTime to);

    List<Corona> findByCombinedKey(String combinedKey);
}
