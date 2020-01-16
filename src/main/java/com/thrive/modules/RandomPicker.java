package com.thrive.modules;

import java.util.Random;

public class RandomPicker {
    Random r = new Random();
    public String randomizer(int strLen) {
        String text = "";
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < strLen; i++) {
            text += str.charAt(r.nextInt(62));
        }
        return text;
    }

    public String generateEmail(int firstName, int domain){
        return randomizer(firstName) + "@" + randomizer(domain) + ".com";
    }

}
