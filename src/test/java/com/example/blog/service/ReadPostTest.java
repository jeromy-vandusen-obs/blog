package com.example.blog.service;

import com.example.blog.domain.PostRepository;
import com.example.blog.domain.model.Post;
import com.example.blog.service.presenter.Presenter;
import com.example.blog.view.model.PostView;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReadPostTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    private PostRepository postRepository;

    @Mock
    private Presenter<Post, PostView> postPresenter;

    @Mock
    private Presenter<Post, PostView> populatedPostPresenter;

    @Test
    public void with_whenIdIsNull_throwsNullPointerException() {
        ReadPost readPost = new ReadPost(postRepository);

        exception.expect(NullPointerException.class);
        readPost.with(null, postPresenter);
    }

    @Test
    public void with_whenPresenterIsNull_throwsNullPointerException() {
        ReadPost readPost = new ReadPost(postRepository);

        exception.expect(NullPointerException.class);
        readPost.with("id", null);
    }

    @Test
    public void with_whenPostIsFound_returnsPresenterForPost() {
        Post post = new Post(ZonedDateTime.now(), "title", "content");
        when(postRepository.findById("id")).thenReturn(Optional.of(post));
        ReadPost readPost = new ReadPost(postRepository);
        when(postPresenter.with(post)).thenReturn(populatedPostPresenter);

        Presenter<Post, PostView> result = readPost.with("id", postPresenter);

        assertThat(result).isNotNull().isEqualTo(populatedPostPresenter);
    }

    @Test
    public void with_whenPostIsNotFound_returnsPresenterForEmptyPost() {
        when(postRepository.findById("id")).thenReturn(Optional.empty());
        ReadPost readPost = new ReadPost(postRepository);
        when(postPresenter.with(null)).thenReturn(populatedPostPresenter);

        Presenter<Post, PostView> result = readPost.with("id", postPresenter);

        assertThat(result).isNotNull().isEqualTo(populatedPostPresenter);
    }
}
