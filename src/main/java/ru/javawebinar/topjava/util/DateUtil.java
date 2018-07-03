package ru.javawebinar.topjava.util;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public   class DateUtil {
    //DateTimeFormatter formatter=DateTimeFormatter.ofPattern("");
    public static DateTimeFormatter formatter=DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static LocalDateTime DateTimeFormat(LocalDateTime ld){
        return LocalDateTime.parse(ld.format(formatter),formatter);
    }

    public static LocalDateTime StringDateTimeFormat(String s){
        return LocalDateTime.parse(s,formatter);
    }
}
