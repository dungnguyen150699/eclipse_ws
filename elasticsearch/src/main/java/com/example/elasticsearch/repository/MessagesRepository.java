package com.example.elasticsearch.repository;

import com.example.elasticsearch.pojo.Messages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepository extends ElasticsearchRepository<Messages, String> {
    Page<Messages> getMessagesByMessage(String message, Pageable pageable);
}
