package com.itibo.project.world_of_tests.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itibo.project.world_of_tests.repository.UserRepository;
import com.itibo.project.world_of_tests.service.UserService;
import com.itibo.project.world_of_tests.service.UserServiceImpl;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class Post implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "title")
    private String title;

    @NotNull
    @NotEmpty
    @Column(name = "subtitle")
    private String subtitle;

    @NotNull
    @NotEmpty
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "date", length = 50)
    private String date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    public Post() {
    }

    public Post(String title, String subtitle, String content, String date, User author) {
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(title, post.title) &&
                Objects.equals(subtitle, post.subtitle) &&
                Objects.equals(content, post.content) &&
                Objects.equals(date, post.date) &&
                Objects.equals(author, post.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, subtitle, content, date, author);
    }

    @Override
    public String toString() {
        return "Post{" +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}
