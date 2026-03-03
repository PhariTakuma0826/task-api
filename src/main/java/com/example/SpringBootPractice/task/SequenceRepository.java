package com.example.SpringBootPractice.task;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.LockModeType;
import java.util.Optional;

public interface SequenceRepository extends JpaRepository<Sequence, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Sequence s where s.name = :name")
    Optional<Sequence> findByNameForUpdate(@Param("name") String name);
}