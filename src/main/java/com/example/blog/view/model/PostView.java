package com.example.blog.view.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public final class PostView {
    private String timestamp;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String content;

    public PostView() {
        super();
    }

    public PostView(String timestamp, String title, String content) {
        this.timestamp = timestamp;
        this.title = title;
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
