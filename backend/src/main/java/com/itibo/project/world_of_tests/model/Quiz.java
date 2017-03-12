package com.itibo.project.world_of_tests.model;

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
    @Size(max = 50)
    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "date", length = 50)
    private String date;

    @Column(name = "author", length = 50)
    private Long author;

    @OneToMany
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private List<Comment> commentList;

    public Quiz(){

    }

    public Quiz(Long id, String title, String date, Long author, List<Comment> commentList){
        this.id = id;
        this.title = title;
        this.date = date;
        this.author = author;
        this.commentList = commentList;
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

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
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
