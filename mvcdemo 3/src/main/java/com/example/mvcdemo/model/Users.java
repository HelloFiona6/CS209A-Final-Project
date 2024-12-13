package com.example.mvcdemo.model;

// Users.java

import javax.persistence.*;

@Entity
@Table
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private int account_id;
    private int reputation;
    private String user_type;
    @Column( nullable = true)
    private int accept_rate=0;
    private String profile_image;
    private String display_name;
    private String link;

    public int getUser_id() {
        return user_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public int getReputation() {
        return reputation;
    }

    public int getAccept_rate() {
        return accept_rate;
    }

    public String getUser_type() {
        return user_type;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public String getLink() {
        return link;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setAccept_rate(int accept_rate) {
        this.accept_rate = accept_rate;
    }
}
