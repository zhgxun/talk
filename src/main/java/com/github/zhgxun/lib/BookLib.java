package com.github.zhgxun.lib;

import com.github.zhgxun.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 图书类库
 */
public class BookLib {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 生成一个唯一数字
     *
     * @return 编号, 不依赖于主键, 主键可能被迁移或表发生更改时发生变化
     */
    public Integer getNumber() {
        try {
            Integer i = jdbcTemplate.queryForObject("SELECT MAX(number) FROM books", Integer.class);
            return ++i;
        } catch (NullPointerException e) {
            // 数据库无数据记录时抛空指针异常, 不予以理会
        }

        return 1;
    }

    /**
     * 图书是否存在
     *
     * @param title 图书标题
     * @return boolean
     */
    public boolean havaOne(String title) {
        try {
            String sql = "SELECT id FROM books WHERE query = ?";
            Integer id = jdbcTemplate.queryForObject(sql, ((rs, rowNum) -> rs.getInt("id")), title.trim());
            return id >= 1;
        } catch (NullPointerException e) {
            // 不处理异常
        }

        return false;
    }

    /**
     * 添加图书
     *
     * @param book 图书对象
     * @return 新增id
     */
    public int add(Book book) {
        try {
            return jdbcTemplate.update("INSERT INTO `books`(`number`, `title`, `author`, `publisher`, `date`) VALUES(?, ?, ?, ?, ?)", book.getNumber(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getDate());
        } catch (NullPointerException e) {
            // 不处理
        }

        return 0;
    }
}
