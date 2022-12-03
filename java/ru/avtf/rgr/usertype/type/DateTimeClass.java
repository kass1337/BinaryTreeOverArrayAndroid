package ru.avtf.rgr.usertype.type;
import java.io.Serializable;
import java.lang.Exception;

// TODO добавить проверки, чтобы
// TODO 1 <= day <= 31 done
// TODO 1 <= month <= 12 done
// TODO 0 <= year (до н.э. не считаем, а будущее - да) done
// TODO 0 <= hour <= 23 done
// TODO 0 <= minute <= 59 done
// TODO 0 <= second <= 59 done
// TODO Проверка на високосный год done
// TODO Проверка на корректное число в месяце done
// TODO Возможно придётся поправить toString() для вывода, чтобы: done
// TODO Было не так 1/1/2021 0:0:1
// TODO А было вот так 01/01/2021 00:00:00 (возникнут ли проблемы с парсингом?)
// Принял волевое решение оставить день и месяц как есть, но нули добавить для часов, минут и секунд, парсинг (не)сделал с учетом этого




public class DateTimeClass implements Serializable{
    private static int changeYear = 1582; 
    private int day;
    private int month;
    private int year;

    private int hour;
    private int minute;
    private int second;

    public DateTimeClass(int day, int month, int year, int hour, int minute, int second) throws Exception {
        try {
        setDay(day);
        setMonth(month);
        setYear(year);
        setHour(hour);
        setMinute(minute);
        setSecond(second);
        }
        catch(Exception ex) {
            System.out.println("Bad Date");
        }
        if (!isDayValid()) throw new Exception("Bad date");
    }

    public DateTimeClass() {
        try{
            setDay(15);
            setMonth(11);
            setYear(2022);
            setHour(22);
            setMinute(52);
            setSecond(24);

        }
        catch(Exception ex) {
            System.out.println("Bad Date");
        }
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) throws Exception {
        if(day < 1 || day > 31) {
            throw new Exception("Bad day");
        }
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) throws Exception {
        if(month < 1 || month > 12) {
            throw new Exception("Bad month");
        }
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) throws Exception {
        if(year <= 0) {
            throw new Exception("Bad year");
        }
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) throws Exception {
        if(hour < 0 || hour > 23) {
            throw new Exception("Bad hour");
        }
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) throws Exception {
        if(minute < 0 || minute > 59) {
            throw new Exception("Bad minute");
        }
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) throws Exception {
        if(second < 0 || second > 59) {
            throw new Exception("Bad second");
        }
        this.second = second;
    }
    public boolean isLeapYear() {
        if (this.year > changeYear) {
            return this.year % 4 == 0 && (this.year % 100 != 0 || this.year % 400 == 0);
        }
    
        return this.year % 4 == 0;
    }
    private boolean isDayValid() {
        if ((this.month == 4 || this.month == 6 || this.month == 9 || this.month == 11) && this.day == 31) {
            return false;
        }
        else if(this.month == 2 && isLeapYear() && this.day > 29) {
            return false;
        }
        else if(this.month == 2 && this.day > 28) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public String toString() {
        String total = String.valueOf(day) + "/" +
        String.valueOf(month) + "/" +
        String.valueOf(year) + " ";
        if(hour < 10) total += "0";
        total += String.valueOf(hour);
        total += ":";
        if(minute < 10) total += "0";
        total += String.valueOf(minute);
        total += ":";
        if(second < 9) total += "0";
        total += String.valueOf(second);
        return total;
    }
}
