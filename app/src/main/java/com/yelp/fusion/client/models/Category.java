package com.yelp.fusion.client.models;

import com.fasterxml.jackson.annotation.JsonGetter;

public class Category
{
    @JsonGetter("title")
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    String title;

    @JsonGetter("alias")
    public String getAlias() {
        return this.alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    String alias;

}
