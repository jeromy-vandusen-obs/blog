package com.example.blog.view.model;

public class PostItemView {
    private String id;

    private String title;

    public PostItemView() {
        super();
    }

    public PostItemView(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
