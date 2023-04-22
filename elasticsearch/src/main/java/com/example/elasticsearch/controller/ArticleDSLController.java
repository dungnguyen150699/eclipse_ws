package com.example.elasticsearch.controller;

import com.example.elasticsearch.repository.repositoryCustomDSL_oldVersion.ArticleCustomDSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("article-dsl")
public class ArticleDSLController {
    @Autowired
    private ArticleCustomDSL articleCustomDSL;

    @GetMapping("")
    ResponseEntity matchQueryGetAllByAuthorName(@RequestParam(name="authorName") String authorName){
        return ResponseEntity.status(HttpStatus.OK).body(articleCustomDSL.queryMatchFindArticleByAuthorsName(authorName));
    }

    @GetMapping("/string-query")
    ResponseEntity stringQueryGetAllByAuthorName(@RequestParam(name="authorName") String authorName){
        return ResponseEntity.status(HttpStatus.OK).body(articleCustomDSL.stringQueryGetAllByAuthorName(authorName));
    }

    @GetMapping("/wildCard-query")
    ResponseEntity wildCardQueryGetAllByAuthorName(@RequestParam(name="authorName") String authorName){
        return ResponseEntity.status(HttpStatus.OK).body(articleCustomDSL.wildcardQueryGetAllByAuthorName(authorName));
    }
    @GetMapping("/multiMatch-query")
    ResponseEntity multiMatchQueryGetAllByAuthorName(@RequestParam(name="text") String text){
        return ResponseEntity.status(HttpStatus.OK).body(articleCustomDSL.multiMatchQueryGetAllByAuthorName(text));
    }
}
