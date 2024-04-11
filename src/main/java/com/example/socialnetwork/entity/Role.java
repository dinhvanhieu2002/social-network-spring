package com.example.socialnetwork.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import org.seasar.doma.*;
;

@Entity(metamodel = @Metamodel)
@Setter
@Getter
public class Role {

    @Id
    private int id;

    @Column
    private String name;
}