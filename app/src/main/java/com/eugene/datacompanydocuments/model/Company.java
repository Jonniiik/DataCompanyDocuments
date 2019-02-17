package com.eugene.datacompanydocuments.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Company  {
    private int id;
    private String nameCompany;
    private String INNCompany;
    private String KPPCompany;
    private String OGRNCompany;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getINNCompany() {
        return INNCompany;
    }

    public void setINNCompany(String INNCompany) {
        this.INNCompany = INNCompany;
    }

    public String getKPPCompany() {
        return KPPCompany;
    }

    public void setKPPCompany(String KPPCompany) {
        this.KPPCompany = KPPCompany;
    }

    public String getOGRNCompany() {
        return OGRNCompany;
    }

    public void setOGRNCompany(String OGRNCompany) {
        this.OGRNCompany = OGRNCompany;
    }
}
