package com.example.blog.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.Objects;

@Document("posts")
public class Post {
    @Id
    private String id;

    private final ZonedDateTime timestamp;

    private final String title;

    private final String content;

    public Post(ZonedDateTime timestamp, String title, String content) {
        this.timestamp = timestamp;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(timestamp, post.timestamp) &&
                Objects.equals(title, post.title) &&
                Objects.equals(content, post.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, title, content);
    }
}
