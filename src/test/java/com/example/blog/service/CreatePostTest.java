package com.example.blog.service;

import com.example.blog.domain.PostRepository;
import com.example.blog.domain.model.Post;
import com.example.blog.service.builder.PostBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreatePostTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    private PostRepository postRepository;

    @Mock
    private PostBuilder postBuilder;

    @Test
    public void with_whenTitleIsNull_throwsNullPointerException() {
        when(postBuilder.build(null, "content")).thenThrow(new NullPointerException());
        CreatePost createPost = new CreatePost(postRepository, postBuilder);

        exception.expect(NullPointerException.class);
        createPost.with(null, "content");
    }

    @Test
    public void with_whenContentIsNull_throwsNullPointerException() {
        when(postBuilder.build("title", null)).thenThrow(new NullPointerException());
        CreatePost createPost = new CreatePost(postRepository, postBuilder);

        exception.expect(NullPointerException.class);
        createPost.with("title", null);
    }

    @Test
    public void with_whenTitleAndContentAreValid_returnsIdOfInsertedPost() {
        Post post = new Post(ZonedDateTime.now(), "title", "content");
        when(postBuilder.build("title", "content")).thenReturn(post);
        Post persistedPost = new Post(ZonedDateTime.now(), "title", "content");
        persistedPost.setId("id");
        when(postRepository.insert(post)).thenReturn(persistedPost);
        CreatePost createPost = new CreatePost(postRepository, postBuilder);

        String result = createPost.with("title", "content");

        assertThat(result).isNotNull().isEqualTo("id");
    }
}
