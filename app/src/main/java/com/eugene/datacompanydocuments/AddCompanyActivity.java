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
import com.eugene.datacompanydocuments.sql.Helper.InputValidation;
import com.eugene.datacompanydocuments.sql.Table.CompanyTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AddCompanyActivity extends AppCompatActivity{

    Button buttonAddCompany;

    EditText editTextNameCompany;
    EditText editTextINNCompany;
    EditText editTextKPPCompany;
    EditText editTextOGRNCompany;

    private SQLiteDatabase mDataBase;
    private RecyclerViewListAddCompany mAdapterListAddCompany;
    private DataBaseHelper databaseHelper;
    private Company company;
    private InputValidation inputValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();
        initObjects();
        InitButton();

    }

    private void InitButton() {
        buttonAddCompany = (Button) findViewById(R.id.buttonAddCompany);
        buttonAddCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSQLCompany();
            }
        });
    }

    private void initViews() {
        editTextNameCompany = (EditText) findViewById(R.id.editTextNameCompany);
        editTextINNCompany = (EditText) findViewById(R.id.editTextINNCompany);
        editTextKPPCompany = (EditText) findViewById(R.id.editTextKPPCompany);
        editTextOGRNCompany = (EditText) findViewById(R.id.editTextOGRNCompany);
    }

    private void addSQLCompany() {
        if (editTextNameCompany.getText().toString().trim().length() == 0 || editTextINNCompany.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Введите 'Имя компании' и 'ИНН'", Toast.LENGTH_LONG).show();
        } else {


            company.setNameCompany(editTextNameCompany.getText().toString().trim());
            company.setINNCompany(editTextINNCompany.getText().toString().trim());
            company.setKPPCompany(editTextKPPCompany.getText().toString().trim());
            company.setOGRNCompany(editTextOGRNCompany.getText().toString().trim());

            databaseHelper.addCompany(company);

            Intent intent = new Intent(this, CompanyActivity.class);
            intent.putExtra("nameCompany", editTextNameCompany.getText().toString().trim());
            intent.putExtra("INNCompany", editTextINNCompany.getText().toString().trim());
            intent.putExtra("KPPCompany", editTextKPPCompany.getText().toString().trim());
            intent.putExtra("OGRNCompany", editTextOGRNCompany.getText().toString().trim());

            emptyEditText();
            startActivity(intent);
        }

    }

    private void emptyEditText() {
        editTextNameCompany.setText(null);
        editTextINNCompany.setText(null);
        editTextKPPCompany.setText(null);
        editTextOGRNCompany.setText(null);
    }

    private void initObjects() {
        inputValidation = new InputValidation(this);
        databaseHelper = new DataBaseHelper(this);
        company = new Company();
    }
}
