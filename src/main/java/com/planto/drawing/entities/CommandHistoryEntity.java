package com.planto.drawing.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "command_history")
public class CommandHistoryEntity {

    @Id
    private Integer id;

    @Column(nullable = false, name = "command")
    private String command;

    @Column(columnDefinition = "text", nullable = false, name = "canvas")
    private String canvas;
}
