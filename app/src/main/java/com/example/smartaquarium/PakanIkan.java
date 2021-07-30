package com.example.smartaquarium;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PakanIkan extends AppCompatActivity {
    DatabaseReference reff, reff2, reff3;
    Button button1, button2, button3, buttonApply;
    int t1Hour, t1Minute, t2Hour, t2Minute, t3Hour, t3Minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pakan_ikan);
        //AssignVariable
        button1 = findViewById(R.id.buttonwaktu);
        button2 = findViewById(R.id.buttonwaktu2);
        button3 = findViewById(R.id.buttonwaktu3);
        buttonApply = findViewById(R.id.btapply);

        reff= FirebaseDatabase.getInstance().getReference().child("Timer").child("1");
        reff2= FirebaseDatabase.getInstance().getReference().child("Timer").child("2");
        reff3= FirebaseDatabase.getInstance().getReference().child("Timer").child("3");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        PakanIkan.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                //Initialize hour and minute
                                t1Hour = hour;
                                t1Minute = minute;
                                //Initialize Calender
                                Calendar calendar = Calendar.getInstance();
                                //Set hour and minute
                                calendar.set(0, 0, 0, t1Hour, t1Minute);
                                //Set selected time on text view
                                button1.setText(DateFormat.format("hh:mm aa", calendar));
                            }
                        }, 12, 0, false
                );

                //Displayed previous selected time
                timePickerDialog.updateTime(t1Hour, t1Minute);
                //show dialog
                timePickerDialog.show();
            }

        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialiaze time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        PakanIkan.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                //Initialize hour and minute
                                t2Hour = hour;
                                t2Minute = minute;
                                //Store hour and minute in string
                                String time = t2Hour + ":" + t2Minute;
                                //Initialize 24 hours time format
                                SimpleDateFormat f24hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24hours.parse(time);
                                    //Initialize 12 hours time format
                                    SimpleDateFormat f12hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    //Set selected time on text view
                                    button2.setText(f12hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );
                //Displayed previous selected time
                timePickerDialog.updateTime(t2Hour, t2Minute);
                //Show dialog
                timePickerDialog.show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialiaze time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        PakanIkan.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                //Initialize hour and minute
                                t3Hour = hour;
                                t3Minute = minute;
                                //Store hour and minute in string
                                String time = t3Hour + ":" + t3Minute;
                                //Initialize 24 hours time format
                                SimpleDateFormat f24hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24hours.parse(time);
                                    //Initialize 12 hours time format
                                    SimpleDateFormat f12hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    //Set selected time on text view
                                    button3.setText(f12hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );
                //Displayed previous selected time
                timePickerDialog.updateTime(t3Hour, t3Minute);
                //Show dialog
                timePickerDialog.show();
            }
        });
        
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        reff.child("jam").setValue(t1Hour);
                        reff.child("menit").setValue(t1Minute);
                        reff2.child("jam").setValue(t2Hour);
                        reff2.child("menit").setValue(t2Minute);
                        reff3.child("jam").setValue(t3Hour);
                        reff3.child("menit").setValue(t3Minute);
            }
        });
    }
}