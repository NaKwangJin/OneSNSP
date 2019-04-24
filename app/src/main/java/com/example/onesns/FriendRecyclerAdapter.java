package com.example.onesns;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FriendRecyclerAdapter extends RecyclerView.Adapter<FriendRecyclerAdapter.ItemViewHolder> {



    private final List<FriendRecyclerItem> mDataList;

    public interface MyRecyclerViewClickListener{
        void onItemClicked(int position);

    }

    private MyRecyclerViewClickListener mListener;

    public void setOnclickListener(MyRecyclerViewClickListener listener){
        mListener = listener;
    }

    public FriendRecyclerAdapter(List<FriendRecyclerItem> dataList){
        mDataList = dataList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemcard,viewGroup,false);

        return new ItemViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        FriendRecyclerItem item = mDataList.get(i);
        itemViewHolder.contents.setText(item.getContents());
    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView contents;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            contents = itemView.findViewById(R.id.contents_text);

        }
    }
}
