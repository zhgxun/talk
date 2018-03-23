package com.github.zhgxun.lib;

/**
 * 单词类库
 */
public class WordLib {

    /**
     * 表名前缀
     */
    private final static String baseTableName = "words";

    /**
     * 生成一张新表
     *
     * @param number 编号
     */
    public void addTable(Integer number) {
        // 单词表模板, 每本书或文档有编号, 根据编号对应一张单词表
        String template = "" +
                "CREATE TABLE IF NOT EXISTS `%s_%s` (" +
                "    `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "    `query` VARCHAR(120) NOT NULL COMMENT '源语言'," +
                "    `translation` VARCHAR(255) NOT NULL COMMENT '翻译结果'," +
                "    `phonetic` VARCHAR(120) NOT NULL COMMENT '默认音标, 默认是英式音标'," +
                "    `us-phonetic` VARCHAR(120) NOT NULL COMMENT '美式音标'," +
                "    `uk-phonetic` VARCHAR(120) NOT NULL COMMENT '英式音标'," +
                "    `us-speech` VARCHAR(120) NOT NULL COMMENT '美式发音'," +
                "    `uk-speech` VARCHAR(120) NOT NULL COMMENT '英式发音'," +
                "    `explains` TEXT NOT NULL COMMENT '基本释义'," +
                "    `web` TEXT NOT NULL COMMENT '词义'," +
                "    `l` VARCHAR(120) NOT NULL COMMENT '源语言和目标语言'," +
                "    `dict` VARCHAR(120) NOT NULL COMMENT '词典deeplink'," +
                "    `webdict` VARCHAR(120) NOT NULL COMMENT 'webdeeplink'," +
                "    `tSpeakUrl` VARCHAR(255) NOT NULL COMMENT '翻译结果发音地址'," +
                "    `speakUrl` VARCHAR(255) NOT NULL COMMENT '源语言发音地址'," +
                "    `example` TEXT NOT NULL COMMENT '书中原文例句'," +
                "    KEY `query`(`query`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单词管理表模板, 具体的单词按书本来编排, 一本书一个表';";
        String sql = String.format(template, baseTableName, number);
    }

    /**
     * 根据编号获取表名
     *
     * @param number 编号
     * @return 表名
     */
    private static String tableName(Integer number) {
        return String.format("%s_%d", baseTableName, number);
    }

    /**
     * 单词是否已经存在
     *
     * @param number 书或文档编号
     * @param query  单词, 当然也包括短语和句子, 一般不适用句子, 如需要句子请去搜索引擎查找直接翻译
     * @return 单词是否存在
     */
//    public boolean haveOne(Integer number, String query) {
//        try {
//            String sql = String.format("SELECT id FROM %s WHERE query = ?", tableName(number));
//        } catch (NullPointerException e) {
//            // 不处理异常
//        }
//
//        return false;
//    }
}
