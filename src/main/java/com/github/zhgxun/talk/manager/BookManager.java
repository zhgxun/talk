package com.github.zhgxun.talk.manager;

import com.github.zhgxun.talk.entity.BookEntity;

import java.util.List;

public interface BookManager {

    BookEntity add(int categoryId, String title, String author, String nickName, String url, String description, int playCount);

    BookEntity findOne(int id, int categoryId, String title);

    List<BookEntity> any(int categoryId, String title, String author, String nickName);

    int update(int id, String url, String description, int playCount);

    int delete(int id);
}
