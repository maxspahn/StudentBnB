package com.example.maxspahn.studentbnb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Arturo on 02/06/2017.
 */

public class CustomAdapterNewEval extends ArrayAdapter<Trip> {

    public CustomAdapterNewEval(Context context, ArrayList<Trip> trips) {
        super(context, R.layout.new_eval_list_item, trips);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater evalInflater = LayoutInflater.from(getContext());
        View evalView = evalInflater.inflate(R.layout.new_eval_list_item, parent, false);

        Trip singleTrip = getItem(position);
        TextView textCity = (TextView) evalView.findViewById(R.id.textCity);
        TextView textInitialDate = (TextView) evalView.findViewById(R.id.textInitialDate);
        TextView textFinalDate = (TextView) evalView.findViewById(R.id.textFinalDate);

        textCity.setText(singleTrip.getHostUser().getResidence().getCity());
        textInitialDate.setText(singleTrip.getInitialDate().toString());
        textFinalDate.setText(singleTrip.getFinalDate().toString());

        return evalView;
    }
}
