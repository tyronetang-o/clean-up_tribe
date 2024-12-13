package com.example.clean_uptribe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArchiveAdapter extends RecyclerView.Adapter<ArchiveAdapter.ArchiveViewHolder> {

    private Context context;
    private List<EventModel> archivedEvents;

    public ArchiveAdapter(Context context, List<EventModel> archivedEvents) {
        this.context = context;
        this.archivedEvents = archivedEvents;
    }

    @NonNull
    @Override
    public ArchiveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.archive_item, parent, false);
        return new ArchiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArchiveViewHolder holder, int position) {
        EventModel event = archivedEvents.get(position);
        holder.eventName.setText(event.getEventName());
        holder.eventLocation.setText(event.getLocation());
        holder.eventDate.setText(event.getDate());
        holder.eventTime.setText(event.getTime());
    }

    @Override
    public int getItemCount() {
        return archivedEvents.size();
    }

    public static class ArchiveViewHolder extends RecyclerView.ViewHolder {

        TextView eventName, eventLocation, eventDate, eventTime;

        public ArchiveViewHolder(@NonNull View itemView) {
            super(itemView);
            eventName = itemView.findViewById(R.id.archiveEventName);
            eventLocation = itemView.findViewById(R.id.archiveEventLocation);
            eventDate = itemView.findViewById(R.id.archiveEventDate);
            eventTime = itemView.findViewById(R.id.archiveEventTime);
        }
    }
}
