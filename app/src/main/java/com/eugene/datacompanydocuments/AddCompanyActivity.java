package com.eugene.datacompanydocuments;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eugene.datacompanydocuments.Adapter.RecyclerViewListAddCompany;
import com.eugene.datacompanydocuments.model.Company;
import com.eugene.datacompanydocuments.sql.Helper.DataBaseHelper;
import com.eugene.datacompanydocuments.sql.Table.CompanyTable;

public class AddCompanyActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonAddCompany;

    EditText editTextNameCompany;
    EditText editTextINNCompany;
    EditText editTextKPPCompany;
    EditText editTextOGRNCompany;

    RecyclerView recyclerViewCompanyAddCompany;

    private SQLiteDatabase mDataBase;
    private RecyclerViewListAddCompany mAdapterListAddCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(AddCompanyActivity.this);
        mDataBase = dataBaseHelper.getWritableDatabase();

        recyclerViewCompanyAddCompany = findViewById(R.id.recyclerViewCompanyAddCompany);
        recyclerViewCompanyAddCompany.setLayoutManager(new LinearLayoutManager(this));
        mAdapterListAddCompany = new RecyclerViewListAddCompany(this, getAllCompany());
        recyclerViewCompanyAddCompany.setAdapter(mAdapterListAddCompany);
        recyclerViewCompanyAddCompany.setVisibility(View.GONE);

        editTextINNCompany = (EditText) findViewById(R.id.editTextINNCompany);
        editTextKPPCompany = (EditText) findViewById(R.id.editTextKPPCompany);
        editTextNameCompany = (EditText) findViewById(R.id.editTextNameCompany);
        editTextOGRNCompany = (EditText) findViewById(R.id.editTextOGRNCompany);

        buttonAddCompany = (Button) findViewById(R.id.buttonAddCompany);
        buttonAddCompany.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        addCompany();
    }

    private void addCompany() {
        if (editTextNameCompany.getText().toString().trim().length() == 0 || editTextINNCompany.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Введите 'Имя компании' и 'ИНН'", Toast.LENGTH_LONG).show();
        } else {

            String nameCompany = editTextNameCompany.getText().toString();
            String INNCompany = editTextINNCompany.getText().toString();
            String KPPCompany = editTextKPPCompany.getText().toString();
            String OGRNCompany = editTextOGRNCompany.getText().toString();

            ContentValues cv = new ContentValues();
            cv.put(CompanyTable.CompanyEntry.COLUMN_NAME_COMPANY, nameCompany);
            cv.put(String.valueOf(CompanyTable.CompanyEntry.COLUMN_INN_COMPANY), INNCompany);
            cv.put(String.valueOf(CompanyTable.CompanyEntry.COLUMN_OGRN_COMPANY), OGRNCompany);
            cv.put(String.valueOf(CompanyTable.CompanyEntry.COLUMN_KPP_COMPANY), KPPCompany);

            mDataBase.insert(CompanyTable.CompanyEntry.TABLE_NAME, null, cv);
            mAdapterListAddCompany.swapCursor(getAllCompany());

            Log.e("Проверка", "Данные фрагмента" + " " + "Имя компании: " + nameCompany + " " + " ИНН " + INNCompany + " " + " КПП " + KPPCompany + " " + " ОГРН " + OGRNCompany);

            editTextKPPCompany.getText().clear();
            editTextNameCompany.getText().clear();
            editTextINNCompany.getText().clear();
            editTextOGRNCompany.getText().clear();

            Intent intent = new Intent(this, CompanyActivity.class);
            startActivity(intent);
        }
    }

    private Cursor getAllCompany() {
        return mDataBase.query(
                CompanyTable.CompanyEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                CompanyTable.CompanyEntry.COLUMN_NAME_COMPANY + " DESC", "10"
        );
    }
}
