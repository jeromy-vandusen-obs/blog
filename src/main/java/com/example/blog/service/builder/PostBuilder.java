package com.example.blog.service.builder;

import com.example.blog.domain.model.Post;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class PostBuilder {
    public Post build(String title, String content) {
        if (title == null || content == null) {
            throw new NullPointerException();
        }
        return new Post(ZonedDateTime.now(), title, content);
    }
}
