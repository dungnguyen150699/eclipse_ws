package com.example.elasticsearch;

import com.example.elasticsearch.pojo.entity.Article;
import com.example.elasticsearch.repository.ArticleRepository;
import com.example.elasticsearch.repository.repositoryCustomDSL_oldVersion.ArticleCustomDSL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@SpringBootApplication
public class ElasticsearchApplication implements CommandLineRunner {
//	@Autowired
//	private ArticleRepository articleRepository;
	@Autowired
	private ArticleCustomDSL articleCustomDSL;

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		int idIncrement = 0;
		List<Article> datas = null;
		try{

			Article articleCreate = new Article()
	//				.setId(++idIncrement)
					.setAuthorsName("spring auto set ID if id field is String ")
					.setContent("No Content")
					.setTitle("My First Article");

	//		articleRepository.save(articleCreate);
			articleCustomDSL.createBulkIndex(articleCreate);
			// Update entity
			articleCreate = new Article()
	//				.setId(++idIncrement)
					.setAuthorsName("NTD")
					.setContent("No Content")
					.setTitle("My Second Article");
	//		articleRepository.save(articleCreate);
			articleCustomDSL.createBulkIndex(articleCreate);
			datas = StreamSupport.stream(articleCustomDSL.findAll(Pageable.unpaged()).spliterator(),true).collect(Collectors.toList());
			System.out.println(datas);
		}catch (Exception exception){
			log.error(exception.getCause().toString(),exception);

		}
	}
}
