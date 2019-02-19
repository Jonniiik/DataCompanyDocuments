package com.eugene.datacompanydocuments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.eugene.datacompanydocuments.Adapter.RecyclerViewListAddCompany;
import com.eugene.datacompanydocuments.model.Company;
import com.eugene.datacompanydocuments.sql.Helper.DataBaseHelper;
import com.eugene.datacompanydocuments.sql.Table.CompanyTable;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class CompanyActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    RecyclerView recyclerViewScanCompany;
    Context context = CompanyActivity.this;
    private ArrayList<Company> companyArrayList;
    RecyclerViewListAddCompany listAddCompanyAdapter;
    private DataBaseHelper databaseHelper;
    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDatabase = databaseHelper.getWritableDatabase();

        initViews();
        initObjects();


        Intent intent = getIntent();
        if (intent.hasExtra("nameCompany")) {
            String nameCompany = getIntent().getExtras().getString("nameCompany");
            String INNCompany = getIntent().getExtras().getString("INNCompany");
            String KPPCompany = getIntent().getExtras().getString("KPPCompany");
            String OGRNCompany = getIntent().getExtras().getString("OGRNCompany");
        } else {
            Toast.makeText(this, "Not Api Data", Toast.LENGTH_LONG).show();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(CompanyActivity.this, AddCompanyActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        recyclerViewScanCompany = (RecyclerView) findViewById(R.id.recyclerViewScanCompany);
    }

    private void initObjects() {
        companyArrayList = new ArrayList<>();
        listAddCompanyAdapter = new RecyclerViewListAddCompany(companyArrayList, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewScanCompany.setLayoutManager(mLayoutManager);
        recyclerViewScanCompany.setItemAnimator(new DefaultItemAnimator());
        recyclerViewScanCompany.setHasFixedSize(true);
        recyclerViewScanCompany.setAdapter(listAddCompanyAdapter);

        databaseHelper = new DataBaseHelper(this);
        getDataFromSQLite();
    }

    private void getDataFromSQLite() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                companyArrayList.clear();
                companyArrayList.addAll(databaseHelper.getAllCompany());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                listAddCompanyAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem search = menu.findItem(R.id.searchCompany);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<Company> newList = new ArrayList<>();
        for (Company company : companyArrayList) {
            String nameCompany = company.getNameCompany().toLowerCase();
            String InnCompany = company.getINNCompany().toLowerCase();
            String OgrnCompany = company.getOGRNCompany().toLowerCase();
            if (InnCompany.contains(newText) || nameCompany.contains(newText) || OgrnCompany.contains(newText)) {
                newList.add(company);
            }
        }
        listAddCompanyAdapter.setFilter(newList);
        return true;
    }
}
