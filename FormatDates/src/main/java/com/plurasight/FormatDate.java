package com.plurasight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class FormatDate {

    private final LocalDate TODAYDATE = LocalDate.now();                                            //local date and time variables
    private final LocalTime TODAYTIME = LocalTime.now();
    private final LocalTime TODAYTIMEGMT = LocalTime.now().plusHours(7);                 //local time in Greenwich Mean Time
    private final LocalDateTime TODAYDATETIME = LocalDateTime.now();
    private DateTimeFormatter dateFormatter;                                                        //formatter variables
    private DateTimeFormatter timeFormatter;
    private String formatDate;                                                                      //Strings to hold the formatted dates and times
    private String formatTime;

    public void displayDifferentFormats(){                                                          //main control method calls other methods in order
       // FormatTodayDateAsMM_dd_yyyy();
       // FormatTodayDateAsISO_8601();
       // FormatTodayDateAsMonth_dd_yyyy();
       // FormatTodayDateTimeAsE_MMM_dd_yyyy_HHmm_in_GMT();

       // System.out.println("\nChallenge");
       // FormatTodayDateTimeAsHHmm_on_dd_MMM_yyyy();
        System.out.println(TODAYDATETIME);
    }

    public void FormatTodayDateAsMM_dd_yyyy(){                                                      //displays date as sample: 01/01/2025

        dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        formatDate = TODAYDATETIME.format(dateFormatter);

        System.out.println(formatDate);
    }

    public void FormatTodayDateAsISO_8601(){                                                       //displays date as sample: 2025-01-01

        System.out.println(TODAYDATE);
    }

    public void FormatTodayDateAsMonth_dd_yyyy(){                                                  //displays date as sample: Jan 1, 2025

        dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        formatDate = TODAYDATE.format(dateFormatter);

        System.out.println(formatDate);

    }

    public void FormatTodayDateTimeAsE_MMM_dd_yyyy_HHmm_in_GMT(){                                  //display date and time as sample: Wed, Jan 1, 2025 10:01 AM

        dateFormatter = DateTimeFormatter.ofPattern("E, MMM dd, yyyy");
        formatDate = TODAYDATETIME.format(dateFormatter);

        timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        formatTime = TODAYTIMEGMT.format(timeFormatter);



        System.out.println(formatDate + "  " + formatTime);
    }

    public void FormatTodayDateTimeAsHHmm_on_dd_MMM_yyyy(){                                        //display time and date as sample: 10:01 AM on 01-Jan-2025

        dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        formatDate = TODAYDATETIME.format(dateFormatter);

        timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        formatTime = TODAYTIME.format(timeFormatter);

        System.out.println(formatTime + " on " + formatDate);
    }



}
