package com.github.zhgxun.talk.controller;

import com.github.zhgxun.talk.common.exception.NormalException;
import com.github.zhgxun.talk.common.util.ResponseUtil;
import com.github.zhgxun.talk.config.Code;
import com.github.zhgxun.talk.entity.BookEntity;
import com.github.zhgxun.talk.manager.BookManager;
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

@Api(tags = {"图书管理"})
@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Autowired
    private BookManager bookManager;

    @ApiOperation(value = "添加图书", notes = "图书名称需保持唯一, 会使用来作为去重策略")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "图书标题", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "author", value = "图书作者", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "nickName", value = "整理作者昵称, 书本可为第三方平台抓取, 或者自行通过AI翻译", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "url", value = "图书链接或者有声读物原始链接", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "description", value = "图书描述, 可为抓取或者自行补充的内容", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "playCount", value = "图书播放次数, 无播放时为0", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(path = "add", method = RequestMethod.POST)
    public ResponseUtil<BookEntity> add(@RequestParam(name = "title") @NotNull(message = "参数为空") String title,
                                        @RequestParam(name = "author") @NotNull(message = "参数为空") String author,
                                        @RequestParam(name = "nickName") @NotNull(message = "参数为空") String nickName,
                                        @RequestParam(name = "url") @NotNull(message = "参数为空") String url,
                                        @RequestParam(name = "description") @NotNull(message = "参数为空") String description,
                                        @RequestParam(name = "playCount") @NotNull(message = "参数为空") int playCount) {
        try {
            return new ResponseUtil<>(bookManager.add(title.trim(), author.trim(), nickName.trim(), url.trim(), description.trim(), playCount));
        } catch (Exception e) {
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }

    @ApiOperation(value = "图书详情", notes = "图书详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "图书标识", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "title", value = "图书标题", paramType = "query", dataType = "String")
    })
    @RequestMapping(path = "/one", method = RequestMethod.GET)
    public ResponseUtil<BookEntity> one(@RequestParam(name = "id", required = false) int id,
                                        @RequestParam(name = "title", required = false) String title) {
        try {
            return new ResponseUtil<>(bookManager.findOne(id, title));
        } catch (Exception e) {
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }

    @ApiOperation(value = "图书列表", notes = "根据条件筛选图书列表, 无条件时为全部图书列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "图书标题", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "author", value = "图书作者", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "nickName", value = "整理作者昵称", paramType = "query", dataType = "String")
    })
    @RequestMapping(path = "/any", method = RequestMethod.GET)
    public ResponseUtil<List<BookEntity>> any(@RequestParam(name = "title", required = false) String title,
                                              @RequestParam(name = "author", required = false) String author,
                                              @RequestParam(name = "nickName", required = false) String nickName) {
        try {
            return new ResponseUtil<>(bookManager.any(title, author, nickName));
        } catch (Exception e) {
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }

    @ApiOperation(value = "图书更新", notes = "主要用于递增图书播放次数, 参数至少提供一个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "图书地址", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "description", value = "图书描述", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "playCount", value = "播放次数", paramType = "query", dataType = "int")
    })
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public ResponseUtil<Integer> update(@RequestParam(name = "id") @NotNull(message = "参数为空") int id,
                                        @RequestParam(name = "url", required = false) String url,
                                        @RequestParam(name = "description", required = false) String description,
                                        @RequestParam(name = "playCount", required = false) int playCount) {
        try {
            int length = 0;
            if (url == null && description == null && playCount < 0) {
                throw new NormalException("参数错误");
            }
            if (url != null) {
                length += url.trim().length();
            }
            if (description != null) {
                length += description.trim().length();
            }
            if (length + playCount < 0) {
                throw new NormalException("参数缺失");
            }
            return new ResponseUtil<>(bookManager.update(id, url, description, playCount));
        } catch (Exception e) {
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }

    @ApiOperation(value = "图书删除", notes = "删除图书及其关联的章节")
    @ApiImplicitParam(name = "id", value = "图书标识", required = true, paramType = "query", dataType = "int")
    @RequestMapping(path = "delete", method = RequestMethod.POST)
    public ResponseUtil<Integer> delete(@RequestParam(name = "id") @NotNull(message = "参数为空") int id) {
        try {
            return new ResponseUtil<>(bookManager.delete(id));
        } catch (Exception e) {
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }
}
