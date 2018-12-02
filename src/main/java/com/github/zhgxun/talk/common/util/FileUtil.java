package com.github.zhgxun.talk.common.util;

import java.io.File;
import java.io.IOException;

/**
 * 文件处理工具
 */
public class FileUtil {

    /**
     * 创建文件, 如果文件不存在
     *
     * @param fileName 文件名
     * @return 处理结果
     * @throws IOException 创建异常
     */
    public static boolean createFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            String path = file.getParent();
            File dir = new File(path);
            if (!dir.isDirectory() && dir.mkdirs()) {
                return file.createNewFile();
            }
        }
        return true;
    }

    /**
     * 删除目录和文件
     *
     * @param path 待删除的目录和文件
     * @return 删除结果
     */
    public static boolean deleteFolder(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        return file.isFile() ? deleteFile(path) : deleteDirectory(path);
    }

    public static boolean deleteFile(String path) {
        File file = new File(path);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static boolean deleteDirectory(String path) {
        if (!path.endsWith(File.separator)) {
            path = path + File.separator;
        }
        File dirFile = new File(path);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        File[] files = dirFile.listFiles();
        if (files == null || files.length <= 0) {
            return true;
        }
        for (File file : files) {
            if (file.isFile()) {
                deleteFile(file.getAbsolutePath());
            } else {
                deleteDirectory(file.getAbsolutePath());
            }
        }

        return dirFile.delete();
    }
}
