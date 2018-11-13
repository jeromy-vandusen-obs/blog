package com.example.blog.service.presenter;

import java.util.Locale;

public interface Presenter<I, O> {
    Presenter<I, O> with(I input);

    O present(Locale locale);
}
