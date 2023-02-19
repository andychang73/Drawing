package com.planto.drawing.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@Table(name = "command_history")
public class CommandHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "command")
    private String command;

    @Column(columnDefinition = "text", nullable = false, name = "canvas")
    private String canvas;
}
