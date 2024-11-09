package com.example.runsyncproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Individual_Batsman_Page extends Fragment {

    private TextView runsTextView;
    private TextView duckTextView;
    private TextView hundredTextView;
    private TextView fiftyTextView;
    private TextView thirtyTextView;
    private TextView sixesTextView;
    private TextView foursTextView;
    private TextView averageTextView;
    private TextView strikerateTextView;
    private TextView bestScoreTextView;
    private TextView runsScoredTextView;
    TextView name;

    public Individual_Batsman_Page() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_individual__batsman__page, container, false);

        runsTextView = view.findViewById(R.id.runsScored);
        duckTextView = view.findViewById(R.id.duck);
        hundredTextView = view.findViewById(R.id.hundred);
        fiftyTextView = view.findViewById(R.id.fifty);
        thirtyTextView = view.findViewById(R.id.thirty);
        sixesTextView = view.findViewById(R.id.sixes);
        foursTextView = view.findViewById(R.id.fours);
        averageTextView = view.findViewById(R.id.average);
        strikerateTextView = view.findViewById(R.id.strikerate);
        bestScoreTextView = view.findViewById(R.id.bestScore);
        runsScoredTextView = view.findViewById(R.id.runsScored);
        name = view.findViewById(R.id.textViewName);


        String batsmanName = getArguments().getString("batsmanName");

        BatsmanDatabaseHandler dbHandler = new BatsmanDatabaseHandler(requireContext());
        Batsman batsman = dbHandler.getBatsmanByName(batsmanName);

        name.setText(batsman.getName());
        runsTextView.setText(String.valueOf(batsman.getRuns()));
        //duckTextView.setText(String.valueOf(batsman.getDuck()));
      //  hundredTextView.setText(String.valueOf(batsman.getHundred()));
        //fiftyTextView.setText(String.valueOf(batsman.getFifty()));
       // thirtyTextView.setText(String.valueOf(batsman.getThirty()));
        sixesTextView.setText(String.valueOf(batsman.getSix()));
        foursTextView.setText(String.valueOf(batsman.getFour()));
       averageTextView.setText(String.valueOf(batsman.getRuns()));

       bestScoreTextView.setText(String.valueOf(batsman.getRuns()));
        runsScoredTextView.setText(String.valueOf(batsman.getRuns()));

        if(batsman.getRuns()==0)
        {
            duckTextView.setText("1");
        }

        else if(batsman.getRuns() >=30 && batsman.getRuns()<50)
        {
            thirtyTextView.setText("1");
        }
        else if(batsman.getRuns() >=50 && batsman.getRuns()<100)
        {
            fiftyTextView.setText("1");
        }
        else if(batsman.getRuns() >=100)
        {
            hundredTextView.setText("1");
        }


        int balls = batsman.getBalls();
        int runs = batsman.getRuns();
        float strike_rate;
        if(balls!=0) {
            strike_rate = (runs / balls) * 100;


        }
        else{
            strike_rate =0;
        }
        strikerateTextView.setText(String.format("%.2f", strike_rate));
        return view;
    }
}
