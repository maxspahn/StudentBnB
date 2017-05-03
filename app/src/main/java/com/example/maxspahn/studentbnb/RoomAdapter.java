package com.example.maxspahn.studentbnb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pedro León on 29/04/2017.
 */

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomAdapterViewHolder> {

    private String[] mRoomData; // array of information to be displayed, setter implemented at the end

    private final RoomAdapterOnClickHandler mOnClickHandler;

    public interface RoomAdapterOnClickHandler{
        void onClick(String roomData);
    }
    /*
    constructor
     */
    public RoomAdapter(RoomAdapterOnClickHandler onClickHandler) {
        mOnClickHandler = onClickHandler;
    }

    public class RoomAdapterViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        public final TextView mRoomTextView;

        public RoomAdapterViewHolder(View view) {
            super(view);
            mRoomTextView = (TextView) view.findViewById(R.id.tv_room_data);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String roomData = mRoomData[adapterPosition];
            mOnClickHandler.onClick(roomData);
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
    /*

     */
    @Override
    public void onBindViewHolder(RoomAdapterViewHolder roomAdapterViewHolder, int position) {
        String room = mRoomData[position];
        roomAdapterViewHolder.mRoomTextView.setText(room);
    }
    /*
    get total number of items in data array
     */
    @Override
    public int getItemCount() {
        if (null == mRoomData) return 0;
        return mRoomData.length;
    }
    /*
    data setter
     */
    public void setRoomData(String[] roomData) {
        mRoomData = roomData;
        notifyDataSetChanged();
    }
}