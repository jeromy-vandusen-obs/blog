package com.example.blog.service.builder;

import com.example.blog.domain.model.Post;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class PostBuilderTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void build_whenTitleIsNull_throwsNullPointerException() {
        PostBuilder postBuilder = new PostBuilder();

        exception.expect(NullPointerException.class);
        postBuilder.build(null, "content");
    }

    @Test
    public void build_whenContentIsNull_throwsNullPointerException() {
        PostBuilder postBuilder = new PostBuilder();

        exception.expect(NullPointerException.class);
        postBuilder.build("title", null);
    }

    @Test
    public void build_whenTitleAndContentAreValid_buildsPostWithProvidedTitleAndContentAndCurrentTimestamp() {
        PostBuilder postBuilder = new PostBuilder();

        Post result = postBuilder.build("title", "content");

        assertThat(result).isNotNull();
        assertThat(result.getTimestamp()).isNotNull();
        assertThat(result.getTitle()).isEqualTo("title");
        assertThat(result.getContent()).isEqualTo("content");
    }
}
