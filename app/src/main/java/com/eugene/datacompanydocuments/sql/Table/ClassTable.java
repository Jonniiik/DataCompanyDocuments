package com.eugene.datacompanydocuments.sql.Table;

import android.provider.BaseColumns;

public class ClassTable {
    private ClassTable(){}
    public static final class ClassEntry implements BaseColumns{
        public static final String TABLE_NAME = "class";
        public static final String COLUMN_NAME_CLASS = "nameClass";
        public static final String COLUMN_SECTION = "section";
    }
}
