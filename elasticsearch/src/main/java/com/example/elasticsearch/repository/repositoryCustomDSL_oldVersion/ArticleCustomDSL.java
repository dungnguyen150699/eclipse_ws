package com.example.elasticsearch.repository.repositoryCustomDSL_oldVersion;

import com.example.elasticsearch.pojo.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;

import java.util.List;

public interface ArticleCustomDSL {
    Article create(Article article);
    List<IndexedObjectInformation> createBulkIndex(Article... articles);
    Page<Article> findAll(Pageable pageable);
    List<Article> findAll();
    List<Article> queryMatchFindArticleByAuthorsName(final String authorsName);
    List<Article> stringQueryGetAllByAuthorName(final String authorsName);
    List<Article> wildcardQueryGetAllByAuthorName(String authorsName);
    List<Article> multiMatchQueryGetAllByAuthorName(String textSearch);

}
