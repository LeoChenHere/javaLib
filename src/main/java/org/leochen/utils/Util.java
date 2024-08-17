package org.leochen.utils;

public class Util {

    public static void simpleSleep(long millis){
        System.out.println("This is simple Sleep, thread will Sleep for " + millis + " milliseconds");
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
