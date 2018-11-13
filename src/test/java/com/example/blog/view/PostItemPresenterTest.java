package com.example.blog.view;

import com.example.blog.domain.model.Post;
import com.example.blog.service.presenter.Presenter;
import com.example.blog.view.model.PostItemView;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class PostItemPresenterTest {
    @Test
    public void with_always_returnsNewPostItemPresenter() {
        Post post = new Post(ZonedDateTime.of(2000, 1, 1, 12, 0, 0, 0, ZoneId.of("UTC")), "title", "content");
        post.setId("id");
        PostItemPresenter postItemPresenter = new PostItemPresenter();

        Presenter<Post, PostItemView> result = postItemPresenter.with(post);

        assertThat(result).isNotNull().isNotEqualTo(postItemPresenter);
    }

    @Test
    public void present_whenPostIsNull_returnsEmptyPostItemView() {
        PostItemPresenter postItemPresenter = new PostItemPresenter();

        PostItemView result = postItemPresenter.present(Locale.CANADA);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull().isEqualTo("");
        assertThat(result.getTitle()).isNotNull().isEqualTo("");
    }

    @Test
    public void present_whenPostIsValid_returnsPostViewPopulatedFromPost() {
        Post post = new Post(ZonedDateTime.of(2000, 1, 1, 12, 0, 0, 0, ZoneId.of("UTC")), "title", "content");
        post.setId("id");
        PostItemPresenter postItemPresenter = new PostItemPresenter(post);

        PostItemView result = postItemPresenter.present(Locale.CANADA);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull().isEqualTo("id");
        assertThat(result.getTitle()).isNotNull().isEqualTo("title");
    }
}
