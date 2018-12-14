package com.github.zhgxun.talk.service.impl;

import com.github.zhgxun.talk.common.exception.NormalException;
import com.github.zhgxun.talk.config.Message;
import com.github.zhgxun.talk.dao.BookDao;
import com.github.zhgxun.talk.dao.ItemDao;
import com.github.zhgxun.talk.entity.BookEntity;
import com.github.zhgxun.talk.entity.ItemEntity;
import com.github.zhgxun.talk.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private BookDao bookDao;

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
    public List<ItemEntity> findAny(int id, int bookId, String author, String name) {
        BookEntity entity = bookDao.findOne(bookId, 0, null);
        if (entity == null) {
            throw new NormalException(Message.ERROR);
        }
        return itemDao.findAny(id, bookId, author, name);
    }

    @Override
    public int delete(int id, int bookId) {
        ItemEntity entity = itemDao.findOne(id);
        if (entity == null) {
            throw new NormalException("章节不存在");
        }
        return itemDao.delete(entity.getId(), bookId);
    }
}
