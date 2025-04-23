package com.plurasight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class FormatDate {

    private final LocalDate TODAYDATE = LocalDate.now();
    private final LocalTime TODAYTIME = LocalTime.now();
    private final LocalTime TODAYTIMEGMT = LocalTime.now().plusHours(7);
    private final LocalDateTime TODAYDATETIME = LocalDateTime.now();
    private DateTimeFormatter dateFormatter;
    private DateTimeFormatter timeFormatter;
    private String formatDate;
    private String formatTime;

    public void displayDifferentFormats(){
        FormatTodayDateAsMM_dd_yyyy();
        FormatTodayDateAsISO_8601();
        FormatTodayDateAsMonth_dd_yyyy();
        FormatTodayDateTimeAsE_MMM_dd_yyyy_HHmm_in_GMT();

        System.out.println("\nChallenge");
        FormatTodayDateTimeAsHHmm_on_dd_MMM_yyyy();
    }

    public void FormatTodayDateAsMM_dd_yyyy(){

        dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        formatDate = TODAYDATETIME.format(dateFormatter);

        System.out.println(formatDate);
    }

    public void FormatTodayDateAsISO_8601(){

        System.out.println(TODAYDATE);
    }

    public void FormatTodayDateAsMonth_dd_yyyy(){

        dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        formatDate = TODAYDATE.format(dateFormatter);

        System.out.println(formatDate);

    }

    public void FormatTodayDateTimeAsE_MMM_dd_yyyy_HHmm_in_GMT(){

        dateFormatter = DateTimeFormatter.ofPattern("E, MMM dd, yyyy");
        formatDate = TODAYDATETIME.format(dateFormatter);

        timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        formatTime = TODAYTIMEGMT.format(timeFormatter);



        System.out.println(formatDate + "  " + formatTime);
    }

    public void FormatTodayDateTimeAsHHmm_on_dd_MMM_yyyy(){

        dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        formatDate = TODAYDATETIME.format(dateFormatter);

        timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        formatTime = TODAYTIME.format(timeFormatter);

        System.out.println(formatTime + " on " + formatDate);
    }



}
