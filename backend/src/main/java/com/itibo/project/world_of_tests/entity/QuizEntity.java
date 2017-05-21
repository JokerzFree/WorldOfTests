package com.itibo.project.world_of_tests.entity;

import java.io.Serializable;

/**
 * Quiz Entity for creating or updating Quiz objects
 */
public class QuizEntity implements Serializable {
    private Long id;
    private String title;
    private String description;
    private String date;
    private Long author;
    private String json_quiz;
    private String json_answer;
    private String image;
    private String[] filenames;

    public QuizEntity() {

    }

    public QuizEntity(Long id, String title, String description, String date, Long author, String json_quiz, String json_answer, String image, String[] filenames) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.author = author;
        this.json_quiz = json_quiz;
        this.json_answer = json_answer;
        this.image = image;
        this.filenames = filenames;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getJson_quiz() {
        return json_quiz;
    }

    public void setJson_quiz(String json_quiz) {
        this.json_quiz = json_quiz;
    }

    public String getJson_answer() {
        return json_answer;
    }

    public void setJson_answer(String json_answer) {
        this.json_answer = json_answer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String[] getFilenames() {
        return filenames;
    }

    public void setFilenames(String[] filenames) {
        this.filenames = filenames;
    }
}
