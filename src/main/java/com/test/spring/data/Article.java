package com.test.spring.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;


//make a soft delete article class
@Entity
@Table(name = "articles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE articles SET deleted=true WHERE id=?")
@FilterDef(name = "deletedArticleFilter", parameters = @ParamDef(name = "isDeleted", type = boolean.class))
@Filter(name = "deletedArticleFilter", condition = "deleted = :isDeleted")
public class Article {
    @Id
    @GeneratedValue
    private Integer id;

    private String title;
    private String description;
    private String author;

    private boolean deleted = Boolean.FALSE;
}
