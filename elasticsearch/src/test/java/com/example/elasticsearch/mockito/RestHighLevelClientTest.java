package com.example.elasticsearch.mockito;

import com.example.elasticsearch.ultis.ElasticConfig;
import com.example.elasticsearch.ultis.ElasticsearchUtil;
import org.apache.lucene.search.TotalHits;
import org.assertj.core.util.Arrays;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WrapperQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.After;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestHighLevelClientTest {

    @InjectMocks
    private ElasticsearchUtil elasticsearchUtil;

    @Mock
    private RestHighLevelClient client;

    private final String fileProperties = "etc/elastic.properties";

    public RestHighLevelClientTest(){
        this.elasticsearchUtil = new ElasticsearchUtil();
    }

    @After
    public void setUpClient(){
        this.client = mock(RestHighLevelClient.class);
    }


    @Test
    @SuppressWarnings("*")
    public void testRestHighLevelClientConnection() throws IOException {
        ElasticConfig.loadDbConfig(fileProperties);
        ElasticsearchUtil elasticsearchUtil = new ElasticsearchUtil();
        RestHighLevelClient client = elasticsearchUtil.getClient();
        final ThrowingRunnable throwingRunnable;
        assertNotEquals(String.format("ElasticSearch Not Connected! URl: %s",ElasticConfig.conn),null,client);
        MatchAllQueryBuilder matchQueryBuilder = QueryBuilders.matchAllQuery();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(matchQueryBuilder).size(10);
        SearchRequest searchRequest = new SearchRequest().source(searchSourceBuilder);
        throwingRunnable = () -> client.search(searchRequest, RequestOptions.DEFAULT);
        try{
            throwingRunnable.run();
        }catch (Throwable throwable){
            if(throwable instanceof ConnectException){
                AssertionError assertionError = new AssertionError(String.format("ElasticSearch Not Connected! URl: %s",ElasticConfig.conn));
                throw assertionError;
            }
        }
    }

    @Test
    public void testEmptySearch() throws IOException {
        final String index="indextest", col="col", keyword="keyword", from=null, to=null;
        final Integer pageNum=1, pageSize=10, isExpression=0;
        final String queryTest = "{\"bool\": {\n" +
                "      \"must\": [\n" +
                "        {\n" +
                "          \"match\": {\n" +
                "            \"col\": \"keyword\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"range\": {\n" +
                "            \"EVENT_TIME_STAMP\": {\n" +
                "              \"gte\": \"now-1y\",\n" +
                "              \"lte\": \"now\"\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    }}";
        WrapperQueryBuilder qb = QueryBuilders.wrapperQuery(queryTest);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        SearchRequest searchRequest;

        sourceBuilder.query(qb);
        sourceBuilder.size(pageSize);
        sourceBuilder.from(pageNum - 1);
        sourceBuilder.sort("EVENT_TIME_STAMP", SortOrder.ASC);
        // init searchRequest;
        searchRequest = new SearchRequest();
        searchRequest.indices(index);
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = initTestEmptySearch();
        SearchResponse searchResponseActual;

        when(client.search(searchRequest, RequestOptions.DEFAULT)).thenReturn(searchResponse);
        searchResponseActual = elasticsearchUtil.search(index,col,keyword,from,to,pageNum,pageSize,isExpression);

        assertEquals(searchResponse,searchResponseActual);

    }

    @Test
    public void testSearchIncludeInformation() throws IOException {
        final String index="indextest", col="col", keyword="keyword", from="2023/05/04", to="2023/05/07";
        final Integer pageNum=1, pageSize=10, isExpression=1;
        final String queryTest = "{\"bool\": {\n" +
                "      \"must\": [\n" +
                "        {\n" +
                "          \"regexp\": {\n" +
                "            \"col\": \"keyword\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"range\": {\n" +
                "            \"EVENT_TIME_STAMP\": {\n" +
                "              \"gte\": \"2023/05/04\",\n" +
                "              \"lte\": \"2023/05/07\"\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    }}";
        WrapperQueryBuilder qb = QueryBuilders.wrapperQuery(queryTest);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        SearchRequest searchRequest;
        ElasticConfig.loadDbConfig(fileProperties);

        sourceBuilder.query(qb);
        sourceBuilder.size(pageSize);
        sourceBuilder.from(pageNum - 1);
        sourceBuilder.sort("EVENT_TIME_STAMP", SortOrder.ASC);
        // init searchRequest;
        searchRequest = new SearchRequest();
        searchRequest.indices(index);
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = initTestIncludeInformationSearch();
        SearchResponse searchResponseActual;
        when(client.search(searchRequest, RequestOptions.DEFAULT)).thenReturn(searchResponse);
        searchResponseActual = elasticsearchUtil.search(index,col,keyword,from,to,pageNum,pageSize,isExpression);

        assertEquals(searchResponse,searchResponseActual);
    }

    public SearchResponse initTestEmptySearch() throws IOException {
        SearchHits hits = new SearchHits(new SearchHit[]{},new TotalHits(0, TotalHits.Relation.EQUAL_TO),0);
        SearchResponseSections searchResponseSections = new SearchResponseSections( hits,null,
                null,
                false,
                null,
                null,
                0 );
        SearchResponse searchResponse = new SearchResponse( searchResponseSections, null, 1,
                1, 0, 0, new ShardSearchFailure[] {} , SearchResponse.Clusters.EMPTY
                );

        return searchResponse;
    }

    public SearchResponse initTestIncludeInformationSearch() throws IOException {
        Map<String, DocumentField> map = new HashMap();
        map.put("item1",new DocumentField("col", Arrays.asList(new String[]{"valueTest"})));
        SearchHits hits = new SearchHits(new SearchHit[]{new SearchHit(1,"150699", new Text("text"),map,null)},new TotalHits(1, TotalHits.Relation.EQUAL_TO),0);
        SearchResponseSections searchResponseSections = new SearchResponseSections( hits,null,
                null,
                false,
                null,
                null,
                0 );
        SearchResponse searchResponse = new SearchResponse( searchResponseSections, null, 1,

                1, 0, 0, new ShardSearchFailure[] {} , SearchResponse.Clusters.EMPTY
                );
        return searchResponse;
    }
}
