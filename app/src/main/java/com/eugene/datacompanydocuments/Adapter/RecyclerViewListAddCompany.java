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
import android.widget.Toast;

import com.eugene.datacompanydocuments.DossierActivity;
import com.eugene.datacompanydocuments.OnTapListener;
import com.eugene.datacompanydocuments.R;
import com.eugene.datacompanydocuments.model.Company;
import com.eugene.datacompanydocuments.sql.Table.CompanyTable;

import java.util.Collections;
import java.util.List;


public class RecyclerViewListAddCompany extends RecyclerView.Adapter<RecyclerViewListAddCompany.ListAddCompanyViewHolder> {

    private Context mContext;
    private Cursor mCursor;
    private List<Company> mCompany = Collections.emptyList();
    private OnTapListener onTapListener;
    private LinearLayout linerLayoutListAddCompany;

    public RecyclerViewListAddCompany(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
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
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        final String nameCompany = mCursor.getString(mCursor.getColumnIndex(CompanyTable.CompanyEntry.COLUMN_NAME_COMPANY));
        String InnCompany = mCursor.getString(mCursor.getColumnIndex(CompanyTable.CompanyEntry.COLUMN_INN_COMPANY));
        String KppCompany = mCursor.getString(mCursor.getColumnIndex(CompanyTable.CompanyEntry.COLUMN_KPP_COMPANY));
        String OgrnCompany = mCursor.getString(mCursor.getColumnIndex(CompanyTable.CompanyEntry.COLUMN_OGRN_COMPANY));

        final long id = mCursor.getLong(mCursor.getColumnIndex(CompanyTable.CompanyEntry._ID));

        listAddCompanyViewHolder.nameCompanyRvAddCompany.setText(nameCompany);
        listAddCompanyViewHolder.InnCompanyRvAddCompany.setText(InnCompany);
        listAddCompanyViewHolder.KppCompanyRvAddCompany.setText(KppCompany);
        listAddCompanyViewHolder.OgrnCompanyRvAddCompany.setText(OgrnCompany);
        listAddCompanyViewHolder.itemView.setTag(id);
        listAddCompanyViewHolder.linerLayoutListAddCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTapListener != null) {
                    onTapListener.OnTapView(position);
                }
                Intent intent = new Intent(mContext, DossierActivity.class);
                intent.putExtra("companyName", nameCompany);
                intent.putExtra("idCompany", id);
                mContext.startActivities(new Intent[]{intent});
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

    public void setOnTapListener(OnTapListener onTapListener) {
        this.onTapListener = onTapListener;
    }
}
