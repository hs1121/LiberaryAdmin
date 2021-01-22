package com.example.liberaryadmin;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDate{
    Integer day,month,year;
    Long timeInMs;

    public CustomDate() {
    }

    public CustomDate(Long timeInMs) {
        this.timeInMs = timeInMs;
        String dateInString =new SimpleDateFormat("dd-MM-yyyy").format(new Date(timeInMs));
        CustomDate date= stringToDate(dateInString);
        this.day=date.day;
        this.month=date.month;
        this.year=date.year;
    }

    public CustomDate(Integer day, Integer month, Integer year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String dateToString(){
        return day+"-"+month+"-"+year;
    }
    public CustomDate getExtendedDate(Integer month){
        CustomDate date=new CustomDate();
        date.day=day;
        date.month=Math.max((month+this.month)%13,1);
        date.year=year+(month+this.month>12?1:0);
        return date;
    }



    public static @NotNull CustomDate stringToDate(@NotNull String s){
        String[] parts = s.split("-");
        Integer day,month,year;
        day=Integer.parseInt(parts[0]);
        month=Integer.parseInt(parts[1]);
        year=Integer.parseInt(parts[2]);
        CustomDate date=new CustomDate(day,month,year);
        return date;
    }
    public static @NotNull Integer difference(@NotNull CustomDate date1, @NotNull CustomDate date2){
        return Math.abs(date1.year-date2.year)*12-Math.abs(date1.month-date2.month);
    }

    public static @NotNull Integer difference(String date1, String date2){
        return difference(stringToDate(date1),stringToDate(date2));

    }
    @Contract(pure = true)
    public static  boolean isLess(@NotNull CustomDate date1, @NotNull CustomDate date2){
        if(date1.year<date2.year) {
            return true;
        }
        else {
            if (date1.month < date2.month) {
                return true;
            }
        }
        return false;
    }


}

