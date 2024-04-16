package com.example.socialnetwork.services;

import com.example.socialnetwork.entity.Post;
import com.example.socialnetwork.payloads.PostDto;
import com.example.socialnetwork.payloads.UserDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, String userId);

    List<PostDto> findByUser(String userId);

    PostDto updatePost(PostDto postDto, String postId);
    void deletePost(String postId);
}
