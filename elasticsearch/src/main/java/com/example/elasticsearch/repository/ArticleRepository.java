package com.example.elasticsearch.repository;

import com.example.elasticsearch.pojo.entity.Article;
//import com.example.elasticsearch.repository.repostoryCustom.ArticleRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

    Page<Article> findByAuthorsName(String name, Pageable pageable);

    @Query("Select a from Article a limit 1") // HSQL not work !!
    Optional<Article> queryCustomArticle();

    @Query("{\"bool\": " +
            "   {\n" +
                    "\"must\": [{\"match\": {\"authorsName\": \"?0\"}}]" +
            "   }\n" +
            "}")
    Page<Article> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable);

    @Query("{\n" +
            "    \"multi_match\": \n" +
            "    {\n" +
            "       \"query\" : \"?0\",\n" +
            "        \"fields\": [\"title\", \"authorsName\"]\n" +
            "       \"fuzziness\": \"AUTO\" \n" +
            "    }" +
            "  }")
    Page<Article> multiMatchTitleOrAuthorsName(String strSearch, Pageable pageable);

    @Query("{\"wildcard\" : " +
            "   {\n" +
            "       \"authorsName\": \"?0*\"\n" +
            "   }\n" +
            "}")
    Page<Article> wildCardSameLike(String authorName, Pageable pageable);
}