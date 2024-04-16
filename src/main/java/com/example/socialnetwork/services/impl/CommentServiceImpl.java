package com.example.socialnetwork.services.impl;

import com.example.socialnetwork.entity.Comment;
import com.example.socialnetwork.entity.Post;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.payloads.CommentDto;
import com.example.socialnetwork.payloads.PostDto;
import com.example.socialnetwork.payloads.UserDto;
import com.example.socialnetwork.repository.CommentRepository;
import com.example.socialnetwork.repository.PostRepository;
import com.example.socialnetwork.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, String postId) {
        Post post = this.postRepository.findById(postId);
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPostId(postId);


        Comment savedComment = this.commentRepository.save(comment).getEntity();
        CommentDto commentResponse = modelMapper.map(savedComment, CommentDto.class);
        return commentResponse;
    }

    @Override
    public List<CommentDto> findByPost(String postId) {
        List<Comment> listOfCommentByPost = this.commentRepository.findByPost(postId);

        List<CommentDto> commentDto = listOfCommentByPost.stream().map((comment) -> this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());

        return commentDto;
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, String commentId) {
        Comment comment = this.commentRepository.findById(commentId);

        comment.setContent(commentDto.getContent());

        Comment updatedComment = this.commentRepository.update(comment).getEntity();

        return this.modelMapper.map(updatedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(String commentId) {
        Comment post = this.commentRepository.findById(commentId);
        this.commentRepository.delete(commentId);
    }
}
