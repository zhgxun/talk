package com.github.zhgxun.talk.manager.impl;

import com.github.zhgxun.talk.entity.ItemEntity;
import com.github.zhgxun.talk.manager.ItemManager;
import com.github.zhgxun.talk.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemManagerImpl implements ItemManager {

    @Autowired
    private ItemService itemService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ItemEntity add(int bookId, String author, String name, String duration, String format, String url, String description) {
        ItemEntity entity = new ItemEntity();
        entity.setBookId(bookId);
        entity.setAuthor(author);
        entity.setName(name);
        entity.setDuration(duration);
        entity.setFormat(format);
        entity.setUrl(url);
        entity.setDescription(description);
        return itemService.add(entity);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ItemEntity findOne(int id) {
        return itemService.findOne(id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ItemEntity> any(int id, int bookId, String author, String name) {
        return itemService.findAny(id, bookId, author, name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int delete(int id, int bookId) {
        return itemService.delete(id, bookId);
    }
}
