package com.planto.drawing.entities;

import lombok.*;

import javax.persistence.*;

// todo to be delete
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

    @Column(columnDefinition = "text", nullable = false, name = "canvas")
    private String canvas;
}
