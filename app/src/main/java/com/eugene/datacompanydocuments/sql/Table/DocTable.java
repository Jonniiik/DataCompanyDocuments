package com.eugene.datacompanydocuments.sql.Table;

import android.provider.BaseColumns;

public class DocTable {
    private DocTable(){}
    public static final class DocEntry implements BaseColumns {
        public static final String TABLE_NAME = "doc";
        public static final String COLUMN_DOC_FILE = "docFile";
    }
}
