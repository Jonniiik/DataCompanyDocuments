package com.eugene.datacompanydocuments.sql.Helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBOpenHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "fileDossier.db";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;

    SQLiteDatabase mySQLDataBase;

    private final Context myContext;

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (Build.VERSION.SDK_INT >= 15) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";

        } else {
            DB_PATH = Environment.getDataDirectory() + "/data/" + context.getPackageName() + "/databases/";
        }
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public void checkAndCopyDataBase() {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            Log.e("DB_TAG", "database already exist");
        } else {
            this.getReadableDatabase();
        }
        try {
            copyDatabase();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("DB_TAG", "Error copy database");
        }
    }

    public boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        String myPath = DB_PATH + DB_NAME;
        checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        if (checkDB != null) {
            mySQLDataBase.close();
        }
        return checkDB != null ? true : false;
    }

    public void copyDatabase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }
    public void openDatabase(){
        String myPath = DB_PATH + DB_NAME;
        mySQLDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
    public synchronized void close() {
        if (mySQLDataBase != null) {
            mySQLDataBase.close();
        }
        super.close();
    }

    public Cursor QueryData(String query) {
        return mySQLDataBase.rawQuery(query, null);
    }
}
