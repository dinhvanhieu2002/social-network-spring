package com.example.socialnetwork.services;

import com.example.socialnetwork.payloads.CommentDto;
import com.example.socialnetwork.payloads.PostDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, String postId);

    List<CommentDto> findByPost(String postId);

    CommentDto updateComment(CommentDto commentDto, String commentId);
    void deleteComment(String commentId);
}
