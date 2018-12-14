package com.github.zhgxun.talk.service;

import com.github.zhgxun.talk.entity.BookEntity;

import java.util.List;

public interface BookService {

    BookEntity add(BookEntity entity);

    BookEntity findOne(int id, int categoryId, String title);

    List<BookEntity> findAny(int categoryId, String title, String author, String nickName);

    int update(int id, String url, String description, int playCount);

    int delete(int id);
}
