package com.github.zhgxun.talk.controller;

import com.github.zhgxun.talk.common.util.ResponseUtil;
import com.github.zhgxun.talk.config.Code;
import com.github.zhgxun.talk.config.Constant;
import com.github.zhgxun.talk.entity.CategoryEntity;
import com.github.zhgxun.talk.manager.CategoryManager;
import com.github.zhgxun.talk.manager.bean.CategoryBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryManager categoryManager;

    @PostMapping("/add")
    public ResponseUtil<CategoryEntity> add(@RequestParam(name = "parentId") @NotNull(message = "参数为空") int parentId,
                                            @RequestParam(name = "name") @NotNull(message = "参数为空") String name,
                                            @RequestParam(name = "level") @NotNull(message = "参数为空") int level) {
        try {
            if (parentId < Constant.MIN_PARENT
                    || level < Constant.MIN_LEVEL
                    || level > Constant.MAX_LEVEL
                    || name.trim().length() < Constant.MIN_LENGTH
                    || name.trim().length() > Constant.MAX_LENGTH
            ) {
                return new ResponseUtil<>(Code.FAILED, "类目提交异常");
            }
            return new ResponseUtil<>(categoryManager.add(parentId, name, level));
        } catch (Exception e) {
            log.error("", e);
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }

    @GetMapping("/one")
    public ResponseUtil<CategoryBean> one(@RequestParam(name = "id") @NotNull(message = "参数为空") int id,
                                          @RequestParam(name = "name", required = false, defaultValue = "") String name) {
        try {
            return new ResponseUtil<>(categoryManager.findOne(id, name.trim()));
        } catch (Exception e) {
            log.error("", e);
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }

    @GetMapping("/any")
    public ResponseUtil<List<CategoryBean>> any() {
        try {
            return new ResponseUtil<>(categoryManager.any());
        } catch (Exception e) {
            log.error("", e);
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }
}
