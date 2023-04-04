package com.example.elasticsearch.repository.repositoryCustomDSL_oldVersion;

import com.example.elasticsearch.pojo.entity.Article;

import java.util.List;

public interface ArticleCustomDSL {
    List<Article> queryMatchFindArticleByAuthorsName(final String authorsName);
    List<Article> stringQueryGetAllByAuthorName(final String authorsName);
    List<Article> wildcardQueryGetAllByAuthorName(String authorsName);
    List<Article> multiMatchQueryGetAllByAuthorName(String textSearch);

}
