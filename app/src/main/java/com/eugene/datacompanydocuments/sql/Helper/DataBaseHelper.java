package com.eugene.datacompanydocuments.sql.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.eugene.datacompanydocuments.sql.Table.ClassTable;
import com.eugene.datacompanydocuments.sql.Table.CompanyTable;
import com.eugene.datacompanydocuments.sql.Table.DocTable;
import com.eugene.datacompanydocuments.sql.Table.DossierTable;
import com.eugene.datacompanydocuments.sql.Table.SectionTable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private DataBaseHelper DBHelper;
    private SQLiteDatabase sqLiteDatabase;

    public static final String DATABASE_NAME = "fileDossier.db";

    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_COMPANY = "company";
    public static final String TABLE_CLASS = "class";
    public static final String TABLE_DOC = "doc";
    public static final String TABLE_DOSSIER = "dosser";
    public static final String TABLE_SECTION = "section";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_COMPANY_TABLE = "CREATE TABLE " +
                CompanyTable.CompanyEntry.TABLE_NAME + " (" +
                CompanyTable.CompanyEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CompanyTable.CompanyEntry.COLUMN_NAME_COMPANY + " TEXT NOT NULL, " +
                CompanyTable.CompanyEntry.COLUMN_INN_COMPANY + " TEXT NOT NULL, " +
                CompanyTable.CompanyEntry.COLUMN_KPP_COMPANY + " TEXT NOT NULL, " +
                CompanyTable.CompanyEntry.COLUMN_OGRN_COMPANY + " TEXT NOT NULL " +
                ");";

        final String SQL_CREATE_CLASS_TABLE = "CREATE TABLE " +
                ClassTable.ClassEntry.TABLE_NAME + " (" +
                ClassTable.ClassEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ClassTable.ClassEntry.COLUMN_NAME_CLASS + " TEXT NOT NULL, " +
                ClassTable.ClassEntry.COLUMN_SECTION + " TEXT NOT NULL " +
                ");";

        final String SQL_CREATE_DOSSIER_TABLE = "CREATE TABLE " +
                DossierTable.DosserEntry.TABLE_NAME + " (" +
                DossierTable.DosserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DossierTable.DosserEntry.COLUMN_NAME_DOSSER + " TEXT NOT NULL, " +
                DossierTable.DosserEntry.COLUMN_DATA_ACTUAL + " TEXT NOT NULL, " +
                DossierTable.DosserEntry.COLUMN_DATA_CREATURE + " TEXT NOT NULL, " +
                DossierTable.DosserEntry.COLUMN_VERSION + " TEXT NOT NULL, " +
                DossierTable.DosserEntry.COLUMN_ID_COMPANY + " TEXT NOT NULL, " +
                DossierTable.DosserEntry.COLUMN_ID_SECTION + " TEXT NOT NULL, " +
                DossierTable.DosserEntry.COLUMN_ID_DOC + " TEXT NOT NULL " +
                ");";

        final String SQL_CREATE_SECRION_TABLE = "CREATE TABLE " +
                SectionTable.SectionEntry.TABLE_NAME + " (" +
                SectionTable.SectionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SectionTable.SectionEntry.COLUMN_NAME_SECTION + " TEXT NOT NULL, " +
                SectionTable.SectionEntry.COLUMN_ID_CLASS + " TEXT NOT NULL " +
                ");";

        final String SQL_CREATE_DOC_TABLE = "CREATE TABLE " +
                DocTable.DocEntry.TABLE_NAME + " (" +
                DocTable.DocEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DocTable.DocEntry.COLUMN_DOC_FILE + " TEXT NOT NULL " +
                ");";

        db.execSQL(SQL_CREATE_CLASS_TABLE);
        db.execSQL(SQL_CREATE_DOSSIER_TABLE);
        db.execSQL(SQL_CREATE_COMPANY_TABLE);
        db.execSQL(SQL_CREATE_SECRION_TABLE);
        db.execSQL(SQL_CREATE_DOC_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DossierTable.DosserEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DocTable.DocEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ClassTable.ClassEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CompanyTable.CompanyEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SectionTable.SectionEntry.TABLE_NAME);
        onCreate(db);
    }
}
