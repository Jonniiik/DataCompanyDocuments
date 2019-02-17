package com.eugene.datacompanydocuments.model;

public class Doc {
    private int id;
    private String DocFile;

    public Doc(int id, String docFile) {
        this.id = id;
        DocFile = docFile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocFile() {
        return DocFile;
    }

    public void setDocFile(String docFile) {
        DocFile = docFile;
    }
}
