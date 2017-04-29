package com.example.maxspahn.studentbnb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Pedro León on 29/04/2017.
 */

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomAdapterViewHolder> {

    private String[] mRoomData;

    public RoomAdapter() {

    }

    public class RoomAdapterViewHolder extends RecyclerView.ViewHolder {
        public final TextView mRoomTextView;

        public RoomAdapterViewHolder(View view) {
            super(view);
            mRoomTextView = (TextView) view.findViewById(R.id.tv_room_data);
        }
    }

    @Override
    public RoomAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.room_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new RoomAdapterViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RoomAdapterViewHolder forecastAdapterViewHolder, int position) {
        String room = mRoomData[position];
        forecastAdapterViewHolder.mRoomTextView.setText(room);
    }


    @Override
    public int getItemCount() {
        if (null == mRoomData) return 0;
        return mRoomData.length;
    }


    public void setRoomData(String[] roomData) {
        mRoomData = roomData;
        notifyDataSetChanged();
    }
}