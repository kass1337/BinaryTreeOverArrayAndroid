package ru.avtf.rgr.comparator;

import ru.avtf.rgr.usertype.type.DateTimeClass;

import java.io.Serializable;

public class DateTimeComparator implements Comparator, Serializable {
    @Override
    public int compare(Object o1, Object o2) {
        int yearL = ((DateTimeClass) o1).getYear();
        int yearR = ((DateTimeClass) o2).getYear();
        if (yearL != yearR) {
            return yearL - yearR;
        }

        int monthL = ((DateTimeClass) o1).getMonth();
        int monthR = ((DateTimeClass) o2).getMonth();
        if (monthL != monthR) {
            return monthL - monthR;
        }

        int dayL = ((DateTimeClass) o1).getDay();
        int dayR = ((DateTimeClass) o2).getDay();

        if (dayL != dayR) {
            return dayL - dayR;
        }

        int hourL = ((DateTimeClass) o1).getHour();
        int hourR = ((DateTimeClass) o2).getHour();
        int minuteL = ((DateTimeClass) o1).getMinute();
        int minuteR = ((DateTimeClass) o2).getMinute();
        int secondL = ((DateTimeClass) o1).getSecond();
        int secondR = ((DateTimeClass) o2).getSecond();

        int timeL = hourL * 60 * 60 + minuteL * 60 + secondL;
        int timeR = hourR * 60 * 60 + minuteR * 60 + secondR;

        return timeL - timeR;
    }
}
