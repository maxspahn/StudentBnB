package com.example.maxspahn.studentbnb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Arturo on 02/06/2017.
 */

public class EvaluateActivity extends Activity {

    Trip trip;
    TextView textTitle;
    ListView listEval;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_evaluate);

        Intent i = getIntent();
        trip = (Trip) i.getSerializableExtra("trip");

        textTitle = (TextView) findViewById(R.id.textTitle);
        textTitle.setText("Evaluate your trip to " + trip.getHostUser().getResidence().getCity() + "!");

        listEval = (ListView) findViewById(R.id.listEval);

        String[] evalElements = {Evaluation.BAD.toString(), Evaluation.NORMAL.toString(), Evaluation.GOOD.toString(), Evaluation.EXCELLENT.toString()};

        ListAdapter profileAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, evalElements);
        listEval.setAdapter(profileAdapter);
        listEval.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String option = ((TextView) view).getText().toString();

                switch (option) {
                    case "BAD":
                        trip.setEvaluation(Evaluation.BAD);
                        break;

                    case "NORMAL":
                        trip.setEvaluation(Evaluation.NORMAL);
                        break;

                    case "GOOD":
                        trip.setEvaluation(Evaluation.GOOD);
                        break;

                    case "EXCELLENT":
                        trip.setEvaluation(Evaluation.EXCELLENT);
                        break;
                }
            }
        });

    }

}
