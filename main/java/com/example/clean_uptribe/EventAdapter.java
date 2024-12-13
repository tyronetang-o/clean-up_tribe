package com.example.clean_uptribe;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private Context context;
    private List<EventModel> eventList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(EventModel eventModel);
    }

    public EventAdapter(Context context, List<EventModel> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventModel eventModel = eventList.get(position);
        holder.eventNameTxt.setText(eventModel.getEventName());
        holder.locationTxt.setText(eventModel.getLocation());
        holder.dateTimeTxt.setText(eventModel.getDate() + " at " + eventModel.getTime());
        holder.descriptionTxt.setText(eventModel.getDescription());

        holder.itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onItemClick(eventModel);
            }
        });

    }


    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        TextView eventNameTxt, locationTxt, dateTimeTxt, descriptionTxt;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventNameTxt = itemView.findViewById(R.id.eventNameTxt);
            locationTxt = itemView.findViewById(R.id.locationTxt);
            dateTimeTxt = itemView.findViewById(R.id.dateTimeTxt);
            descriptionTxt = itemView.findViewById(R.id.descriptionTxt);
        }
    }
}
