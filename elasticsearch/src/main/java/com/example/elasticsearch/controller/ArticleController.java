package com.example.elasticsearch.controller;

import com.example.elasticsearch.pojo.entity.Article;
import com.example.elasticsearch.pojo.request.article.ArticleCreateRequest;
import com.example.elasticsearch.pojo.request.article.ArticleSearchRequest;
import com.example.elasticsearch.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
//    @Autowired
//    private ArticleRepositoryCustom articleRepositoryCustom;

    @GetMapping("")
    public ResponseEntity getAllArticle(){
        Stream<Article> datas ;
        datas = StreamSupport.stream(articleRepository.findAll(Pageable.unpaged()).spliterator(),true);
        return ResponseEntity.status(HttpStatus.OK).body(datas);
    }

    @GetMapping("/multiSearch-titleOrAuthorName")
    public ResponseEntity multiSearcjTitleOrAuthorName(@RequestParam(value = "str") String str, Pageable pageable){
        Page<Article> page;
        page = articleRepository.multiMatchTitleOrAuthorsName(str,pageable);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/wildcard")
    public ResponseEntity wildCard(@RequestParam(value = "name") String name, Pageable pageable){
        Page<Article> page;
        page = articleRepository.wildCardSameLike(name,pageable);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @PostMapping("/getAllByCondition")
    public ResponseEntity getAllByCondition(@RequestBody ArticleSearchRequest request, Pageable pageable){
        String authorsName = request.getAuthorsName();
        Page<Article> page =  articleRepository.findByAuthorsNameUsingCustomQuery(authorsName,pageable);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @PutMapping("/createArticle")
    public ResponseEntity createArticle(@RequestBody ArticleCreateRequest request){
//        Article createArticle;
//        if(request.getId() != null){
//            Article articleExist = articleRepository.findById(request.getId()).orElse(null);
//            if(articleExist == null){
//                createArticle = new Article()
//                        .setId(request.getId())
//                        .setTitle(request.getTitle())
//                        .setContent(request.getContent())
//                        .setAuthorsName(request.getAuthorsName());
//                articleRepository.save(createArticle);
//            }else throw new RuntimeException();
//        }
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping("/createArticle-by-elasticSearchOperation")
    public ResponseEntity createArticleByElasticSearchOperation(@RequestBody ArticleCreateRequest request){
        Article createArticle;
//        List<IndexedObjectInformation> reponse = null;
        String reponse = null;
//        if(request.getId() != null){
//            Article articleExist = articleRepository.findById(request.getId()).orElse(null);
//            if(articleExist == null){
//                createArticle = new Article()
//                        .setId(request.getId())
//                        .setTitle(request.getTitle())
//                        .setContent(request.getContent())
//                        .setAuthorsName(request.getAuthorsName());
////                reponse = articleRepositoryCustom.createArticleIndexBulk(Arrays.asList(createArticle));
//                reponse = articleRepositoryCustom.createIndex(createArticle);
//            }else throw new RuntimeException();
//        }
        return ResponseEntity.status(HttpStatus.CREATED).body(reponse);
    }
}
