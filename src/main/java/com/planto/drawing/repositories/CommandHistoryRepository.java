package com.planto.drawing.repositories;

import com.planto.drawing.entities.CommandHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandHistoryRepository extends JpaRepository<CommandHistoryEntity, Integer> {

    @Query(
            value = "SELECT canvas FROM command_history ORDER BY id DESC LIMIT 1",
            nativeQuery = true
    )
    String getLast();
}
