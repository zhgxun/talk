package com.github.zhgxun.talk.service;

import com.github.zhgxun.talk.entity.ItemEntity;

import java.util.List;

public interface ItemService {

    ItemEntity add(ItemEntity entity);

    ItemEntity findOne(int id);

    List<ItemEntity> any(int id, int bookId, String author, String name);

    int delete(int id, int bookId);
}
