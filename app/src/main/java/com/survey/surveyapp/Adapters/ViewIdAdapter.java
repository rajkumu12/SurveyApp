package com.survey.surveyapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.survey.surveyapp.Models.DailyReportModel;
import com.survey.surveyapp.Models.GetIdCardModel;
import com.survey.surveyapp.Models.IdCardDataModel;
import com.survey.surveyapp.Models.ViewIdModels;
import com.survey.surveyapp.R;
import com.survey.surveyapp.Ui.Activity.IdDetailActivity;
import com.survey.surveyapp.Ui.Activity.ViewIdCardActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewIdAdapter extends RecyclerView.Adapter<ViewIdAdapter.ViewHolder> {

    private Context context;
    private List<IdCardDataModel> arrayList;

    public ViewIdAdapter(Context context, List<IdCardDataModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewIdAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_id_cards, parent, false);
        return new ViewIdAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewIdAdapter.ViewHolder holder, final int position) {

        IdCardDataModel idCardDataModel = arrayList.get(position);

        holder.tv_name.setText(idCardDataModel.getFname() + " " + idCardDataModel.getLanme());
        holder.tv_designation.setText("Designation- " + idCardDataModel.getDesignation());
        holder.tv_id.setText("Id-" + idCardDataModel.getId());
        holder.tv_dob.setText(idCardDataModel.getDob());

        Glide.with(context)
                .load(idCardDataModel.getProfile_pic())
                .centerCrop()
                .placeholder(R.drawable.ic_user)
                .into(holder.id_profile);


        holder.linearLayout_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, IdDetailActivity.class);
                intent.putExtra("id", idCardDataModel.getId());
                intent.putExtra("user_id", idCardDataModel.getUser_id());
                intent.putExtra("fname", idCardDataModel.getFname());
                intent.putExtra("lanme", idCardDataModel.getLanme());
                intent.putExtra("email", idCardDataModel.getEmail());
                intent.putExtra("dob", idCardDataModel.getDob());
                intent.putExtra("gender", idCardDataModel.getGender());
                intent.putExtra("mobile", idCardDataModel.getMobile());
                intent.putExtra("designation", idCardDataModel.getDesignation());
                intent.putExtra("address", idCardDataModel.getAddress());
                intent.putExtra("profile_pic", idCardDataModel.getProfile_pic());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout linearLayout_card;
        CircleImageView id_profile;
        TextView tv_name, tv_designation, tv_id, tv_dob;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout_card = itemView.findViewById(R.id.lly_card);
            id_profile = itemView.findViewById(R.id.image_ok);
            tv_name = itemView.findViewById(R.id.tv_name_idholder);
            tv_designation = itemView.findViewById(R.id.tv_designation_hol);
            tv_id = itemView.findViewById(R.id.tv_id_idholder);
            tv_dob = itemView.findViewById(R.id.tv_dob_idholder);

        }
    }
}


