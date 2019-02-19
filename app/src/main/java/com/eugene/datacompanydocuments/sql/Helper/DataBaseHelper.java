package com.eugene.datacompanydocuments.sql.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.eugene.datacompanydocuments.model.Company;
import com.eugene.datacompanydocuments.sql.Table.ClassTable;
import com.eugene.datacompanydocuments.sql.Table.CompanyTable;
import com.eugene.datacompanydocuments.sql.Table.DocFile;
import com.eugene.datacompanydocuments.sql.Table.DocTable;
import com.eugene.datacompanydocuments.sql.Table.DossierTable;
import com.eugene.datacompanydocuments.sql.Table.SectionTable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "fileDossier.db";

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
                DossierTable.DosserEntry.COLUMN_ID_COMPANY + " TEXT NOT NULL, " +
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
                DocTable.DocEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DocTable.DocEntry.COLUMN_DOCUMENT_NAME + " TEXT NOT NULL, " +
                DocTable.DocEntry.COLUMN_DOCUMENT_VERSION + " TEXT NOT NULL, " +
                DocTable.DocEntry.COLUMN_DOCUMENT_ID_CLASS + " TEXT NOT NULL, " +
                DocTable.DocEntry.COLUMN_DOCUMENT_ID_SECTIONS + " TEXT NOT NULL, " +
                DocTable.DocEntry.COLUMN_DOC_URL_FILE + " TEXT NOT NULL " +
                ");";

        final String SQL_CREATE_DOC_FILE_TABLE = "CREATE TABLE " +
                DocFile.DocFileEntry.TABLE_NAME + " (" +
                DocFile.DocFileEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DocFile.DocFileEntry.COLUMN_DOCUMENT_FILE_NAME + " TEXT NOT NULL, " +
                DocFile.DocFileEntry.COLUMN_DOC_URL_FILE + " TEXT NOT NULL " +
                ");";

        db.execSQL(SQL_CREATE_CLASS_TABLE);
        db.execSQL(SQL_CREATE_DOSSIER_TABLE);
        db.execSQL(SQL_CREATE_COMPANY_TABLE);
        db.execSQL(SQL_CREATE_SECRION_TABLE);
        db.execSQL(SQL_CREATE_DOC_TABLE);
        db.execSQL(SQL_CREATE_DOC_FILE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DossierTable.DosserEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DocTable.DocEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ClassTable.ClassEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CompanyTable.CompanyEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SectionTable.SectionEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DocFile.DocFileEntry.TABLE_NAME);
        onCreate(db);
    }

    public DataBaseHelper open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        DBHelper.close();
    }

    public void addCompany(Company company) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CompanyTable.CompanyEntry.COLUMN_NAME_COMPANY, company.getNameCompany());
        values.put(CompanyTable.CompanyEntry.COLUMN_INN_COMPANY, company.getINNCompany());
        values.put(CompanyTable.CompanyEntry.COLUMN_KPP_COMPANY, company.getKPPCompany());
        values.put(CompanyTable.CompanyEntry.COLUMN_OGRN_COMPANY, company.getOGRNCompany());

        db.insert(CompanyTable.CompanyEntry.TABLE_NAME, null, values);
        db.close();
    }

    public List<Company> getAllCompany(){
        String[] columns = {
                CompanyTable.CompanyEntry.COLUMN_NAME_COMPANY,
                CompanyTable.CompanyEntry.COLUMN_INN_COMPANY,
                CompanyTable.CompanyEntry.COLUMN_KPP_COMPANY,
                CompanyTable.CompanyEntry.COLUMN_OGRN_COMPANY
        };
        String sortOrder = CompanyTable.CompanyEntry.COLUMN_NAME_COMPANY + " ASC";
        List<Company> companyList = new ArrayList<Company>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(CompanyTable.CompanyEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if (cursor.moveToFirst()){
            do {
                Company company = new Company();
                company.setNameCompany(cursor.getString(cursor.getColumnIndex(CompanyTable.CompanyEntry.COLUMN_NAME_COMPANY)));
                company.setINNCompany(cursor.getString(cursor.getColumnIndex(CompanyTable.CompanyEntry.COLUMN_INN_COMPANY)));
                company.setKPPCompany(cursor.getString(cursor.getColumnIndex(CompanyTable.CompanyEntry.COLUMN_KPP_COMPANY)));
                company.setOGRNCompany(cursor.getString(cursor.getColumnIndex(CompanyTable.CompanyEntry.COLUMN_OGRN_COMPANY)));

                companyList.add(company);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return companyList;
    }

}
