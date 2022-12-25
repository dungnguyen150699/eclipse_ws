package com.java8.springboot.spring.ReadFileInResource;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetInpuStreamFromResource {
    public static InputStream getInputStreamFromResource(String uri) {
        Resource resource = new ClassPathResource(uri);
        try {
            InputStream is = resource.getInputStream();
            return is;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static boolean isExcelFile(String nameOrPath) {
        if (nameOrPath == null || nameOrPath.isEmpty()) return false;
//        return nameOrPath.endsWith(Constants.FILE_EXTENSION.HSSF) || nameOrPath.endsWith(Constants.FILE_EXTENSION.XSSF);
        return nameOrPath.endsWith(".xls") || nameOrPath.endsWith(".xlsx");
    }
    
    // Done! 
}
