package com.planto.drawing.repositories;

import com.planto.drawing.entities.UndoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UndoRepository extends JpaRepository<UndoEntity,Integer> {
}
