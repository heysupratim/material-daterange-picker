package com.borax12.materialdaterangepickerexample;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.borax12.materialdaterangepicker.time.RadialPickerLayout;
import com.borax12.materialdaterangepicker.time.TimePickerDialog;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements
    DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener, SharedPreferences.OnSharedPreferenceChangeListener
{
    private static final String NIGHT_MODE_KEY = "night_mode";
    private TextView dateTextView;
    private TextView timeTextView;
    private boolean mAutoHighlight;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        AppCompatDelegate.setDefaultNightMode(getNightMode());
        sp.registerOnSharedPreferenceChangeListener(this);
        setContentView(R.layout.activity_main);
        // Find our View instances
        dateTextView = findViewById(R.id.date_textview);
        timeTextView = findViewById(R.id.time_textview);
        Button dateButton = findViewById(R.id.date_button);
        Button timeButton = findViewById(R.id.time_button);
        Switch nightModeSwitch = findViewById(R.id.night_mode_switch);
        nightModeSwitch.setChecked(isNightMode());

        CheckBox ahl = findViewById(R.id.autohighlight_checkbox);
        ahl.setOnCheckedChangeListener((compoundButton, b) ->
                mAutoHighlight = b
        );

        // Show a datepicker when the dateButton is clicked
        dateButton.setOnClickListener(v -> {
            Calendar now = Calendar.getInstance();
            DatePickerDialog dpd = DatePickerDialog.newInstance(
                    MainActivity.this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
            dpd.setAutoHighlight(mAutoHighlight);
            dpd.setThemeDark(isNightMode());
            dpd.show(getSupportFragmentManager(), "Datepickerdialog");
        });

        timeButton.setOnClickListener(v -> {
            Calendar now = Calendar.getInstance();
            TimePickerDialog tpd = TimePickerDialog.newInstance(
                    MainActivity.this,
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    false
            );
            tpd.setOnCancelListener(dialogInterface ->
                    Log.d("TimePicker", "Dialog was cancelled")
            );
            tpd.setThemeDark(isNightMode());
            tpd.show(getSupportFragmentManager(), "Timepickerdialog");
        });
        nightModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) ->
                setNightMode(isChecked)
        );
    }

    @Override
    public void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) getSupportFragmentManager().findFragmentByTag("Datepickerdialog");
        if(dpd != null) dpd.setOnDateSetListener(this);
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth,int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
        String date = "You picked the following date: From- "+dayOfMonth+"/"+(++monthOfYear)+"/"+year+" To "+dayOfMonthEnd+"/"+(++monthOfYearEnd)+"/"+yearEnd;
        dateTextView.setText(date);
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {
        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
        String minuteString = minute < 10 ? "0"+minute : ""+minute;
        String hourStringEnd = hourOfDayEnd < 10 ? "0"+hourOfDayEnd : ""+hourOfDayEnd;
        String minuteStringEnd = minuteEnd < 10 ? "0"+minuteEnd : ""+minuteEnd;
        String time = "You picked the following time: From - "+hourString+"h"+minuteString+" To - "+hourStringEnd+"h"+minuteStringEnd;

        timeTextView.setText(time);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sp.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(NIGHT_MODE_KEY)) {
            recreate();
        }
    }

    private int getNightMode() {
        if (isNightMode()) {
            return AppCompatDelegate.MODE_NIGHT_YES;
        } else {
            return AppCompatDelegate.MODE_NIGHT_NO;
        }
    }

    private boolean isNightMode() {
        return sp.getBoolean(NIGHT_MODE_KEY, false);
    }

    private void setNightMode(boolean isNightMode) {
        sp.edit().putBoolean(NIGHT_MODE_KEY, isNightMode).apply();
    }
}
