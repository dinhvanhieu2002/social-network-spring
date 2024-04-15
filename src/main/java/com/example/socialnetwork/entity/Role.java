package com.example.socialnetwork.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import org.seasar.doma.*;
;

@Entity(metamodel = @Metamodel)
@Setter
@Getter
@Table(name = "roles")
public class Role {

    @Id
    private int id;

    @Column
    private String name;
}