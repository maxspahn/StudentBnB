package com.example.maxspahn.studentbnb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Pedro León on 06/06/2017.
 */

public class AvailabilityAdapter extends RecyclerView.Adapter<AvailabilityAdapter.AvailabilityAdapterViewHolder>  {

    private ArrayList<Date> mAvailabilityData; // array of information to be displayed, setter implemented at the end

    private final AvailabilityAdapter.AvailabilityAdapterOnClickHandler mOnClickHandler;

    public interface AvailabilityAdapterOnClickHandler {
        void onClick(Date d);
    }
    /*
    constructor
     */
    public AvailabilityAdapter(AvailabilityAdapterOnClickHandler onClickHandler) {
        mOnClickHandler = onClickHandler;
    }

    public class AvailabilityAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView dateTextView;

        public AvailabilityAdapterViewHolder(View view) {
            super(view);
            dateTextView = (TextView) view.findViewById(R.id.tv_user_data);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Date d = mAvailabilityData.get(adapterPosition);
            mOnClickHandler.onClick(d);
        }
    }

    @Override
    public AvailabilityAdapter.AvailabilityAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.room_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new AvailabilityAdapter.AvailabilityAdapterViewHolder(view);
    }
    /*

     */
    @Override
    public void onBindViewHolder(AvailabilityAdapter.AvailabilityAdapterViewHolder roomAvailabilityViewHolder, int position) {
        Date d = mAvailabilityData.get(position);
        roomAvailabilityViewHolder.dateTextView.setText(d.toString());
    }
    /*
    get total number of items in data array
     */
    @Override
    public int getItemCount() {
        if (null == mAvailabilityData) return 0;
        return mAvailabilityData.size();
    }
    /*
    data setter
     */
    public void setRoomData(ArrayList<Date> userData) {
        mAvailabilityData = userData;
        notifyDataSetChanged();
    }
}
