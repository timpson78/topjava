package ru.javawebinar.topjava.Exeptions;

public class MyNumberFormatException extends NumberFormatException {

    public MyNumberFormatException(String num) {
        super(" Не целочисленное значение: " + num);
    }

}
