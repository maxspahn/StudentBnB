package com.example.maxspahn.studentbnb;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Arturo on 01/06/2017.
 */

public class CustomAdapter extends ArrayAdapter<Trip> {

    public CustomAdapter(Context context, ArrayList<Trip> trips) {
        super(context, R.layout.eval_list_item, trips);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater evalInflater = LayoutInflater.from(getContext());
        View evalView = evalInflater.inflate(R.layout.eval_list_item, parent, false);

        Trip singleTrip = getItem(position);
        TextView textCity = (TextView) evalView.findViewById(R.id.textCity);
        TextView textEval = (TextView) evalView.findViewById(R.id.textEval);

        textCity.setText(singleTrip.getHostUser().getResidence().getCity());
        if(singleTrip.getEvaluation() != null) {
            textEval.setText(singleTrip.getEvaluation().toString());
        } else {
            textEval.setText("Not evaluated");
        }

        return evalView;
    }
}
