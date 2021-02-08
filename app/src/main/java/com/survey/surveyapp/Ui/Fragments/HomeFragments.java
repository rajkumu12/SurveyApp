package com.survey.surveyapp.Ui.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.survey.surveyapp.R;
import com.survey.surveyapp.Ui.Activity.ReportActivity;
import com.survey.surveyapp.Ui.Activity.ViewIdCardActivity;
import com.survey.surveyapp.Ui.Activity.WorkerIdCardActivity;

public class HomeFragments extends Fragment {

    LinearLayout lly_create,lly_report;
    public HomeFragments() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home_fragments, container, false);

        lly_create=view.findViewById(R.id.lly_workerid_card);
        lly_report=view.findViewById(R.id.lly_report);


        lly_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), WorkerIdCardActivity.class));
            }
        });

        lly_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), ReportActivity.class));
            }
        });

        return view;
    }
}