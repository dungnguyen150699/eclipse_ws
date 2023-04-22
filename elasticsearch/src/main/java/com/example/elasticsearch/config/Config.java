package com.example.elasticsearch.config;


import com.google.gson.Gson;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.http.HttpHeaders;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.elasticsearch.repository")
@ComponentScan(basePackages = { "com.example.elasticsearch.pojo" })
public class Config extends AbstractElasticsearchConfiguration {

    @Bean
    public Gson gsonBean() {
        return new Gson();
    }

//    @Bean
//    @Override
//    public RestHighLevelClient elasticsearchClient() {
//        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo("localhost:9200")
//                .build();
//
//        return RestClients.create(clientConfiguration)
//                .rest();
//    }

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("localhost",9200));
        restClientBuilder.setDefaultHeaders(compatibilityHeaders()); // accept Header from apache Header ??

        return new RestHighLevelClient(restClientBuilder);
    }

    // Header from package org.apache.http; ??
    private Header[] compatibilityHeaders() {
        return new Header[]{
//                new BasicHeader(HttpHeaders.ACCEPT, "application/vnd.elasticsearch+json;compatible-with=7"),
//                new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.elasticsearch+json;compatible-with=7")
                new BasicHeader("Content-type", "application/json")
        };
    }
}
