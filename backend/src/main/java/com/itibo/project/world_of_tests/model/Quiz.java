package com.itibo.project.world_of_tests.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by Andrew on 26.02.2017.
 */
@Entity
@Table(name = "quizzes")
public class Quiz implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "title", length = 50)
    private String title;

    @NotNull
    @NotEmpty
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "date", length = 50)
    private String date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @NotNull
    @NotEmpty
    @Column(name = "json_quiz", columnDefinition = "JSON")
    private String json_quiz;

    @NotNull
    @NotEmpty
    @Column(name ="json_answer", columnDefinition = "JSON")
    private String json_answer;

    @OneToMany
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private List<Comment> commentList;

    public Quiz(){

    }

    public Quiz(Long id, String title, String date, User author, List<Comment> commentList, String json_quiz, String json_answer){
        this.id = id;
        this.title = title;
        this.date = date;
        this.author = author;
        this.commentList = commentList;
        this.json_quiz = json_quiz;
        this.json_answer = json_answer;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @JsonIgnore
    public void setJson_answer(String json_answer) {
        this.json_answer = json_answer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date, commentList);
    }

    @Override
    public String toString(){
        return "id: "+id
                +"; title: "+title
                +"; date: "+date
                +"; author: "+author
                +"; comments:"+commentList;
    }
}
