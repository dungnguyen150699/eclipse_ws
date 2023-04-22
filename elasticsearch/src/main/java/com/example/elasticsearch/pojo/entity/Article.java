package com.example.elasticsearch.pojo.entity;

import com.example.elasticsearch.ultis.DateUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

import static com.example.elasticsearch.ultis._enum.DatePatternEnum.DD_MM_YYYY_HHMMSS;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Document(indexName = "article")
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id = DateUtils.dateFormatToString(new Date(),DD_MM_YYYY_HHMMSS);

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
