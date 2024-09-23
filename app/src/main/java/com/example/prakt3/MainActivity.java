package com.example.prakt3;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TimePicker timePicker;
    Button btnSetAlarm;
    TextView txtAlarmTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = findViewById(R.id.timePicker);
        btnSetAlarm = findViewById(R.id.btnSetAlarm);
        txtAlarmTime = findViewById(R.id.txtAlarmTime);

        timePicker.setIs24HourView(true);


        btnSetAlarm.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
            calendar.set(Calendar.MINUTE, timePicker.getMinute());
            calendar.set(Calendar.SECOND, 0);

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            String formattedTime = timeFormat.format(calendar.getTime());

            Intent intent = new Intent(MainActivity.this, Alarm.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    MainActivity.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(), pendingIntent);

            Toast.makeText(MainActivity.this, "Будильник был установлен на " + formattedTime,
                    Toast.LENGTH_LONG).show();

            txtAlarmTime.setText("Будильник установлен на " + formattedTime);
        });
    }
}
