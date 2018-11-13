package com.example.blog.service;

import com.example.blog.domain.PostRepository;
import com.example.blog.domain.model.Post;
import com.example.blog.service.presenter.Presenter;
import com.example.blog.view.model.PostView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ListPostsTest {
    @Mock
    private PostRepository postRepository;

    @Mock
    private Presenter<Post, PostView> postPresenter;

    @Mock
    private Presenter<Post, PostView> firstPopulatedPostPresenter;

    @Mock
    private Presenter<Post, PostView> secondPopulatedPostPresenter;

    @Mock
    private Presenter<Post, PostView> thirdPopulatedPostPresenter;

    @Test
    public void descending_whenNoPostsExist_returnsEmptyList() {
        when(postRepository.findAll()).thenReturn(new ArrayList<>());
        ListPosts listPosts = new ListPosts(postRepository);

        List<Presenter<Post, PostView>> result = listPosts.descending(postPresenter);

        assertThat(result).isNotNull().isEmpty();
    }

    @Test
    public void descending_whenPostsExist_listsAllPostsInDescendingTimestampOrder() {
        Post firstPost = new Post(ZonedDateTime.of(2000, 1, 1, 12, 0, 0, 0, ZoneId.of("UTC")), "First Post", "Lorem ipsum dolor sit amet.");
        Post secondPost = new Post(ZonedDateTime.of(2000, 1, 1, 12, 1, 0, 0, ZoneId.of("UTC")), "Second Post", "Lorem ipsum dolor sit amet.");
        Post thirdPost = new Post(ZonedDateTime.of(2000, 1, 2, 12, 0, 0, 0, ZoneId.of("UTC")), "Third Post", "Lorem ipsum dolor sit amet.");
        when(postRepository.findAll()).thenReturn(Arrays.asList(secondPost, thirdPost, firstPost));
        when(postPresenter.with(firstPost)).thenReturn(firstPopulatedPostPresenter);
        when(postPresenter.with(secondPost)).thenReturn(secondPopulatedPostPresenter);
        when(postPresenter.with(thirdPost)).thenReturn(thirdPopulatedPostPresenter);
        ListPosts listPosts = new ListPosts(postRepository);

        List<Presenter<Post, PostView>> result = listPosts.descending(postPresenter);

        assertThat(result).isNotNull().hasSize(3).containsExactly(thirdPopulatedPostPresenter, secondPopulatedPostPresenter, firstPopulatedPostPresenter);
    }
}
