package com.example.mvcdemo.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table

public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tag_id;

    @NotNull
    @Column(unique = true, nullable = false, length = 255)
    private String name;

    public Tags(int tag_id, String name) {
        this.tag_id = tag_id;
        this.name = name;
    }

    public Tags(String name) {
        this.name = name;
    }
    public Tags() {
    }
   // @ManyToMany(mappedBy = "questions")
    //private Set<Questions> questions;
// 多对多关系：一个标签可以关联多个问题
   @ManyToMany(mappedBy = "tags")
   private Set<Questions> questions;
    public int getTagId() {
        return tag_id;
    }

    public String getName() {
        return name;
    }

    public void setTagId(int tagId) {
        this.tag_id = tag_id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
