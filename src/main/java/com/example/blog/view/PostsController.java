package com.example.blog.view;

import com.example.blog.service.ListPosts;
import com.example.blog.view.model.PostItemView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
public class PostsController {
    private final ListPosts listPosts;

    private final PostItemPresenter postItemPresenter;

    public PostsController(ListPosts listPosts, PostItemPresenter postItemPresenter) {
        this.listPosts = listPosts;
        this.postItemPresenter = postItemPresenter;
    }

    @ModelAttribute("posts")
    public List<PostItemView> posts(Locale locale) {
        return listPosts.descending(postItemPresenter).stream().map(presenter -> presenter.present(locale)).collect(Collectors.toList());
    }

    @GetMapping("/")
    public String view() {
        return "posts";
    }
}
