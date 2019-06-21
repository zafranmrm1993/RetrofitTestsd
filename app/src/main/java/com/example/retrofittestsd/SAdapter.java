package com.example.retrofittestsd;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.retrofittestsd.api.SModel;

import java.util.List;

public class SAdapter extends RecyclerView.Adapter<SAdapter.ViewHolder> {


    private onAdapterOnClickListner adapterOnClickListner;
    private List<SModel> sModels;
    private LayoutInflater layoutInflater;

    public interface onAdapterOnClickListner{
        void getSmodelPossition(int id);
    }

    public SAdapter(Context context, List<SModel> sModels,onAdapterOnClickListner adapterOnClickListner) {
        this.layoutInflater = LayoutInflater.from(context);
        this.sModels = sModels;
        this.adapterOnClickListner = adapterOnClickListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(layoutInflater.inflate(R.layout.row_elemence, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.name_textview.setText(sModels.get(i).getName());
        viewHolder.age_textview.setText(""+sModels.get(i).getAge());
        adapterOnClickListner.getSmodelPossition(i);
        viewHolder.row_list_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterOnClickListner.getSmodelPossition(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name_textview;
        private TextView age_textview;
        private LinearLayout row_list_view;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_textview =itemView.findViewById(R.id.name_textview);
            age_textview =itemView.findViewById(R.id.age_textview);
            row_list_view = itemView.findViewById(R.id.row_list_view);

        }
    }
}
