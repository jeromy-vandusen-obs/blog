package com.example.blog.service;

import com.example.blog.domain.PostRepository;
import com.example.blog.domain.model.Post;
import com.example.blog.service.presenter.Presenter;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListPosts {
    private final PostRepository postRepository;

    public ListPosts(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public <T> List<Presenter<Post, T>> descending(Presenter<Post, T> presenter) {
        return postRepository.findAll().stream().sorted(Comparator.comparing(Post::getTimestamp).reversed()).map(presenter::with).collect(Collectors.toList());
    }
}
