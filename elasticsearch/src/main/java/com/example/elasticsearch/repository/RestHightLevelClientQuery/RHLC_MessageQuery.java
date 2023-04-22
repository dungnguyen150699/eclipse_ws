package com.example.elasticsearch.repository.RestHightLevelClientQuery;

import com.example.elasticsearch.pojo.Messages;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RHLC_MessageQuery{

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public List<Messages> commonQuery(){
        return null;
    }
}
