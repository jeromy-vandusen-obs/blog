package com.example.blog.view;

import com.example.blog.domain.model.Post;
import com.example.blog.service.presenter.Presenter;
import com.example.blog.view.model.PostItemView;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class PostItemPresenter implements Presenter<Post, PostItemView> {
    private final Post post;

    public PostItemPresenter() {
        post = null;
    }

    public PostItemPresenter(Post post) {
        this.post = post;
    }

    @Override
    public Presenter<Post, PostItemView> with(Post post) {
        return new PostItemPresenter(post);
    }

    @Override
    public PostItemView present(Locale locale) {
        if (post == null) {
            return new PostItemView("", "");
        }
        return new PostItemView(post.getId(), post.getTitle());
    }
}
