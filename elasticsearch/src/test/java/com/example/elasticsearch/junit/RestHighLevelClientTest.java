package com.example.elasticsearch.junit;

import com.example.elasticsearch.ultis.ElasticConfig;
import com.example.elasticsearch.ultis.ElasticsearchUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import java.io.IOException;

import static org.junit.Assert.assertNotEquals;

public class RestHighLevelClientTest {

    private ElasticsearchUtil elasticsearchUtil;

    public RestHighLevelClientTest(){
        this.elasticsearchUtil = new ElasticsearchUtil();
    }

    @Test
    @SuppressWarnings("*")
    public void testRestHighLevelClientConnection() throws IOException {
        ElasticConfig.loadDbConfig("etc/elastic.properties");
        RestHighLevelClient client = elasticsearchUtil.getClient();
        assertNotEquals(String.format("ElasticSearch Not Connected! Value Get Client: %s",client.toString()),
                null,elasticsearchUtil.getClient());
        MatchAllQueryBuilder matchQueryBuilder = QueryBuilders.matchAllQuery();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(matchQueryBuilder).size(10);
        SearchRequest searchRequest = new SearchRequest().source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//        final ThrowingRunnable throwingRunnable = () -> client.search(searchRequest, RequestOptions.DEFAULT);
//        Assert.assertThrows("E",RuntimeException.class,throwingRunnable);
    }
}
