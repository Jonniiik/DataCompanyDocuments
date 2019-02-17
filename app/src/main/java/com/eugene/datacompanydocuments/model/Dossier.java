package com.eugene.datacompanydocuments.model;

public class Dossier {
    private int id;
    private String nameDosser;
    private String dataActual;
    private String dataCreate;
    private String version;
    private String IDSection;
    private String IdCompany;
    private String IdDOC;

    public Dossier(int id, String nameDosser, String dataActual, String dataCreate, String version, String IDSection, String idCompany, String idDOC) {
        this.id = id;
        this.nameDosser = nameDosser;
        this.dataActual = dataActual;
        this.dataCreate = dataCreate;
        this.version = version;
        this.IDSection = IDSection;
        IdCompany = idCompany;
        IdDOC = idDOC;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameDosser() {
        return nameDosser;
    }

    public void setNameDosser(String nameDosser) {
        this.nameDosser = nameDosser;
    }

    public String getDataActual() {
        return dataActual;
    }

    public void setDataActual(String dataActual) {
        this.dataActual = dataActual;
    }

    public String getDataCreate() {
        return dataCreate;
    }

    public void setDataCreate(String dataCreate) {
        this.dataCreate = dataCreate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIDSection() {
        return IDSection;
    }

    public void setIDSection(String IDSection) {
        this.IDSection = IDSection;
    }

    public String getIdCompany() {
        return IdCompany;
    }

    public void setIdCompany(String idCompany) {
        IdCompany = idCompany;
    }

    public String getIdDOC() {
        return IdDOC;
    }

    public void setIdDOC(String idDOC) {
        IdDOC = idDOC;
    }
}
