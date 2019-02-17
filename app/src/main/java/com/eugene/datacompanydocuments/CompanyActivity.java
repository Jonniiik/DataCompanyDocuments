package com.eugene.datacompanydocuments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.eugene.datacompanydocuments.Adapter.RecyclerViewListAddCompany;
import com.eugene.datacompanydocuments.sql.Helper.DataBaseHelper;
import com.eugene.datacompanydocuments.sql.Table.CompanyTable;
import com.mancj.materialsearchbar.MaterialSearchBar;

public class CompanyActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerViewScanCompany;
    EditText editTextScanCompany;
    MaterialSearchBar materialSearchBarScanCompany;
    ImageButton buttonScanCompany;

    private SQLiteDatabase mDataBase;
    private RecyclerViewListAddCompany mAdapterListAddCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        mDataBase = dataBaseHelper.getWritableDatabase();

        recyclerViewScanCompany = (RecyclerView) findViewById(R.id.recyclerViewScanCompany);
        recyclerViewScanCompany.setLayoutManager(new LinearLayoutManager(this));
        mAdapterListAddCompany = new RecyclerViewListAddCompany(this, getAllCompany());
        recyclerViewScanCompany.setAdapter(mAdapterListAddCompany);
        delCompanyRV();

        editTextScanCompany = findViewById(R.id.editTextScanCompany);
        materialSearchBarScanCompany = findViewById(R.id.materialSearchBarScanCompany);
        materialSearchBarScanCompany.setHint("Поиск");
        materialSearchBarScanCompany.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        // loadSuggestList();
        buttonScanCompany = findViewById(R.id.buttonScanCompany);
        buttonScanCompany.setOnClickListener(this);


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

    private void delCompanyRV() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                removeCompany((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerViewScanCompany);
    }

    private void removeCompany(long id) {
        mDataBase.delete(CompanyTable.CompanyEntry.TABLE_NAME, CompanyTable.CompanyEntry._ID + "=" + id, null);
        mAdapterListAddCompany.swapCursor(getAllCompany());
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
    private Cursor getSearchCompany(){
        return mDataBase.rawQuery("Select * FROM "
                + CompanyTable.CompanyEntry.TABLE_NAME
                + " where "
                + CompanyTable.CompanyEntry.COLUMN_NAME_COMPANY
                + "=?", new String[]{CompanyTable.CompanyEntry._ID + ""});

    }

    @Override
    public void onClick(View v) {

    }
}
