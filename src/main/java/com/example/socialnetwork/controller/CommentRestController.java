package com.example.socialnetwork.controller;

import com.example.socialnetwork.payloads.ApiResponse;
import com.example.socialnetwork.payloads.CommentDto;
import com.example.socialnetwork.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentRestController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto, @PathVariable("postId") String postId) throws Exception {
        CommentDto createdComment = this.commentService.createComment(commentDto, postId);

        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsOfPost(@PathVariable("postId") String postId) throws Exception {
        List<CommentDto> comments = this.commentService.findByPost(postId);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> updatePost(@RequestBody CommentDto commentDto, @PathVariable("commentId") String commentId) throws Exception {
        CommentDto comment = this.commentService.updateComment(commentDto, commentId);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
    @DeleteMapping("/comments/{commentId}")
    public ApiResponse deletePost(@PathVariable("commentId") String commentId) throws Exception {
        this.commentService.deleteComment(commentId);
        return new ApiResponse("Post id deleted successfully with this id:" + commentId, true);
    }
}
