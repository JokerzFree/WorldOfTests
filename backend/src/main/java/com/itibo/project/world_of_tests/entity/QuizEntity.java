package com.itibo.project.world_of_tests.entity;

import java.io.Serializable;

/**
 * Created by Andrew on 26.02.2017.
 */
public class QuizEntity implements Serializable {
    private Long id;
    private String title;
    private String date;
    private Long author;

    public QuizEntity(){

    }

    public QuizEntity(Long id, String title, String date, Long author) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }
}
