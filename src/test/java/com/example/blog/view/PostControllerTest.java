package com.example.blog.view;

import com.example.blog.service.CreatePost;
import com.example.blog.service.ReadPost;
import com.example.blog.view.model.PostView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {
    @Mock
    private ReadPost readPost;

    @Mock
    private CreatePost createPost;

    @Mock
    private PostPresenter postPresenter;

    @Mock
    private PostPresenter populatedPostPresenter;

    @Test
    public void post_always_readsPostWithIdAndPresentsUsingPresenter() {
        PostController postController = new PostController(readPost, createPost, postPresenter);
        when(readPost.with("id", postPresenter)).thenReturn(populatedPostPresenter);
        PostView postView = new PostView("", "", "");
        when(populatedPostPresenter.present(Locale.CANADA)).thenReturn(postView);

        PostView result = postController.post("id", Locale.CANADA);

        assertThat(result).isNotNull().isEqualTo(postView);
    }

    @Test
    public void view_always_returnsPostTemplateName() {
        PostController postController = new PostController(readPost, createPost, postPresenter);

        String result = postController.view();

        assertThat(result).isNotNull().isEqualTo("post");
    }

    @Test
    public void create_whenBindingResultHasErrors_returnsPostTemplateName() {
        PostController postController = new PostController(readPost, createPost, postPresenter);
        PostView post = new PostView("", "title", "content");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        String result = postController.create(post, bindingResult);

        assertThat(result).isNotNull().isEqualTo("post");
    }

    @Test
    public void create_whenBindingResultHasNoErrors_createsPostAndRedirectsToViewOfCreatedPost() {
        PostController postController = new PostController(readPost, createPost, postPresenter);
        PostView post = new PostView("", "title", "content");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(createPost.with("title", "content")).thenReturn("id");

        String result = postController.create(post, bindingResult);

        assertThat(result).isNotNull().isEqualTo("redirect:/post/id");
    }
}
