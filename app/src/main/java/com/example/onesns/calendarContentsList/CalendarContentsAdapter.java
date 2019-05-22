package com.example.onesns.calendarContentsList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.onesns.R;

import java.util.List;

public class CalendarContentsAdapter extends RecyclerView.Adapter<CalendarContentsAdapter.ItemViewHolder> {

    private final List<CalendarContentsItem> mDataList;

    public CalendarContentsAdapter(List<CalendarContentsItem> mDataList) {
        this.mDataList = mDataList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.calendarcontentsview,viewGroup,false);

        return new CalendarContentsAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
            CalendarContentsItem cal = mDataList.get(i);
            itemViewHolder.contents.setText(cal.getContents());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView contents;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            contents = itemView.findViewById(R.id.calContent);

        }
    }
}


