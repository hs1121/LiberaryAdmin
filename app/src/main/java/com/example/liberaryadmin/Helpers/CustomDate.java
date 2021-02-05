package com.example.liberaryadmin.Helpers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
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
    }    // convert CustomDate to string
    public CustomDate getExtendedDate(Integer month){
        CustomDate date=new CustomDate();
        date.day=day;
        date.month=Math.max((month+this.month)%13,1);
        date.year=year+(month+this.month>12?1:0);
        return date;
    }   //get date after m month .Used to calculate membership end date
    public static long getTime(String s){
        String myDate = s+" 00:00:01";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long timeinms=date.getTime();
        return timeinms;
    }       // Returns date in millisecond format .Used for difference in dates


    public static @NotNull CustomDate stringToDate(@NotNull String s){
        String[] parts = s.split("-");
        Integer day,month,year;
        day=Integer.parseInt(parts[0]);
        month=Integer.parseInt(parts[1]);
        year=Integer.parseInt(parts[2]);
        CustomDate date=new CustomDate(day,month,year);
        return date;
    }     // converts string to Custom date
    public static @NotNull Integer difference(@NotNull CustomDate date1, @NotNull CustomDate date2){
        return difference(date1.dateToString(),date2.dateToString());
    }  // difference between dates in CustomDate format
    public static @NotNull Integer difference(String date1, String date2){
        long days=(getTime(date1)-getTime(date2))/86400000;
        return (int)days;

    }       // difference between dates in String format
    @Contract(pure = true)
    public static  boolean isLess(@NotNull CustomDate date1, @NotNull CustomDate date2){
        if(date1.year>date2.year) {
            return false;
        }
        else if(date1.month > date2.month) {
                return false;
            }
        else if(date1.day>date2.day) {
            return false;
        }
        else {
            return true;
        }
    }    // compares dates at boolean level . Used to check validation of membership


}

