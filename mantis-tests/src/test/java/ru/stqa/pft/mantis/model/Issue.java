package ru.stqa.pft.mantis.model;

import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.hamcrest.core.Is;

public class Issue {

    private int id;
    private String summary;
    private String description;
    private Project project;
    private ObjectRef resolution;

    public ObjectRef getResolution() {
        return resolution;
    }

    public void setResolution(ObjectRef resolution) {
        this.resolution = resolution;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void isFixed(boolean fixed) {
        this.fixed = fixed;
    }

    private boolean fixed;

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }


}
