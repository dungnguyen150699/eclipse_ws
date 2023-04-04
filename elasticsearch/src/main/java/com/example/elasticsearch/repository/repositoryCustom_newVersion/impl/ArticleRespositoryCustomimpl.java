//package com.example.elasticsearch.repository.repostoryCustom.impl;
//
//import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
//import com.example.elasticsearch.pojo.entity.Article;
//import com.example.elasticsearch.pojo.entity.Product;
//import com.example.elasticsearch.repository.repostoryCustom.ArticleRepositoryCustom;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.client.elc.QueryBuilders;
//import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQueryBuilder;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
//import org.springframework.data.elasticsearch.core.SearchHits;
//import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
//import org.springframework.data.elasticsearch.core.query.IndexQuery;
//import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
//import org.springframework.data.elasticsearch.core.query.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//// version 3.0.5 =))
//@Repository
//public class ArticleRespositoryCustomimpl implements ArticleRepositoryCustom {
//    @Autowired
//    protected ElasticsearchOperations elasticsearchOperations;
//
//    private static final String ARTICLE_INDEX = "article";
//
//    // Cái này lỗi lắm nha ko nên dùng cứ update version là lại 1 cách khác
//    @Override
//    public Page<Article> findByAuthorsName(String name, Pageable pageable) {
////        QueryBuilder queryBuilder =  QueryBuilders.matchQuery("authorsName", name, Operator.And,1f);
////
////        Query searchQuery = new NativeSearchQueryBuilder()
////                .withQuery(queryBuilder)
////                .build();
////
////        SearchHits<Product> productHits =
////                elasticsearchOperations
////                        .search(searchQuery,
////                                Product.class,
////                                IndexCoordinates.of(ARTICLE_INDEX));
//        return null;
//    }
//
//    public List<IndexedObjectInformation> createArticleIndexBulk( List<Article> articles) {
//
//        List<IndexQuery> queries = articles.stream()
//                .map(article->
//                        new IndexQueryBuilder()
//                                .withId(article.getId() + "")
//                                .withObject(article).build())
//                .collect(Collectors.toList());;
//
//        return elasticsearchOperations
//                .bulkIndex(queries, IndexCoordinates.of(ARTICLE_INDEX));
//    }
//
//    @Override
//    public String createIndex(Article articles) {
//        IndexQuery indexQuery = new IndexQueryBuilder()
//                .withId(articles.getId() + "")
//                .withObject(articles).build();
//        String documentId = elasticsearchOperations
//                .index(indexQuery, IndexCoordinates.of(ARTICLE_INDEX));
//        return documentId;
//    }
//}
