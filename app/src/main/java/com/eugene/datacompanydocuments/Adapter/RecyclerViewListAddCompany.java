package com.eugene.datacompanydocuments.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eugene.datacompanydocuments.CompanyActivity;
import com.eugene.datacompanydocuments.DossierActivity;
import com.eugene.datacompanydocuments.OnTapListener;
import com.eugene.datacompanydocuments.R;
import com.eugene.datacompanydocuments.model.Company;
import com.eugene.datacompanydocuments.sql.Table.CompanyTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RecyclerViewListAddCompany extends RecyclerView.Adapter<RecyclerViewListAddCompany.ListAddCompanyViewHolder> {

    private ArrayList<Company> companyList;
    private Context mContext;
    // private Cursor mCursor;
    private OnTapListener onTapListener;

    public RecyclerViewListAddCompany(ArrayList<Company> companyList, Context mContext) {
        this.companyList = companyList;
        this.mContext = mContext;
    }

    public class ListAddCompanyViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout relativeLayoutListScanCompany;
        LinearLayout linerLayoutListAddCompany;
        TextView nameCompanyRvAddCompany;
        TextView InnCompanyRvAddCompany;
        TextView KppCompanyRvAddCompany;
        TextView OgrnCompanyRvAddCompany;

        public ListAddCompanyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameCompanyRvAddCompany = itemView.findViewById(R.id.nameCompanyRvAddCompany);
            InnCompanyRvAddCompany = itemView.findViewById(R.id.InnCompanyRvAddCompany);
            KppCompanyRvAddCompany = itemView.findViewById(R.id.KppCompanyRvAddCompany);
            OgrnCompanyRvAddCompany = itemView.findViewById(R.id.OgrnCompanyRvAddCompany);
            relativeLayoutListScanCompany = itemView.findViewById(R.id.relativeLayoutListScanCompany);
            linerLayoutListAddCompany = itemView.findViewById(R.id.linerLayoutListAddCompany);

        }
    }

    @NonNull
    @Override
    public ListAddCompanyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.recycler_view_list_add_company, viewGroup, false);
        return new ListAddCompanyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAddCompanyViewHolder listAddCompanyViewHolder, final int position) {
        Company company = companyList.get(position);

        listAddCompanyViewHolder.nameCompanyRvAddCompany.setText(companyList.get(position).getNameCompany());
        listAddCompanyViewHolder.InnCompanyRvAddCompany.setText(companyList.get(position).getINNCompany());
        listAddCompanyViewHolder.KppCompanyRvAddCompany.setText(companyList.get(position).getKPPCompany());
        listAddCompanyViewHolder.OgrnCompanyRvAddCompany.setText(companyList.get(position).getOGRNCompany());

        // final long id = mCursor.getLong(mCursor.getColumnIndex(CompanyTable.CompanyEntry._ID));
        // listAddCompanyViewHolder.itemView.setTag(id);
        listAddCompanyViewHolder.relativeLayoutListScanCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTapListener != null) {
                    onTapListener.OnTapView(position);
                }
                Intent intent = new Intent(mContext, DossierActivity.class);
                intent.putExtra("companyName", companyList.get(position).getNameCompany());
                // intent.putExtra("idCompany", id);
                mContext.startActivities(new Intent[]{intent});
            }
        });

    }

    @Override
    public int getItemCount() {
        return companyList.size();
    }

    public void setOnTapListener(OnTapListener onTapListener) {
        this.onTapListener = onTapListener;
    }

    public void setFilter(ArrayList<Company> newList) {
        companyList = new ArrayList<>();
        companyList.addAll(newList);
        notifyDataSetChanged();
    }
}
