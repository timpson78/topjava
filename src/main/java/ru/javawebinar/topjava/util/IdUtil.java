package ru.javawebinar.topjava.util;

import java.util.UUID;

public class IdUtil {
    public static String generateID(){
        return UUID.randomUUID().toString();
    };
}
