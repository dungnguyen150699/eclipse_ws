package com.example.elasticsearch.repository;

import com.example.elasticsearch.pojo.entity.Product;
import lombok.experimental.PackagePrivate;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.DoubleTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedDoubleTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.elasticsearch.search.aggregations.pipeline.BucketMetricsPipelineAggregationBuilder;
import org.elasticsearch.search.aggregations.pipeline.BucketMetricsPipelineAggregator;
import org.elasticsearch.search.aggregations.pipeline.SumBucketPipelineAggregationBuilder;
import org.elasticsearch.search.aggregations.pipeline.SumBucketPipelineAggregator;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Service
public class ElasticSearchHighClientQuery {

    @Autowired
    private RestHighLevelClient client;
    private final String INDEX_PRODUCT = "products";

    public List<Product> searchBucketThenSourceMapping() throws IOException {
        List<Product> results = new ArrayList();
        SearchRequest searchRequest = createSearchRequestBucketThenSourceMapping();
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        Map<String, Aggregation> aggregationMap = searchResponse.getAggregations().getAsMap();
        Terms nameProductTerms = (Terms) aggregationMap.get("productAggregations");

        List<? extends Terms.Bucket> buckets = nameProductTerms.getBuckets();
        buckets.stream().forEach(dataBucket -> {
            Product product = new Product();
            double totalPrice = ((ParsedSum) dataBucket.getAggregations().get("totalPrice")).getValue();
            String nameAggregation = dataBucket.getKey().toString();

            product.setPrice(totalPrice);
            product.setName(nameAggregation);
            results.add(product);
        });

        System.out.println(results);
        return null;
    }

    private SearchRequest createSearchRequestBucketThenSourceMapping(){
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders
                .terms("productAggregations")
                .field("name.keyword")
                .subAggregation(AggregationBuilders.sum("totalPrice").field("price"));
        // create Bucket : AGG for AGG
        BucketMetricsPipelineAggregationBuilder<SumBucketPipelineAggregationBuilder> sumBucket = new SumBucketPipelineAggregationBuilder("totalPrices", "productAggregations.totalPrice" );

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(boolQueryBuilder).size(0);
        searchSourceBuilder.aggregation(termsAggregationBuilder).aggregation(sumBucket);
        return new SearchRequest().source(searchSourceBuilder).indices(INDEX_PRODUCT).requestCache(true);

    }
}
