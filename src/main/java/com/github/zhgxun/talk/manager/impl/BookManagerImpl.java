package com.github.zhgxun.talk.manager.impl;

import com.github.zhgxun.talk.manager.BookManager;
import com.github.zhgxun.talk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookManagerImpl implements BookManager {

    @Autowired
    private BookService bookService;
}
