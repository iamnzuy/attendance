package com.example.calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarRecycleViewAdapter extends RecyclerView.Adapter<CalendarRecycleViewAdapter.ViewHolder> {

    private final ArrayList<String> daysOfMonth;
    private final OnItemListener onItemListener;

    public CalendarRecycleViewAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener) {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewHolder = inflater.inflate(R.layout.item_calendar, parent, false);
        ViewGroup.LayoutParams layoutParams = viewHolder.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.2);
        return new ViewHolder(viewHolder, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dayOfMonth.setText(daysOfMonth.get(position));
        if (daysOfMonth.get(position).equals("")) {
            holder.dayOfMonth.setBackground(null);
        }else {
            holder.dayOfMonth.setBackgroundResource(R.drawable.green_circle);
        }
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView dayOfMonth;
        private final OnItemListener onItemListener;

        public ViewHolder(@NonNull View itemView, CalendarRecycleViewAdapter.OnItemListener onItemListener) {
            super(itemView);
            dayOfMonth = itemView.findViewById(R.id.item_calendar);
            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemListener.onClick(getAdapterPosition(), (String) dayOfMonth.getText());
        }
    }

    public interface OnItemListener {
        void onClick (int position, String dayText);
    }
}
