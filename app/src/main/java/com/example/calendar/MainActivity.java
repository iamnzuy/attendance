package com.example.calendar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarRecycleViewAdapter.OnItemListener {

    private TextView tvMonthYear;
    private RecyclerView rvCalendar;
    private LocalDate selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.attendance_employee_detail);

        rvCalendar = findViewById(R.id.rvCalendar);
        tvMonthYear = findViewById(R.id.tvMonthYear);

        selectedDate = LocalDate.now();
        setTvMonthYear();

    }

    private String monthYearFromDate(LocalDate selectedDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return selectedDate.format(formatter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = (firstOfMonth.getDayOfWeek().getValue() % 7);

        for (int i = 1; i <= 42; i++) {
            if (i <= dayOfWeek || i > (daysInMonth + dayOfWeek)) {
                daysInMonthArray.add("");
            }else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    public void setTvMonthYear() {
        tvMonthYear.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        CalendarRecycleViewAdapter calendarRecycleViewAdapter = new CalendarRecycleViewAdapter(daysInMonth, this, selectedDate.getMonthValue(), selectedDate.getYear());
        rvCalendar.setLayoutManager(new GridLayoutManager(getApplicationContext(), 7));
        rvCalendar.setAdapter(calendarRecycleViewAdapter);

    }

    public void nextMonthAction(View view) {
        selectedDate = selectedDate.plusMonths(1);
        setTvMonthYear();
    }

    public void prevMonthAction(View view) {
        selectedDate = selectedDate.minusMonths(1);
        setTvMonthYear();
    }

    @Override
    public void onClick(int position, String dayText) {
        if(!dayText.equals(""))
        {
            String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }
    }
}