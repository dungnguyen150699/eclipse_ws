package com.example.elasticsearch.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@AllArgsConstructor
@Document(indexName = "messages")
public class Messages {

    @Id
    private String id;

    @Field(type = FieldType.Text, name = "message")
    private String message;

}
