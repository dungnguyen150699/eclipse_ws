/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elasticsearch.ultis;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WrapperQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

/**
 * @author ocs-os30
 */
public class ElasticsearchUtil {
    private RestHighLevelClient client;

    private final int DEFAULT_PAGE_SIZE = 10;
    private final int DEFAULT_PAGE = 1;
    private final String MATCH_QUERY_TYPE = "match";
    private final String REGEXP_QUERY_TYPE = "regexp";

    public SearchResponse search(String index, String col, String keyword,
                                 String from, String to, Integer page, Integer size,
                                 int isExpression) throws IOException {

        page = page == null ? DEFAULT_PAGE : page;
        size = size == null ? DEFAULT_PAGE_SIZE : size;

        String queryType = isExpression == 0 ? MATCH_QUERY_TYPE : REGEXP_QUERY_TYPE;
        String gte = from == null ? "now-1y" : from;
        String lte = to == null ? "now" : to;


        String query = String.format("{\"bool\": {\n" +
                "      \"must\": [\n" +
                "        {\n" +
                "          \"%s\": {\n" +
                "            \"%s\": \"%s\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"range\": {\n" +
                "            \"EVENT_TIME_STAMP\": {\n" +
                "              \"gte\": \"%s\",\n" +
                "              \"lte\": \"%s\"\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    }}", queryType, col, keyword, gte, lte);

         System.out.println("Query" + query);
//      create wrapper query accept base64 encode query
        WrapperQueryBuilder qb = QueryBuilders.wrapperQuery(query);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(qb);
        sourceBuilder.size(size);
        sourceBuilder.from(page - 1);
        sourceBuilder.sort("EVENT_TIME_STAMP", SortOrder.ASC);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = this.getClient().search(searchRequest, RequestOptions.DEFAULT);
//        SearchHits hits = searchResponse.getHits();
//        SearchHit[] searchHits = hits.getHits();
//        ObjectMapper objectMapper = new ObjectMapper();
//
////        List<Cdr> list = new ArrayList();
//        for (SearchHit hit : searchHits) {
//            String jsonString=hit.getSourceAsString();
////            TempClass tmpCl=(TempClass )JSONToObject.parser(jsonString, TempClass .class);
////            Cdr cdr = (Cdr) objectMapper.readValue(jsonString, clazz);
////            list.add(cdr);
//        }
////        return list;

        return searchResponse;
    }

    public RestHighLevelClient getClient() {

        if (null != client) return client;

        HttpHost[] hosts = new HttpHost[6];
        String[] token = ElasticConfig.conn.split("\\,");
        int i, j;

        for (i = 0; token != null && (i < token.length && i < hosts.length); i++) {
            String[] s = token[i].split("\\:");
            int port = Integer.parseInt(s[1]);
            hosts[i] = new HttpHost(s[0], port, "http");
//            hosts[i] = new HttpHost(s[0], port, "https");
        }

        for (j = i; j < hosts.length; j++) {
            hosts[j] = hosts[j - i];
        }


        i = 0;
        RestClientBuilder restClientBuilder = RestClient.builder(
                hosts[i++],
                hosts[i++],
                hosts[i++],
                hosts[i++],
                hosts[i++],
                hosts[i++]
        );
        if (ElasticConfig.enableSecurity) {
            UsernamePasswordCredentials credentials =
                    new UsernamePasswordCredentials(ElasticConfig.eUsername, ElasticConfig.ePassword);

            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, credentials);

//            restClientBuilder.setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        }

        client = new RestHighLevelClient(restClientBuilder);

        return client;
    }

}
