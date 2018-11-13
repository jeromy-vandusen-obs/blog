package com.example.blog.view;

import com.example.blog.service.ListPosts;
import com.example.blog.view.model.PostItemView;
import com.example.blog.view.model.PostView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostsControllerTest {
    @Mock
    private ListPosts listPosts;

    @Mock
    private PostItemPresenter postItemPresenter;

    @Mock
    private PostItemPresenter firstPopulatedPostPresenter;

    @Mock
    private PostItemPresenter secondPopulatedPostPresenter;

    @Mock
    private PostItemPresenter thirdPopulatedPostPresenter;

    @Test
    public void posts_always_listsPostsAndPresentsUsingPresenter() {
        when(listPosts.descending(postItemPresenter)).thenReturn(Arrays.asList(thirdPopulatedPostPresenter, secondPopulatedPostPresenter, firstPopulatedPostPresenter));
        PostItemView firstPostItemView = new PostItemView("id1", "First Post");
        PostItemView secondPostItemView = new PostItemView("id2", "Second Post");
        PostItemView thirdPostItemView = new PostItemView("id3", "Third Post");
        when(firstPopulatedPostPresenter.present(Locale.CANADA)).thenReturn(firstPostItemView);
        when(secondPopulatedPostPresenter.present(Locale.CANADA)).thenReturn(secondPostItemView);
        when(thirdPopulatedPostPresenter.present(Locale.CANADA)).thenReturn(thirdPostItemView);
        PostsController postsController = new PostsController(listPosts, postItemPresenter);

        List<PostItemView> result = postsController.posts(Locale.CANADA);

        assertThat(result).isNotNull().hasSize(3).containsExactly(thirdPostItemView, secondPostItemView, firstPostItemView);
    }


    @Test
    public void view_always_returnsPostsTemplateName() {
        PostsController postsController = new PostsController(listPosts, postItemPresenter);

        String result = postsController.view();

        assertThat(result).isNotNull().isEqualTo("posts");
    }
}
