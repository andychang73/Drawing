package com.planto.drawing.repositories;

import com.planto.drawing.entities.CommandHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandHistoryRepository extends JpaRepository<CommandHistoryEntity, Integer> {

    @Query(
            value = "SELECT canvas FROM command_history ORDER BY id DESC LIMIT 1",
            nativeQuery = true
    )
    String getLast();

    @Query(
            value = "SELECT id FROM command_history ORDER BY id DESC LIMIT 1",
            nativeQuery = true
    )
    int getLastId();

    @Query(
            value = "SELECT canvas FROM command_history WHERE id = ?1",
            nativeQuery = true
    )
    String selectById(int index);

    @Query(
            value = "SELECT COUNT(id) FROM command_history",
            nativeQuery = true
    )
    long count();

    @Modifying
    @Query(
            value = "DELETE FROM command_history WHERE id > ?1",
            nativeQuery = true
    )
    void clear(int index);
}
