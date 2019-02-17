package com.eugene.datacompanydocuments.sql.Table;

import android.provider.BaseColumns;

public class CompanyTable {
    private CompanyTable() {}
    public static final class CompanyEntry implements BaseColumns {
        public static final String TABLE_NAME = "company";
        public static final String COLUMN_NAME_COMPANY = "nameCompany";
        public static final String COLUMN_INN_COMPANY = "INNCompany";
        public static final String COLUMN_KPP_COMPANY = "KPPCompany";
        public static final String COLUMN_OGRN_COMPANY = "OGRNCompany";
    }
}
