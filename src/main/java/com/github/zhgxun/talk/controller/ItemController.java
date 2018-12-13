package com.github.zhgxun.talk.controller;

import com.github.zhgxun.talk.common.exception.NormalException;
import com.github.zhgxun.talk.common.util.ResponseUtil;
import com.github.zhgxun.talk.config.Code;
import com.github.zhgxun.talk.config.Message;
import com.github.zhgxun.talk.entity.ItemEntity;
import com.github.zhgxun.talk.manager.ItemManager;
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

@Api(tags = {"图书章节管理"})
@RestController
@Slf4j
public class ItemController {

    @Autowired
    private ItemManager itemManager;

    @ApiOperation(value = "章节添加", notes = "章节添加不去重, 请自行确认是否有重复, 重复的章节调用删除接口删除即可")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookId", value = "图书标识", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "author", value = "作者", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "章节名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "duration", value = "章节播放时长", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "format", value = "播放音频格式", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "url", value = "播放地址, 本地存放路径为相对路径", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "description", value = "章节描述", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseUtil<ItemEntity> add(@RequestParam(name = "bookId") @NotNull(message = "参数为空") int bookId,
                                        @RequestParam(name = "author") @NotNull(message = "参数为空") String author,
                                        @RequestParam(name = "name") @NotNull(message = "参数为空") String name,
                                        @RequestParam(name = "duration") @NotNull(message = "参数为空") String duration,
                                        @RequestParam(name = "format") @NotNull(message = "参数为空") String format,
                                        @RequestParam(name = "url") @NotNull(message = "参数为空") String url,
                                        @RequestParam(name = "description") @NotNull(message = "参数为空") String description) {
        try {
            return new ResponseUtil<>(itemManager.add(bookId, author, name, duration, format, url, description));
        } catch (Exception e) {
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }

    @ApiOperation(value = "章节详情", notes = "获取详情, 暂无什么用")
    @ApiImplicitParam(name = "id", value = "章节标识", required = true, paramType = "query", dataType = "int")
    @RequestMapping(path = "/one", method = RequestMethod.GET)
    public ResponseUtil<ItemEntity> one(@RequestParam(name = "id") @NotNull(message = "参数为空") int id) {
        try {
            return new ResponseUtil<>(itemManager.findOne(id));
        } catch (Exception e) {
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }

    @ApiOperation(value = "章节列表", notes = "图书章节列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "章节标识", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "bookId", value = "图书标识", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "author", value = "图书作者", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "章节名称", paramType = "query", dataType = "String"),
    })
    @RequestMapping(path = "/any", method = RequestMethod.GET)
    public ResponseUtil<List<ItemEntity>> any(@RequestParam(name = "id", required = false) int id,
                                              @RequestParam(name = "bookId") @NotNull(message = "参数为空") int bookId,
                                              @RequestParam(name = "author", required = false) String author,
                                              @RequestParam(name = "name", required = false) String name) {
        try {
            return new ResponseUtil<>(itemManager.any(id, bookId, author, name));
        } catch (Exception e) {
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }

    @ApiOperation(value = "章节删除", notes = "章节添加错误时使用")
    @ApiImplicitParam(name = "id", value = "章节标识", required = true, paramType = "query", dataType = "int")
    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public ResponseUtil<Integer> delete(@RequestParam(name = "id") @NotNull(message = "参数为空") int id) {
        try {
            if (id < 0) {
                throw new NormalException(Message.ERROR);
            }
            return new ResponseUtil<>(itemManager.delete(id, 0));
        } catch (Exception e) {
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }
}
