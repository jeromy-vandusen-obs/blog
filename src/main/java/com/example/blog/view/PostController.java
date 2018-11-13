package com.example.blog.view;

import com.example.blog.service.CreatePost;
import com.example.blog.service.ReadPost;
import com.example.blog.view.model.PostView;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class PostController {
    private final ReadPost readPost;

    private final CreatePost createPost;

    private final PostPresenter postPresenter;

    public PostController(ReadPost readPost, CreatePost createPost, PostPresenter postPresenter) {
        this.readPost = readPost;
        this.createPost = createPost;
        this.postPresenter = postPresenter;
    }

    @ModelAttribute("post")
    public PostView post(@PathVariable("id") String id, Locale locale) {
        return readPost.with(id, postPresenter).present(locale);
    }

    @GetMapping("/post/{id}")
    public String view() {
        return "post";
    }

    @PostMapping("/post/{id}")
    public String create(@Valid @ModelAttribute("post") PostView post, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "post";
        }
        return String.format("redirect:/post/%s", createPost.with(post.getTitle(), post.getContent()));
    }
}
