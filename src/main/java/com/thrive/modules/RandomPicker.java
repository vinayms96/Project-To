package com.thrive.modules;

import java.util.Random;

public class RandomPicker {
    static Random rand = new Random();
    public static String randomizer(int strLen) {
        String text = "";
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < strLen; i++) {
            text += str.charAt(rand.nextInt(62));
        }
        return text;
    }

    public static String generateEmail(int firstName, int domain){
        return randomizer(firstName) + "@" + randomizer(domain) + ".com";
    }

    public static int random_prod(int size){
        return rand.nextInt(size);
    }

}
