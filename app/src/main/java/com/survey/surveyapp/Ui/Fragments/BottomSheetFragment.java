package com.survey.surveyapp.Ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.survey.surveyapp.Delegates.BaseDelegate;
import com.survey.surveyapp.Delegates.BottomSheetDelegate;
import com.survey.surveyapp.R;


public class BottomSheetFragment extends BottomSheetDialogFragment {
    protected BaseDelegate cb;
    LinearLayout linearLayoutCamera, linearLayoutGallery ;
    public BottomSheetFragment(BaseDelegate cb) {
        this.cb = cb;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false);
        linearLayoutCamera = view.findViewById(R.id.layout_camera);
        linearLayoutGallery = view.findViewById(R.id.layout_gallery);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        linearLayoutCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                ((BottomSheetDelegate) cb).onCameraClick();
            }
        });

        linearLayoutGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                ((BottomSheetDelegate) cb).onGalleryClick();
            }
        });
    }
}