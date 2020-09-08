package com.cherish.gadspracticeproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cherish.gadspracticeproject.R;
import com.cherish.gadspracticeproject.data.model.api.response.LearningLeadersData;
import com.cherish.gadspracticeproject.data.model.api.response.SkillIQData;
import com.cherish.gadspracticeproject.module.GlideApp;

import java.util.List;

public class SkillIQLeadersAdapter extends RecyclerView.Adapter<SkillIQLeadersAdapter.ItemHolderClass> {

    private Context context;
    private List<SkillIQData> skillIQData;

    public SkillIQLeadersAdapter(Context context, List<SkillIQData>skillIQData){
        this.context = context;
        this.skillIQData = skillIQData;
    }

    @NonNull
    @Override
    public SkillIQLeadersAdapter.ItemHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_iq_layout,parent,false);
        return new SkillIQLeadersAdapter.ItemHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillIQLeadersAdapter.ItemHolderClass holder, int position) {
        SkillIQData objectData = skillIQData.get(position);
        holder.name.setText(objectData.getName());
        holder.country.setText(String.format("%s.", objectData.getCountry()));
        holder.score.setText(String.format("%s skill IQ Score, ", objectData.getScore()));
        GlideApp.with(holder.image)
                .load(objectData.getBadgeUrl())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return skillIQData.size();
    }

    public class  ItemHolderClass extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name,score,country;

        public ItemHolderClass(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iq_image);
            name = itemView.findViewById(R.id.iq_name);
            score = itemView.findViewById(R.id.score);
            country = itemView.findViewById(R.id.country);
        }
    }
}
