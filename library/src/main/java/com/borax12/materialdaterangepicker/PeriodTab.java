package com.borax12.materialdaterangepicker;

/**
 * Created by hei on 22.02.2018.
 */

public enum PeriodTab {
    FROM(0),
    TO(1);

    private int value;

    PeriodTab(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
