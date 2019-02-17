package com.eugene.datacompanydocuments.model;

public class Class {
    private int id;
    private String nameClass;
    private String section;

    public Class(int id, String nameClass, String section) {
        this.id = id;
        this.nameClass = nameClass;
        this.section = section;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
