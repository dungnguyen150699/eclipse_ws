package com.example.elasticsearch.pojo.request.article;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.StringJoiner;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ArticleCreateRequest {
    private Long id;

    private String title;

    private String content;

    private String authorsName;

    @Override
    public String toString(){
        StringJoiner stringJoiner = new StringJoiner("\n","[","]");

        return stringJoiner
                .add(this.getId() + "")
                .add(this.getTitle())
                .add(this.getAuthorsName())
                .add(this.getContent())
                .toString();
    }
}
