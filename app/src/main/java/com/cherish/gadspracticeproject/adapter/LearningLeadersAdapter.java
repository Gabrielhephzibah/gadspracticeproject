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
import com.cherish.gadspracticeproject.module.GlideApp;

import java.util.List;

public class LearningLeadersAdapter extends  RecyclerView.Adapter<LearningLeadersAdapter.ItemHolderClass> {
    private Context context;
    private List<LearningLeadersData> apiObjectData;

    public LearningLeadersAdapter(Context context, List<LearningLeadersData>apiObjectData){
        this.context = context;
        this.apiObjectData = apiObjectData;
    }

    @NonNull
    @Override
    public LearningLeadersAdapter.ItemHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.learning_leader_layout,parent,false);
        return new ItemHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningLeadersAdapter.ItemHolderClass holder, int position) {
        LearningLeadersData objectData = apiObjectData.get(position);
        holder.name.setText(objectData.getName());
        holder.country.setText(String.format("%s.", objectData.getCountry()));
        holder.hours.setText(String.format("%s learning hours, ", objectData.getHours()));
        GlideApp.with(holder.image)
                .load(objectData.getBadgeUrl())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return apiObjectData.size();
    }

    public class  ItemHolderClass extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name,hours,country;

        public ItemHolderClass(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            hours = itemView.findViewById(R.id.hours);
            country = itemView.findViewById(R.id.country);
        }
    }
}
