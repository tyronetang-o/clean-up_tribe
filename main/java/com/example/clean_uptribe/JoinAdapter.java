package com.example.clean_uptribe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JoinAdapter extends RecyclerView.Adapter<JoinAdapter.ViewHolder> {

    private Context context;
    private List<JoinModel> joinList;

    public JoinAdapter(Context context, List<JoinModel> joinList) {
        this.context = context;
        this.joinList = joinList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_join_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JoinModel joinModel = joinList.get(position);

        holder.nameTextView.setText(joinModel.getName());
        holder.emailTextView.setText(joinModel.getEmail());
        holder.locationTextView.setText(joinModel.getLocation());
        holder.interestsTextView.setText(joinModel.getInterests());
        holder.contributionsTextView.setText(joinModel.getContributions());
    }

    @Override
    public int getItemCount() {
        return joinList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, emailTextView, locationTextView, interestsTextView, contributionsTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
            interestsTextView = itemView.findViewById(R.id.interestsTextView);
            contributionsTextView = itemView.findViewById(R.id.contributionsTextView);
        }
    }
}
