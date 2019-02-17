package com.eugene.datacompanydocuments.model;

public class Section {
    private int id;
    private String nameSection;
    private String IdClass;

    public Section(int id, String nameSection, String idClass) {
        this.id = id;
        this.nameSection = nameSection;
        IdClass = idClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSection() {
        return nameSection;
    }

    public void setNameSection(String nameSection) {
        this.nameSection = nameSection;
    }

    public String getIdClass() {
        return IdClass;
    }

    public void setIdClass(String idClass) {
        IdClass = idClass;
    }
}
