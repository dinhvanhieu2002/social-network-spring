package com.example.socialnetwork.services.impl;

import com.example.socialnetwork.config.ApplicationConstants;
import com.example.socialnetwork.entity.Post;
import com.example.socialnetwork.entity.Role;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.payloads.PostDto;
import com.example.socialnetwork.payloads.UserDto;
import com.example.socialnetwork.repository.PostRepository;
import com.example.socialnetwork.repository.RoleRepository;
import com.example.socialnetwork.repository.UserRepository;
import com.example.socialnetwork.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto, String userId) {
        Post post = this.modelMapper.map(postDto, Post.class);

        User user = this.userRepository.findById(userId);

        post.setPostImage("default.png");
        post.setPostCreatedDate(new Date());
        post.setUserId(userId);

        Post savedPost = this.postRepository.save(post).getEntity();
        PostDto postResponse = modelMapper.map(savedPost, PostDto.class);
        postResponse.setUser(modelMapper.map(user, UserDto.class));
        return postResponse;
    }

    @Override
    public List<PostDto> findByUser(String userId) {

        List<Post> listOfPostByUser = this.postRepository.findByUser(userId);

        List<PostDto> postDto = listOfPostByUser.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDto;
    }

    @Override
    public PostDto updatePost(PostDto postDto, String postId) {
        Post post = this.postRepository.findById(postId);

        post.setPostTitle(postDto.getPostTitle());
        post.setPostContent(postDto.getPostContent());
        post.setPostImage(postDto.getPostImage());

        Post updatedPost = this.postRepository.update(post).getEntity();

        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(String postId) {
        Post post = this.postRepository.findById(postId);
        this.postRepository.delete(postId);
    }


}
