package com.github.zhgxun.talk.service.impl;

import com.github.zhgxun.talk.common.exception.NormalException;
import com.github.zhgxun.talk.dao.BookDao;
import com.github.zhgxun.talk.dao.ItemDao;
import com.github.zhgxun.talk.entity.BookEntity;
import com.github.zhgxun.talk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private ItemDao itemDao;

    @Override
    public BookEntity add(BookEntity entity) {
        if (bookDao.findOne(0, entity.getTitle()) != null) {
            throw new NormalException("该书已经存在");
        }
        bookDao.add(entity);
        return entity;
    }

    @Override
    public BookEntity findOne(int id, String title) {
        return bookDao.findOne(id, title);
    }

    @Override
    public List<BookEntity> any(String title, String author, String nickName) {
        return bookDao.any(title, author, nickName);
    }

    @Override
    public int update(int id, String url, String description, int playCount) {
        return bookDao.update(id, url, description, playCount);
    }

    @Override
    public int delete(int id) {
        itemDao.delete(0, id);
        return bookDao.delete(id);
    }
}
