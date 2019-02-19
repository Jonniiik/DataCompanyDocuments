package com.eugene.datacompanydocuments.sql.Table;

import android.provider.BaseColumns;

public class DossierTable {
    private DossierTable(){}

    public static final class DosserEntry implements BaseColumns {
        public static final String TABLE_NAME = "dosser";
        public static final String COLUMN_NAME_DOSSER = "nameDosser";
        public static final String COLUMN_DATA_ACTUAL = "dataActual";
        public static final String COLUMN_DATA_CREATURE = "dataCreate";
        public static final String COLUMN_ID_COMPANY = "IdCompany";
        public static final String COLUMN_ID_DOC = "IdDOC";
    }
}
