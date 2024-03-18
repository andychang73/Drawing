package com.planto.drawing.repositories;

import com.planto.drawing.entities.SymbolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SymbolRepository extends JpaRepository<SymbolEntity, Integer> {

    @Query(
            value = "SELECT symbol FROM symbol",
            nativeQuery = true
    )
    String getSymbol();
}
