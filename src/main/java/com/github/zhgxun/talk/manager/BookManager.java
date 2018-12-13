package com.github.zhgxun.talk.manager;

import com.github.zhgxun.talk.entity.BookEntity;

import java.util.List;

public interface BookManager {

    BookEntity add(String title, String author, String nickName, String url, String description, int playCount);

    BookEntity findOne(int id, String title);

    List<BookEntity> any(String title, String author, String nickName);

    int update(int id, String url, String description, int playCount);
}
