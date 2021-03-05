package com.example.scheduller.SkillsActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scheduller.ParticularSkillActivity.ParticularSkillActivity;
import com.example.scheduller.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;
import java.util.Map;

public class SkillsRecyclerViewAdapter extends RecyclerView.Adapter<SkillsRecyclerViewAdapter.SkillsViewHolder> {


    List<Skills> skillsList;
    Context context;

    public SkillsRecyclerViewAdapter(List<Skills> skillsList, Context context) {
        this.skillsList = skillsList;
        this.context = context;
    }

    @NonNull
    @Override
    public SkillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_row, parent, false);
        return new SkillsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillsViewHolder holder, final int position) {

        holder.skillMaterialTextView.setText(skillsList.get(holder.getAdapterPosition()).getSkillName());
        holder.skillsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, ParticularSkillActivity.class);
                intent.putExtra("NAME",skillsList.get(holder.getAdapterPosition()).getSkillName());
                intent.putExtra("ID",skillsList.get(holder.getAdapterPosition()).getId());
                intent.putExtra("DESCRIPTION",skillsList.get(holder.getAdapterPosition()).getSkillDescription());
                intent.putExtra("DATE",skillsList.get(holder.getAdapterPosition()).getDateOfCreation());
                (context).startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return skillsList.size();
    }

    public class SkillsViewHolder extends RecyclerView.ViewHolder {

       MaterialTextView skillMaterialTextView;
       CardView skillsCardView;
        public SkillsViewHolder(@NonNull View itemView) {
            super(itemView);

          skillMaterialTextView=itemView.findViewById(R.id.skillRowXml);
          skillsCardView=itemView.findViewById(R.id.skillsCardView);
        }
    }

//    private static final int VIEW_TYPE_LOADING = 0;
//    private static final int VIEW_TYPE_NORMAL = 1;
//    List<skills> skillsList;
//    private boolean isLoaderVisible = false;
//
//    public SkillsRecyclerViewAdapter(List<skills> skillsList) {
//        super();
//        this.skillsList = skillsList;
//
//    }
//
//    @NonNull
//    @Override
//    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        switch (viewType) {
//            case VIEW_TYPE_NORMAL:
//                return new SkillsRecyclerViewAdapter.SkillsViewHolder(
//                        LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_row, parent, false));
//            case VIEW_TYPE_LOADING:
//                return new SkillsRecyclerViewAdapter.ProgressHolder(
//                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false));
//            default:
//                return null;
//        }
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
//
//        holder.onBind(holder.getCurrentPosition());
//
//    }
//
//
//    public void clear() {
//        skillsList.clear();
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        if (isLoaderVisible) {
//            return position == skillsList.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
//        } else {
//            return VIEW_TYPE_NORMAL;
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return skillsList == null ? 0 : skillsList.size();
//    }
//
//    public void addItems(List<skills> skillsList) {
//        skillsList.addAll(skillsList);
//        notifyDataSetChanged();
//    }
//
//    public void addLoading() {
//        isLoaderVisible = true;
//        skillsList.add(new skills());
//        notifyItemInserted(skillsList.size() - 1);
//    }
//
//    public void removeLoading() {
//        isLoaderVisible = false;
//
//        int position = 0;
//        skills skills= null;
//
//        if (skillsList.size()>0) {
//            position = skillsList.size() - 1;
//            skills = getItem(position);
//        }
//
//        if (skills != null) {
//            skillsList.remove(position);
//            notifyItemRemoved(position);
//        }
//    }
//
//
//    skills getItem(int position) {
//        return skillsList.get(position);
//    }
//
//    public class SkillsViewHolder extends BaseViewHolder {
//
//        MaterialTextView skillsMaterialTextView;
//
//        public SkillsViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            skillsMaterialTextView = itemView.findViewById(R.id.skillRowXml);
//
//        }
//
//        @Override
//        protected void clear() {
//
//        }
//
//        @Override
//        public void onBind(int position) {
//            super.onBind(position);
//
//            skillsMaterialTextView.setText(String.valueOf(skillsList.get(getAdapterPosition()).getSkillName()
//                    + skillsList.get(getAdapterPosition()).getSkillName()));
//
//
//
//
//        }
//    }
//
//    public class ProgressHolder extends BaseViewHolder {
//
//        ProgressBar progressBar;
//
//        ProgressHolder(View itemView) {
//            super(itemView);
//            progressBar = itemView.findViewById(R.id.progressBar);
//        }
//
//        @Override
//        protected void clear() {
//        }
//    }
}
