package com.github.zhgxun.lib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 单词类库
 */
public class WordLib {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 单词是否已经存在
     *
     * @param query 单词, 当然也包括短语和句子, 一般不适用句子, 如需要句子请去搜索引擎查找直接翻译
     * @return 单词是否存在
     */
    public boolean haveOne(String query) {
        try {
            Integer id = jdbcTemplate.queryForObject("SELECT id FROM word WHERE query = ?", ((rs, rowNum) -> rs.getInt("id")), query);
            return id >= 1;
        } catch (NullPointerException e) {
            // 不处理异常
        }

        return false;
    }
}
