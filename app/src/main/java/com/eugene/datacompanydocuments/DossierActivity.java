package com.eugene.datacompanydocuments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class DossierActivity extends AppCompatActivity {
    EditText editTextNameDossierDossier;
    TextView nameCompanyDossier;
    boolean editNameDossier;
    ImageButton companyActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dossier);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getInformationCompany();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        editTextNameDossierDossier = (EditText) findViewById(R.id.editTextNameDossierDossier);

        companyActivity = (ImageButton) findViewById(R.id.companyActivity);
        companyActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DossierActivity.this, CompanyActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getInformationCompany() {
        Log.e("getCompany", String.valueOf(nameCompanyDossier));
        Log.e("getCompany", "Сведения получены");

        String companyName = getIntent().getStringExtra("companyName");
        String idCompany = getIntent().getStringExtra("idCompany");

        setNameCompany(companyName);
    }

    private void setNameCompany(String companyName) {
        Log.e("setCompany", "set company name");

        nameCompanyDossier = (TextView) findViewById(R.id.nameCompanyDossier);
        nameCompanyDossier.setText(companyName);
        //editTextNameDossierDossier.setText("Электронное досье " + companyName);
    }

}
