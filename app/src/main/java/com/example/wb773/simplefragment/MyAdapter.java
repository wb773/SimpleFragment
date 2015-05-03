package com.example.wb773.simplefragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * アダプタ
 * Created by wb773 on 15/05/03.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private ArrayList<ViewItem> mViewItems;

    MyAdapter(ArrayList<ViewItem> viewItems){
        mViewItems = viewItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        //TODO set the view's size, margins, paddings and layout parameters



        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(mViewItems.get(position).title);
        holder.desctiprion.setText(mViewItems.get(position).description);
    }

    @Override
    public int getItemCount() {
        return mViewItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView desctiprion;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            desctiprion = (TextView)itemView.findViewById(R.id.description);
        }
    }

    public static class ViewItem {
        public ViewItem(String title, String description){
            this.title = title;
            this.description = description;
        }
        public String title = "";
        public String description = "";
    }
}
