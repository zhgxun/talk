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
        if (bookDao.findOne(0, entity.getCategoryId(), entity.getTitle()) != null) {
            throw new NormalException("该书已经存在");
        }
        bookDao.add(entity);
        return entity;
    }

    @Override
    public BookEntity findOne(int id, int categoryId, String title) {
        return bookDao.findOne(id, categoryId, title);
    }

    @Override
    public List<BookEntity> findAny(int categoryId, String title, String author, String nickName) {
        return bookDao.findAny(categoryId, title, author, nickName);
    }

    @Override
    public int update(int id, String url, String description, int playCount) {
        BookEntity entity = bookDao.findOne(id, 0, null);
        if (entity == null) {
            throw new NormalException("图书不存在");
        }
        return bookDao.update(entity.getId(), url, description, playCount, 0, 0);
    }

    @Override
    public int delete(int id) {
        BookEntity entity = bookDao.findOne(id, 0, null);
        if (entity == null) {
            throw new NormalException("图书不存在");
        }
        itemDao.delete(0, entity.getId());
        return bookDao.delete(id);
    }
}
