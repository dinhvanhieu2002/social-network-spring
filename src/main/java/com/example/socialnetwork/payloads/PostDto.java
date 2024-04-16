package com.example.socialnetwork.payloads;

import com.example.socialnetwork.entity.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.example.socialnetwork.config.ApplicationUtils.generateUUID;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class PostDto {
    private String id = generateUUID();

    @NotBlank
    @Size(min = 5)
    private String postTitle;

    @NotBlank
    @Size(min = 10)
    private String postContent;

    private String postImage;

    private Date postCreatedDate;

    private UserDto user;

    private Set<CommentDto> comments = new HashSet<>();
}
