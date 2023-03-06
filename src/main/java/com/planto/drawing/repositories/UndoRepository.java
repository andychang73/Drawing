package com.planto.drawing.repositories;

import com.planto.drawing.entities.UndoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// todo to be delete
@Repository
public interface UndoRepository extends JpaRepository<UndoEntity,Integer> {

    @Query(
            value = "SELECT * FROM undo ORDER BY id DESC LIMIT 1",
            nativeQuery = true
    )
    UndoEntity getLast();

    @Modifying
    @Query(
            value = "DELETE FROM undo WHERE id = ?1",
            nativeQuery = true
    )
    void deleteLast(int id);

}
