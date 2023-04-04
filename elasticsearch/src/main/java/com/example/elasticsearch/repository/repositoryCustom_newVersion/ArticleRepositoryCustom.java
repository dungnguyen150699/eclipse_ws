//package com.example.elasticsearch.repository.repostoryCustom;
//
//import com.example.elasticsearch.pojo.entity.Article;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
//
//import java.util.List;
//
//// version 3.0.5
//public interface ArticleRepositoryCustom {
//    Page<Article> findByAuthorsName(String name, Pageable pageable);
//
//    List<IndexedObjectInformation> createArticleIndexBulk (List<Article> articles);
//
//    String createIndex(Article articles);
//}
