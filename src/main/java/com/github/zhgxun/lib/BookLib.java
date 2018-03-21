package com.github.zhgxun.lib;

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
}
