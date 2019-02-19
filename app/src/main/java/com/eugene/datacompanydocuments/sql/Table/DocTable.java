package com.eugene.datacompanydocuments.sql.Table;

import android.provider.BaseColumns;

public class DocTable {
    private DocTable(){}
    public static final class DocEntry implements BaseColumns {
        public static final String TABLE_NAME = "doc";
        public static final String COLUMN_DOCUMENT_NAME = "nameDocument";
        public static final String COLUMN_DOCUMENT_VERSION = "versionDocument";
        public static final String COLUMN_DOCUMENT_ID_CLASS = "idClassDocument";
        public static final String COLUMN_DOCUMENT_ID_SECTIONS = "idSectionsDocument";
        public static final String COLUMN_DOC_URL_FILE = "docFile";
    }
}
