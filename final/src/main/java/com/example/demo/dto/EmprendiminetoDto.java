package com.example.demo.dto;

import com.example.demo.entity.Tags;

import java.util.ArrayList;
import java.util.List;

public class EmprendiminetoDto {
    private String name;
    private String description;
    private String content;
    private double goal;
    private boolean published;
    private List<Tags> tags = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }
}
