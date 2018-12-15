package com.github.zhgxun.talk.manager.impl;

import com.github.zhgxun.talk.entity.BookEntity;
import com.github.zhgxun.talk.manager.BookManager;
import com.github.zhgxun.talk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookManagerImpl implements BookManager {

    @Autowired
    private BookService bookService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BookEntity add(int categoryId, String title, String author, String nickName, String url, String description, int playCount) {
        BookEntity entity = new BookEntity();
        entity.setCategoryId(categoryId);
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
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public BookEntity findOne(int id, int categoryId, String title) {
        return bookService.findOne(id, categoryId, title);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<BookEntity> any(int categoryId, String title, String author, String nickName) {
        return bookService.findAny(categoryId, title, author, nickName);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int update(int id, String url, String description, int playCount) {
        return bookService.update(id, url, description, playCount);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int delete(int id) {
        return bookService.delete(id);
    }
}
