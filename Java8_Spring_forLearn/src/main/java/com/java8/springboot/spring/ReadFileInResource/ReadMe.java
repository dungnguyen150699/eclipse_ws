package com.java8.springboot.spring.ReadFileInResource;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ReadMe {
/*
 * Helper read File in Resource
 * You cant read file with relative path only absolute path But
 * Proplem have reslove by ClassPathResource -> resource in Spring
 * 
 *      Resource resource = new ClassPathResource(uri);
        try {
            InputStream is = resource.getInputStream();
            return is;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
 * 
 */
}
