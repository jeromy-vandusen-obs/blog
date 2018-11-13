package com.example.blog.service;

import com.example.blog.domain.PostRepository;
import com.example.blog.service.builder.PostBuilder;
import org.springframework.stereotype.Service;

@Service
public class CreatePost {
    private final PostRepository postRepository;

    private final PostBuilder postBuilder;

    public CreatePost(PostRepository postRepository, PostBuilder postBuilder) {
        this.postRepository = postRepository;
        this.postBuilder = postBuilder;
    }

    public String with(String title, String content) {
        return postRepository.insert(postBuilder.build(title, content)).getId();
    }
}
