package com.eugene.datacompanydocuments.sql.Table;

import android.provider.BaseColumns;

public class DocFile {
    public DocFile() {}

    public static final class DocFileEntry implements BaseColumns {
        public static final String TABLE_NAME = "docFile";
        public static final String COLUMN_DOCUMENT_FILE_NAME = "nameFileDocument";
        public static final String COLUMN_DOC_URL_FILE = "docURLFile";
    }
}
