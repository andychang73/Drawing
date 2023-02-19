package com.planto.drawing.repositories;

import com.planto.drawing.entities.RedoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedoRepository extends JpaRepository<RedoEntity,Integer> {
}
