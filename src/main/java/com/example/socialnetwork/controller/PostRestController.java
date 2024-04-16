package com.example.socialnetwork.controller;

import com.example.socialnetwork.payloads.ApiResponse;
import com.example.socialnetwork.payloads.JwtAuthenticationRequest;
import com.example.socialnetwork.payloads.JwtAuthenticationResponse;
import com.example.socialnetwork.payloads.PostDto;
import com.example.socialnetwork.services.PostService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostRestController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable("userId") String userId) throws Exception {
        PostDto createdPost = this.postService.createPost(postDto, userId);

        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsOfUser(@PathVariable("userId") String userId) throws Exception {
        List<PostDto> posts = this.postService.findByUser(userId);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") String postId) throws Exception {
        PostDto post = this.postService.updatePost(postDto, postId);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable("postId") String postId) throws Exception {
        this.postService.deletePost(postId);
        return new ApiResponse("Post id deleted successfully with this id:" + postId, true);
    }

}
