package com.example.socialnetwork.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.seasar.doma.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(metamodel = @Metamodel)
@Table(name = "posts")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Post {
    @Id
    private String id;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_content")
    private String postContent;

    @Column(name = "post_image")
    private String postImage;

    @Column(name = "post_date")
    private Date postCreatedDate;

    @Column(name = "user_id")
    private String userId;

    @Transient private Set<Comment> comments = new HashSet<>();
}
