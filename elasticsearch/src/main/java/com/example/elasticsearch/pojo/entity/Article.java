package com.example.elasticsearch.pojo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.StringJoiner;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Document(indexName = "article")
public class Article {
    @Id
    private long id;

    @Field(type = FieldType.Text, name = "title")
    private String title;

    @Field(type = FieldType.Text, name = "content")
    private String content;

    @Field(type = FieldType.Text, name = "authorsName")
    private String authorsName;

    @Override
    public String toString(){
        StringJoiner stringJoiner = new StringJoiner(",");
        return stringJoiner
                .add(this.getId() + "")
                .add(this.getTitle())
                .add(this.getAuthorsName())
                .add(this.getContent())
                .toString();
    }
}
