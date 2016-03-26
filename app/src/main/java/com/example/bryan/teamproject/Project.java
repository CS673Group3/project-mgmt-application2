package com.example.bryan.teamproject;

/**
 * Created by ChihWu on 3/22/16.
 */
public class Project {

    private long projectId;
    private long listId;
    private String name;
    private String description;
    private String completed;
    private String hidden;

    public static final String TRUE = "1";
    public static final String FALSE = "0";

    public Project() {
        name = "";
        description = "";
        completed = FALSE;
        hidden = FALSE;
    }

    public Project(int listId, String name, String description,
                String completed, String hidden) {
        this.listId = listId;
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.hidden = hidden;
    }

    public Project(int taskId, int listId, String name, String description,
                String completed, String hidden) {
        this.projectId = taskId;
        this.listId = listId;
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.hidden = hidden;
    }

    public long getId() {
        return projectId;
    }

    public void setId(long id) {
        this.projectId = id;
    }

    public long getListId() {
        return listId;
    }

    public void setListId(long listId) {
        this.listId = listId;
    }

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

    public String getCompleted() {
        return completed;
    }


    public String getHidden(){
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

}
