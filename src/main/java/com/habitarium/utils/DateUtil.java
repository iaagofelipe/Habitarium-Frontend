package com.habitarium.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String datePaid(int amountPaid, Date date, int payday) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, payday);
        calendar.add(Calendar.MONTH, amountPaid);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        String dateStr = format.format(calendar.getTime());

        return dateStr;
    }
}
