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

    ArrayList<FriendRecyclerItem> mItems;


    public FriendRecyclerAdapter(ArrayList<FriendRecyclerItem> mItems) {
        this.mItems = mItems;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_friend_list_view,viewGroup,false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.mNameTv.setText(mItems.get(i).getName());
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView mNameTv;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTv = (TextView)itemView.findViewById(R.id.itemNameTv);
        }
    }
}
