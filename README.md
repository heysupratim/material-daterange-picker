[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-MaterialDateRangePicker-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/2501)

[ ![Download](https://api.bintray.com/packages/borax12/maven/material-datetime-rangepicker/images/download.svg) ](https://bintray.com/borax12/maven/material-datetime-rangepicker/_latestVersion)

Material Date and Time Picker with Range Selection
======================================================


Credit to the original amazing material date picker library by wdullaer - https://github.com/wdullaer/MaterialDateTimePicker

##Adding to your project

Add the jcenter repository information in your build.gradle file like this
```java

repositories {
  jcenter()
}


dependencies {
  compile 'com.borax12.materialdaterangepicker:library:1.2'
}

```


##Update
-Added Time Range Picker


##Date Selection

![FROM](/screenshots/2.png?raw=true)
![TO](/screenshots/1.png?raw=true)

##Time Selection

![FROM](/screenshots/3.png?raw=true)
![TO](/screenshots/4.png?raw=true)

Support for Android 4.0 and up.

From the original library documentation -

You may also add the library as an Android Library to your project. All the library files live in ```library```.

Using the  Pickers
--------------------------------

1. Implement an `OnDateSetListener` or `OnTimeSetListener`
2. Create a ``DatePickerDialog` using the supplied factory

### Implement an `OnDateSetListener`
In order to receive the date  set in the picker, you will need to implement the `OnDateSetListener` interfaces. Typically this will be the `Activity` or `Fragment` that creates the Pickers.

or
### Implement an `OnTimeSetListener`
In order to receive the time set in the picker, you will need to implement the `OnTimeSetListener` interfaces. Typically this will be the `Activity` or `Fragment` that creates the Pickers.

```java

//new onDateSet
@Override
public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth,int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {

}

@Override
public void onTimeSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth,int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
        String minuteString = minute < 10 ? "0"+minute : ""+minute;
        String hourStringEnd = hourOfDayEnd < 10 ? "0"+hourOfDayEnd : ""+hourOfDayEnd;
        String minuteStringEnd = minuteEnd < 10 ? "0"+minuteEnd : ""+minuteEnd;
        String time = "You picked the following time: From - "+hourString+"h"+minuteString+" To - "+hourStringEnd+"h"+minuteStringEnd;

        timeTextView.setText(time);

}
```

### Create a DatePickerDialog` using the supplied factory
You will need to create a new instance of `DatePickerDialog` using the static `newInstance()` method, supplying proper default values and a callback. Once the dialogs are configured, you can call `show()`.
```java
Calendar now = Calendar.getInstance();
DatePickerDialog dpd = DatePickerDialog.newInstance(
  MainActivity.this,
  now.get(Calendar.YEAR),
  now.get(Calendar.MONTH),
  now.get(Calendar.DAY_OF_MONTH);
);
dpd.show(getFragmentManager(), "Datepickerdialog");
```

### Create a TimePickerDialog` using the supplied factory
You will need to create a new instance of `TimePickerDialog` using the static `newInstance()` method, supplying proper default values and a callback. Once the dialogs are configured, you can call `show()`.
```java
Calendar now = Calendar.getInstance();
TimePickerDialog tpd = TimePickerDialog.newInstance(
                        MainActivity.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        false
                );
tpd.show(getFragmentManager(), "Timepickerdialog");
```

For other documentation regarding theming , handling orientation changes , and callbacks - check out the original documentation - https://github.com/wdullaer/MaterialDateTimePicker

TODO
----
1. More device config handling
