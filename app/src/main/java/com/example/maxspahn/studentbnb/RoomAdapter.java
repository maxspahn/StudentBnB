package com.example.maxspahn.studentbnb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pedro León on 29/04/2017.
 */

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomAdapterViewHolder> {

    private ArrayList<User> mUserData; // array of information to be displayed, setter implemented at the end

    private final RoomAdapterOnClickHandler mOnClickHandler;

    public interface RoomAdapterOnClickHandler{
        void onClick(User u);
    }
    /*
    constructor
     */
    public RoomAdapter(RoomAdapterOnClickHandler onClickHandler) {
        mOnClickHandler = onClickHandler;
    }

    public class RoomAdapterViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        public final TextView userTextView;
        public final TextView residenceTextView;

        public RoomAdapterViewHolder(View view) {
            super(view);
            userTextView = (TextView) view.findViewById(R.id.tv_user_data);
            residenceTextView = (TextView) view.findViewById(R.id.tv_residence_data);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            User u = mUserData.get(adapterPosition);
            mOnClickHandler.onClick(u);
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
        User u = mUserData.get(position);
        roomAdapterViewHolder.userTextView.setText(u.getName() + " " + u.getSurname());
        roomAdapterViewHolder.residenceTextView.setText(u.getResidence().getName());
    }
    /*
    get total number of items in data array
     */
    @Override
    public int getItemCount() {
        if (null == mUserData) return 0;
        return mUserData.size();
    }
    /*
    data setter
     */
    public void setRoomData(ArrayList<User> userData) {
        mUserData = userData;
        notifyDataSetChanged();
    }
}