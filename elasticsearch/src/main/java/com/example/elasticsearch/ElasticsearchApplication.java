package com.example.elasticsearch;

import com.example.elasticsearch.pojo.entity.Article;
import com.example.elasticsearch.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.StreamSupport;

@SpringBootApplication
public class ElasticsearchApplication implements CommandLineRunner {
	@Autowired
	private ArticleRepository articleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		int idIncrement = 0;
//		List<Article> datas ;
//		Article articleCreate = new Article()
//				.setId(++idIncrement)
//				.setAuthorsName("NTD")
//				.setContent("No Content")
//				.setTitle("My First Article");
//
////		articleRepository.save(articleCreate);
//		articleRepository.index(articleCreate);
//		// Update entity
//		articleCreate = new Article()
//				.setId(++idIncrement)
//				.setAuthorsName("NTD")
//				.setContent("No Content")
//				.setTitle("My Second Article");
////		articleRepository.save(articleCreate);
//		articleRepository.index(articleCreate);
//		datas = StreamSupport.stream(articleRepository.findAll(Pageable.unpaged()).spliterator(),true).toList();
//		System.out.println(datas);
	}
}
