package com.github.zhgxun.talk.controller;

import com.github.zhgxun.talk.common.util.ResponseUtil;
import com.github.zhgxun.talk.config.Code;
import com.github.zhgxun.talk.config.Constant;
import com.github.zhgxun.talk.config.Message;
import com.github.zhgxun.talk.entity.CategoryEntity;
import com.github.zhgxun.talk.manager.CategoryManager;
import com.github.zhgxun.talk.manager.bean.CategoryBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@Api(tags = {"类目管理"})
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryManager categoryManager;

    /**
     * 添加类目
     *
     * @param parentId 父类目标识, 根类目为0
     * @param name     类目名称, 不允许重复, 全局唯一
     * @param level    类目层级, 目前支持1-3级
     * @return 添加成功的类目详情
     */
    @ApiOperation(value = "添加类目", notes = "新建类目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "父类目标识, 根类目为0", defaultValue = "0", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "类目名称, 不允许重复, 全局唯一", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "level", value = "类目层级, 目前支持1-3级", defaultValue = "1", required = true, paramType = "query", dataType = "int")
    })
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseUtil<CategoryEntity> add(@RequestParam(name = "parentId", defaultValue = "0") @NotNull(message = "参数为空") int parentId,
                                            @RequestParam(name = "name") @NotNull(message = "参数为空") String name,
                                            @RequestParam(name = "level", defaultValue = "1") @NotNull(message = "参数为空") int level) {
        try {
            if (parentId < Constant.MIN_PARENT
                    || level < Constant.MIN_LEVEL
                    || level > Constant.MAX_LEVEL
                    || name.trim().length() < Constant.MIN_LENGTH
                    || name.trim().length() > Constant.MAX_LENGTH
            ) {
                return new ResponseUtil<>(Code.FAILED, Message.ERROR);
            }
            return new ResponseUtil<>(categoryManager.add(parentId, name, level));
        } catch (Exception e) {
            log.error("", e);
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }

    /**
     * 类目详情
     *
     * @param id   类目标识
     * @param name 类目名称, 完全匹配
     * @return 局部类目树
     */
    @ApiOperation(value = "类目详情", notes = "类目信息, 关联展示, 从高层级到低层级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "类目标识", defaultValue = "0", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "name", value = "类目名称, 完全匹配", paramType = "query", dataType = "String")
    })
    @RequestMapping(path = "/one", method = RequestMethod.GET)
    public ResponseUtil<CategoryBean> one(@RequestParam(name = "id", defaultValue = "0") @NotNull(message = "参数为空") int id,
                                          @RequestParam(name = "name", required = false, defaultValue = "") String name) {
        try {
            return new ResponseUtil<>(categoryManager.findOne(id, name.trim()));
        } catch (Exception e) {
            log.error("", e);
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }

    /**
     * 获取类目树
     *
     * @return 全量类目树
     */
    @ApiOperation(value = "获取类目树", notes = "类目列表")
    @RequestMapping(path = "/any", method = RequestMethod.GET)
    public ResponseUtil<List<CategoryBean>> any() {
        try {
            return new ResponseUtil<>(categoryManager.any());
        } catch (Exception e) {
            log.error("", e);
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }

    @ApiOperation(value = "类目删除", notes = "只删除类目, 不关联删除")
    @ApiImplicitParam(name = "id", value = "类目标识", defaultValue = "0", required = true, paramType = "query", dataType = "int")
    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public ResponseUtil<Integer> delete(@RequestParam(name = "id", defaultValue = "0") @NotNull(message = "参数为空") int id) {
        try {
            return new ResponseUtil<>(categoryManager.delete(id));
        } catch (Exception e) {
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }
}
