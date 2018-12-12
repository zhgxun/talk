package com.github.zhgxun.talk.entity;

import com.github.zhgxun.talk.common.util.DateUtil;
import lombok.Data;

@Data
class BaseEntity {
    private int id;
    private int creator = 1;
    private int updater = 1;
    private Long createTime = DateUtil.getTimeStamp();
    private Long updateTime = DateUtil.getTimeStamp();
}
