package com.example.socialnetwork.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.seasar.doma.*;

@Entity(metamodel = @Metamodel)
@Table(name = "comments")
@Setter
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    private String id;

    private String content;

    @Column(name = "post_id")
    private String postId;
}
