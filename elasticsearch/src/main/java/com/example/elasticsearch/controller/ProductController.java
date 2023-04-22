package com.example.elasticsearch.controller;

import com.example.elasticsearch.repository.ElasticSearchHighClientQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ElasticSearchHighClientQuery elasticSearchHighClientQuery;

    @GetMapping("")
    public ResponseEntity getAggregationBucket() throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(elasticSearchHighClientQuery.searchBucketThenSourceMapping());
    }
}
