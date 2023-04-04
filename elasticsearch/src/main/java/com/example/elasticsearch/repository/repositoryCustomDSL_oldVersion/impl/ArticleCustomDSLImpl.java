package com.example.elasticsearch.repository.repositoryCustomDSL_oldVersion.impl;

import com.example.elasticsearch.pojo.entity.Article;
import com.example.elasticsearch.repository.repositoryCustomDSL_oldVersion.ArticleCustomDSL;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ArticleCustomDSLImpl implements ArticleCustomDSL {
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    public final String ARTICLE_INDEX = "article";
    public final String AUTHORSNAME_FIELD = "authorsName";
    public final String TILTE_FIELD = "tilte";

    /**
     * MatchQuery => (fieldName, data)
     * @param authorsName
     * @return
     */
    @Override
    public List<Article> queryMatchFindArticleByAuthorsName(String authorsName) {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery(AUTHORSNAME_FIELD, authorsName);
        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();
        SearchHits<Article> articleSearchHits =  elasticsearchOperations.search(searchQuery, Article.class, IndexCoordinates.of(ARTICLE_INDEX));
        return articleSearchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
    }

    /**
     * StringQuery
     * @param authorsName
     * @return
     */
    @Override
    public List<Article> stringQueryGetAllByAuthorName(String authorsName) {
        Query searchQuery = new StringQuery(
                "{\"match\":{\"" + AUTHORSNAME_FIELD + "\":{\"query\":\""+ authorsName + "\"}}}\"");

        SearchHits<Article> articleSearchHits = elasticsearchOperations.search(
                searchQuery,
                Article.class,
                IndexCoordinates.of(ARTICLE_INDEX));
        return articleSearchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
    }

    /**
     * WildCardQuery
     * @param authorsName
     * @return
     */
    @Override
    public List<Article> wildcardQueryGetAllByAuthorName(String authorsName) {
        WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery(AUTHORSNAME_FIELD,authorsName + "*");
        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(wildcardQueryBuilder)
                .build();
        SearchHits<Article> articleSearchHits =  elasticsearchOperations.search(searchQuery, Article.class, IndexCoordinates.of(ARTICLE_INDEX));
        return articleSearchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
    }

    /**
     *
     * @param searchText
     * @return
     */
    @Override
    public List<Article> multiMatchQueryGetAllByAuthorName(String searchText) {
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(searchText,AUTHORSNAME_FIELD,TILTE_FIELD).fuzziness("AUTO");
        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQueryBuilder)
                .build();
        SearchHits<Article> articleSearchHits =  elasticsearchOperations.search(searchQuery, Article.class, IndexCoordinates.of(ARTICLE_INDEX));
        return articleSearchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
    }
}
