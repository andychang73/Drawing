package com.planto.drawing.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "symbol")
public class SymbolEntity {

    @Id
    private Integer id;

    @Column(nullable = false, name = "symbol")
    private char symbol;
}
