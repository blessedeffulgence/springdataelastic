package com.springdata.elastic.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.List;

import static org.springframework.data.elasticsearch.annotations.FieldType.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "blog" ,indexStoreType = "article")
public class Article {

    @Id
    private String id;

    @MultiField(mainField = @Field(type = Text, fielddata = true), otherFields = { @InnerField(suffix = "verbatim", type = Keyword) })
    private String title;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Author> authors;

    public Article(String id,String title) {
        this.id = id;
        this.title = title;
    }
}
