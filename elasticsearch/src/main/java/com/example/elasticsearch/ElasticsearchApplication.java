package com.example.elasticsearch;

import com.example.elasticsearch.pojo.Product;
import com.example.elasticsearch.repository.ProductRepository;
import com.google.gson.Gson;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.util.Date;

@SpringBootApplication
//@ComponentScan(excludeFilters =
//	@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ElasticsearchOperations.class)
//)
public class ElasticsearchApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private RestHighLevelClient restHighLevelClient;
	@Autowired
	private Gson gson;

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product product = new Product()
				.setName("Name Product1")
				.setDescription("Description1")
				.setPrice(150199d)
				.setDate(new Date());
//		IndexRequest indexRequest = new IndexRequest("products");
//		BulkRequest bulkRequest = new BulkRequest();

//		indexRequest.source(gson.toJson(product), XContentType.JSON);
//		bulkRequest.add(indexRequest);
//		restHighLevelClient.bulk(bulkRequest,RequestOptions.DEFAULT);
		productRepository.save(product);
	}
}
