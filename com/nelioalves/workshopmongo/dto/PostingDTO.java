package com.nelioalves.workshopmongo.dto;

import java.util.Date;

public class PostingDTO {

    private Long id;
    private String title;
    private String body;
    private String authorId; // Assuming you want to send the author's ID
    private Date date;

    // Construtores, getters e setters

    public PostingDTO() {
    }

    public PostingDTO(Long id, String title, String body, String authorId, Date date) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.authorId = authorId;
        this.date = date;
    }

    // Getters e setters

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
