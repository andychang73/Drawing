package com.planto.drawing.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "current_state")
public class CurrentStateEntity {

    @Id
    private Integer id;

    private Integer index;

    private boolean resetHistory;
}
