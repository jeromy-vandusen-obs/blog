package com.example.blog.view;

import com.example.blog.domain.model.Post;
import com.example.blog.service.presenter.Presenter;
import com.example.blog.view.model.PostView;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class PostPresenterTest {
    @Test
    public void with_always_returnsNewPostPresenter() {
        Post post = new Post(ZonedDateTime.of(2000, 1, 1, 12, 0, 0, 0, ZoneId.of("UTC")), "title", "content");
        PostPresenter postPresenter = new PostPresenter();

        Presenter<Post, PostView> result = postPresenter.with(post);

        assertThat(result).isNotNull().isNotEqualTo(postPresenter);
    }

    @Test
    public void present_whenPostIsNull_returnsEmptyPostView() {
        PostPresenter postPresenter = new PostPresenter();

        PostView result = postPresenter.present(Locale.CANADA);

        assertThat(result).isNotNull();
        assertThat(result.getTimestamp()).isNotNull().isEqualTo("");
        assertThat(result.getTitle()).isNotNull().isEqualTo("");
        assertThat(result.getContent()).isNotNull().isEqualTo("");
    }

    @Test
    public void present_whenPostIsValid_returnsPostViewPopulatedFromPost() {
        Post post = new Post(ZonedDateTime.of(2000, 1, 1, 12, 0, 0, 0, ZoneId.of("UTC")), "title", "content");
        PostPresenter postPresenter = new PostPresenter(post);

        PostView result = postPresenter.present(Locale.CANADA);

        assertThat(result).isNotNull();
        assertThat(result.getTimestamp()).isNotNull().isEqualTo("Jan 1, 2000 12:00:00 UTC");
        assertThat(result.getTitle()).isNotNull().isEqualTo("title");
        assertThat(result.getContent()).isNotNull().isEqualTo("content");
    }
}
