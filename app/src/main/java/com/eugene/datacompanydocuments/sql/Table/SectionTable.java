package com.eugene.datacompanydocuments.sql.Table;

import android.provider.BaseColumns;

public class SectionTable {
    private SectionTable(){}
    public static final class SectionEntry implements BaseColumns{
        public static final String TABLE_NAME = "section";
        public static final String COLUMN_NAME_SECTION = "nameSection";
        public static final String COLUMN_ID_CLASS = "IdClass";
    }
}
