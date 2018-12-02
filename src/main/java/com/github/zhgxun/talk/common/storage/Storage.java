package com.github.zhgxun.talk.common.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface Storage {

    void init();

    void store(MultipartFile file);

    Path load(String filename);

    Resource loadAsResource(String filename);
}
