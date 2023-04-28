package com.example.elasticsearch.testApi;

import static org.elasticsearch.common.xcontent.XContentFactory.*;
import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.elasticsearch.controller.ArticleController;
import com.example.elasticsearch.pojo.entity.Article;
import com.example.elasticsearch.repository.ArticleRepository;
import com.example.elasticsearch.repository.ElasticSearchHighClientQuery;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.node.Node;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
//@WebMvcTest(ArticleController.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ElasticSearchEvaluation {
//    @TestConfiguration
//    public static class ServiceTestConfiguration {
//        @Bean
//        ArticleRepository bookingService() {
//            return new ArticleRepository() {
//                @Override
//                public Page<Article> findByAuthorsName(String name, Pageable pageable) {
//                    return null;
//                }
//
//                @Override
//                public Optional<Article> queryCustomArticle() {
//                    return Optional.empty();
//                }
//
//                @Override
//                public Page<Article> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable) {
//                    return null;
//                }
//
//                @Override
//                public Page<Article> multiMatchTitleOrAuthorsName(String strSearch, Pageable pageable) {
//                    return null;
//                }
//
//                @Override
//                public Page<Article> wildCardSameLike(String authorName, Pageable pageable) {
//                    return null;
//                }
//
//                @Override
//                public Page<Article> searchSimilar(Article article, String[] strings, Pageable pageable) {
//                    return null;
//                }
//
//                @Override
//                public Iterable<Article> findAll(Sort sort) {
//                    return null;
//                }
//
//                @Override
//                public Page<Article> findAll(Pageable pageable) {
//                    return null;
//                }
//            };
//        }
//    }

    @MockBean
    private ElasticSearchHighClientQuery articleRepository;

    @Autowired
    private MockMvc mockMvc;


    public ElasticSearchEvaluation(){
        System.out.println("xx");
    }
    @Test
    public void testES() throws Exception {
        given(articleRepository.searchBucketThenSourceMapping()).willReturn(null);
    }

}