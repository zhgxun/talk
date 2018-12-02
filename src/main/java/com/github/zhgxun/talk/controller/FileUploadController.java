package com.github.zhgxun.talk.controller;

import com.github.zhgxun.talk.common.storage.Storage;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileUploadController {

    private final Storage storage;

    public FileUploadController(Storage storage) {
        this.storage = storage;
    }
}
