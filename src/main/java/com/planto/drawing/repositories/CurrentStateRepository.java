package com.planto.drawing.repositories;

import com.planto.drawing.entities.CurrentStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentStateRepository extends JpaRepository<CurrentStateEntity, Integer> {

    @Query(
            value = "SELECT index FROM current_state WHERE id = 1",
            nativeQuery = true
    )
    int getCurrentIndex();

    @Modifying
    @Query(
            value = "UPDATE current_state SET index = ?1 WHERE id = 1",
            nativeQuery = true
    )
    void updateCurrentIndex(int index);

    @Modifying
    @Query(
            value = "UPDATE current_state SET reset_history = ?1 WHERE id = 1",
            nativeQuery = true
    )
    void updateResetHistory(boolean reset);

    @Query(
            value = "SELECT reset_history FROM current_state WHERE id = 1",
            nativeQuery = true
    )
    boolean isResetHistory();
}
