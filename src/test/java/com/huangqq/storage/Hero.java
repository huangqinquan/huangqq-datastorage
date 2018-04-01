package com.huangqq.storage;

import com.google.gson.Gson;

/**
 * Created by huangqq on 2017/11/14.
 */
public class Hero {

    private Integer id;
    private String name;
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    private String displayName;
    private String tags;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
