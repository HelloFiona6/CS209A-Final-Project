package com.example.mvcdemo.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

// Question.java
@Entity
@Table
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int question_id;
    private String title;
    @Column(name = "body", columnDefinition = "TEXT")
    private String body;
    @Column( nullable = true)
    private Integer owner_user_id;
    private int comment_count;//有用到计算参与度
    private boolean is_answered;
    private int view_count;
    private int favorite_count;
    private int down_vote_count;//有用到计算参与度
    private int up_vote_count;//有用到计算参与度
    private int answer_count;//有用到计算参与度
    private int score;
    private int last_activity_date;
    private int creation_date;
    private String link;

    //@ManyToMany(mappedBy = "tags")
    //private Set<Tags> tags;
// 多对多关系：一个问题可以有多个标签
    @ManyToMany
    @JoinTable(
            name = "question_tags",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tags> tags;

    public int getQuestion_id() {
        return question_id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public int getOwner_user_id() {return owner_user_id;}

    public int getComment_count() {
        return comment_count;
    }

    public boolean isIs_answered() {
        return is_answered;
    }

    public int getView_count() {
        return view_count;
    }

    public int getFavorite_count() {
        return favorite_count;
    }

    public int getDown_vote_count() {
        return down_vote_count;
    }

    public int getUp_vote_count() {
        return up_vote_count;
    }

    public int getAnswer_count() {
        return answer_count;
    }

    public int getScore() {
        return score;
    }

    public int getLast_activity_date() {
        return last_activity_date;
    }

    public int getCreation_date() {
        return creation_date;
    }

    public String getLink() {
        return link;
    }

    public Set<Tags> getTags() {return tags;}

    public void setLink(String link) {
        this.link = link;
    }

    public void setCreation_date(int creation_date) {
        this.creation_date = creation_date;
    }

    public void setLast_activity_date(int last_activity_date) {
        this.last_activity_date = last_activity_date;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setAnswer_count(int answer_count) {
        this.answer_count = answer_count;
    }

    public void setUp_vote_count(int up_vote_count) {
        this.up_vote_count = up_vote_count;
    }

    public void setDown_vote_count(int down_vote_count) {
        this.down_vote_count = down_vote_count;
    }

    public void setFavorite_count(int favorite_count) {
        this.favorite_count = favorite_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public void setIs_answered(boolean is_answered) {
        this.is_answered = is_answered;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public void setOwner_user_id(int owner_user_id) {this.owner_user_id = owner_user_id;}

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public void setTags(Set<Tags> tags) {this.tags = tags;}
}
