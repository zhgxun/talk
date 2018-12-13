package com.github.zhgxun.talk.service.impl;

import com.github.zhgxun.talk.dao.BookDao;
import com.github.zhgxun.talk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
}
