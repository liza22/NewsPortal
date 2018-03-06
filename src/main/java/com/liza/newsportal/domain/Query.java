package com.liza.newsportal.domain;

public class Query {
    private String title;

    private String body;

    private Integer category_id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String firstname) {
        this.title = firstname;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String lastname) {
        this.body = lastname;
    }

    public Integer getCategory() {
        return category_id;
    }

    public void setCategory(Integer id) {
        this.category_id = id;
    }
}
