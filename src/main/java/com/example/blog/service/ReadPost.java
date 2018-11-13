package com.example.blog.service;

import com.example.blog.domain.PostRepository;
import com.example.blog.domain.model.Post;
import com.example.blog.service.presenter.Presenter;
import org.springframework.stereotype.Service;

@Service
public class ReadPost {
    private final PostRepository postRepository;

    public ReadPost(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public <T> Presenter<Post, T> with(String id, Presenter<Post, T> presenter) {
        if (id == null || presenter == null) {
            throw new NullPointerException();
        }
        return presenter.with(postRepository.findById(id).orElse(null));
    }
}
