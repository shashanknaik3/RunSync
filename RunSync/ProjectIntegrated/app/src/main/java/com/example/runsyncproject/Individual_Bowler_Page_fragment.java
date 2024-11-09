package com.example.runsyncproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

  public class Individual_Bowler_Page_fragment extends Fragment {

    private TextView wicketsTextView;
    private TextView runsGivenTextView;
    TextView bname;
    private TextView oversTextView;
    private TextView maidensTextView;
    private TextView economyTextView;
    private TextView fiveWicketsTextView;
    private TextView fourWicketsTextView;
    private TextView averageTextView;
    private TextView bestFiguresTextView;

    public Individual_Bowler_Page_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_individual__bowler__page_fragment, container, false);

        wicketsTextView = view.findViewById(R.id.wickets);
        runsGivenTextView = view.findViewById(R.id.runsGiven);
        oversTextView = view.findViewById(R.id.overs);
        maidensTextView = view.findViewById(R.id.maidens);
        economyTextView = view.findViewById(R.id.economy);
        fiveWicketsTextView = view.findViewById(R.id.fivewickets);
        fourWicketsTextView = view.findViewById(R.id.fourwickets);
        averageTextView = view.findViewById(R.id.averageB);
       bestFiguresTextView = view.findViewById(R.id.bestFigures);
       bname = view.findViewById(R.id.bname);

        String bowlerName = getArguments().getString("bowlerName");
        Log.d("received",bowlerName+"bowler object reveiced");

        BolwerDataBaseHandler dbHandler = new BolwerDataBaseHandler(requireContext());
        Bowler bowler = dbHandler.getBowlerData(bowlerName);
        Log.d("received",bowler.getName()+" bowler object reveiced from databse");

        bname.setText(bowler.getName());
        wicketsTextView.setText(String.valueOf(bowler.getWickets()));
        runsGivenTextView.setText(String.valueOf(bowler.getRuns()));
        oversTextView.setText(String.valueOf(bowler.getOvers()+"."+bowler.getBalls()));
        maidensTextView.setText(String.valueOf(bowler.getMaidens()));

        averageTextView.setText(String.valueOf(bowler.getRuns()));
        bestFiguresTextView.setText(String.valueOf(bowler.getRuns()+"/"+bowler.getWickets()));

        if(bowler.getWickets()==4)
        {
            fourWicketsTextView.setText("1");
        } else if (bowler.getWickets()>=5) {
            fiveWicketsTextView.setText("1");
        }
        else {

        }
        double economy;

        if(bowler.getOvers()!=0) {
             economy = (bowler.getRuns()) / (bowler.getOvers());


        }
        else {
            economy = (bowler.getRuns()) / ((bowler.getBalls()*0.1666));
        }
        economyTextView.setText(String.format("%.2f", economy));
        return view;
    }
}
