package com.github.zhgxun.talk.manager.impl;

import com.github.zhgxun.talk.entity.BookEntity;
import com.github.zhgxun.talk.manager.BookManager;
import com.github.zhgxun.talk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookManagerImpl implements BookManager {

    @Autowired
    private BookService bookService;

    @Override
    public BookEntity add(String title, String author, String nickName, String url, String description, int playCount) {
        BookEntity entity = new BookEntity();
        entity.setTitle(title);
        entity.setAuthor(author);
        entity.setNickName(nickName);
        entity.setUrl(url);
        entity.setDescription(description);
        entity.setPlayCount(playCount);
        bookService.add(entity);
        return entity;
    }

    @Override
    public BookEntity findOne(int id, String title) {
        return bookService.findOne(id, title);
    }

    @Override
    public List<BookEntity> any(String title, String author, String nickName) {
        return bookService.any(title, author, nickName);
    }

    @Override
    public int update(int id, String url, String description, int playCount) {
        return bookService.update(id, url, description, playCount);
    }
}
