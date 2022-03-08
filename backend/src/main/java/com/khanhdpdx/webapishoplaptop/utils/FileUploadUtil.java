package com.khanhdpdx.webapishoplaptop.utils;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUploadUtil {
    public static void uploadFiles(List<MultipartFile> files, StringBuffer pathName) throws IOException {
        StringBuffer fileName;
        Path path = Paths.get(pathName.toString());
        if(!Files.exists(path)) {
            Files.createDirectory(path);
        }
        for(MultipartFile file : files) {
            fileName = new StringBuffer(pathName + file.getOriginalFilename());
            FileCopyUtils.copy(file.getBytes(), new File(fileName.toString()));
        }
    }
}
