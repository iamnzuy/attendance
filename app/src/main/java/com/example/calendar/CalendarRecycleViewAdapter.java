package com.example.calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarRecycleViewAdapter extends RecyclerView.Adapter<CalendarRecycleViewAdapter.ViewHolder> {

    private final ArrayList<String> daysOfMonth;
    private final OnItemListener onItemListener;
    private final int displayedMonth;
    private final int displayedYear;
    private final LocalDate currentDate;

    public CalendarRecycleViewAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener, int displayedMonth, int displayedYear) {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
        this.currentDate = LocalDate.now();
        this.displayedMonth = displayedMonth;
        this.displayedYear = displayedYear;
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
        String day = daysOfMonth.get(position);
        holder.dayOfMonth.setText(day);

        if (!day.isEmpty()) {
            int dayInt = Integer.parseInt(day);
            if (currentDate.getDayOfMonth() == dayInt &&
                    currentDate.getMonthValue() == displayedMonth &&
                    currentDate.getYear() == displayedYear) {
                holder.dayOfMonth.setBackgroundResource(R.drawable.gray_circle);
            } else if (position == 22 || position == 17 || position == 15 ) {
                holder.dayOfMonth.setBackgroundResource(R.drawable.red_circle);
            }
            else if (position == 10 || position == 13 ) {
                holder.dayOfMonth.setBackgroundResource(R.drawable.blue_circle);
            }
            else if (position == 10 || position == 13 ) {
                holder.dayOfMonth.setBackgroundResource(R.drawable.blue_circle);
            }
            else if (position == 9 ) {
                holder.dayOfMonth.setBackgroundResource(R.drawable.yellow_circle);
            }

            else {
                holder.dayOfMonth.setBackgroundResource(R.drawable.green_circle);
            }
        } else {
            holder.dayOfMonth.setBackground(null);
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
