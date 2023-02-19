package com.planto.drawing.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@Table(name = "redo")
public class RedoEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, name = "canvas")
    private String canvas;
}
