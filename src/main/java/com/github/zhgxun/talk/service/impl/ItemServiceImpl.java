package com.github.zhgxun.talk.service.impl;

import com.github.zhgxun.talk.dao.ItemDao;
import com.github.zhgxun.talk.entity.ItemEntity;
import com.github.zhgxun.talk.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public ItemEntity add(ItemEntity entity) {
        itemDao.add(entity);
        return entity;
    }

    @Override
    public ItemEntity findOne(int id) {
        return itemDao.findOne(id);
    }

    @Override
    public List<ItemEntity> any(int id, int bookId, String author, String name) {
        return itemDao.any(id, bookId, author, name);
    }

    @Override
    public int delete(int id, int bookId) {
        return itemDao.delete(id, bookId);
    }
}
