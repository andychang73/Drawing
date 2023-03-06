package com.planto.drawing.repositories;

import com.planto.drawing.entities.RedoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// todo to be delete
@Repository
public interface RedoRepository extends JpaRepository<RedoEntity,Integer> {

    @Query(
            value = "SELECT * FROM redo ORDER BY id DESC LIMIT 1",
            nativeQuery = true
    )
    RedoEntity getLast();

    @Modifying
    @Query(
            value = "DELETE FROM redo WHERE id = ?1",
            nativeQuery = true
    )
    void deleteLast(int id);

    @Modifying
    @Query(
            value = "DELETE FROM redo",
            nativeQuery = true
    )
    void deleteAll();
}
