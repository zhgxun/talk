package com.github.zhgxun.talk.manager;

import com.github.zhgxun.talk.entity.ItemEntity;

import java.util.List;

public interface ItemManager {

    ItemEntity add(int bookId, String author, String name, String duration, String format, String url, String description);

    ItemEntity findOne(int id);

    List<ItemEntity> any(int id, int bookId, String author, String name);

    int delete(int id, int bookId);
}
