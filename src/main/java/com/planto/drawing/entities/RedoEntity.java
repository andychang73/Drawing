package com.planto.drawing.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "redo")
public class RedoEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, name = "canvas")
    private String canvas;
}
