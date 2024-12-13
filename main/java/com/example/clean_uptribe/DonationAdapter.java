package com.example.clean_uptribe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.DonationViewHolder> {

    private Context context;
    private List<DonationModel> donationList;

    public DonationAdapter(Context context, List<DonationModel> donationList) {
        this.context = context;
        this.donationList = donationList;
    }

    @NonNull
    @Override
    public DonationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.donation_item_layout, parent, false);
        return new DonationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonationViewHolder holder, int position) {
        DonationModel donation = donationList.get(position);
        holder.nameTextView.setText(donation.getName());
        holder.amountTextView.setText("Amount: ₱" + donation.getAmount());
        holder.contactTextView.setText("Contact: " + donation.getContactNumber());
    }

    @Override
    public int getItemCount() {
        return donationList.size();
    }

    public static class DonationViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, amountTextView, contactTextView;

        public DonationViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tvName);
            amountTextView = itemView.findViewById(R.id.tvAmount);
            contactTextView = itemView.findViewById(R.id.tvContact);
        }
    }
}
