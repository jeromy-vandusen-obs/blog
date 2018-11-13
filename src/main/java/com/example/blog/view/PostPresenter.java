package com.example.blog.view;

import com.example.blog.domain.model.Post;
import com.example.blog.service.presenter.Presenter;
import com.example.blog.view.model.PostView;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class PostPresenter implements Presenter<Post, PostView> {
    private final Post post;

    public PostPresenter() {
        post = null;
    }

    public PostPresenter(Post post) {
        this.post = post;
    }

    @Override
    public Presenter<Post, PostView> with(Post post) {
        return new PostPresenter(post);
    }

    @Override
    public PostView present(Locale locale) {
        if (post == null) {
            return new PostView("", "", "");
        }
        return new PostView(DateTimeFormatter.ofPattern("LLL d, yyyy hh:mm:ss z", locale).format(post.getTimestamp()), post.getTitle(), post.getContent());
    }
}
