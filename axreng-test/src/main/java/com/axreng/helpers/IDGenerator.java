package com.axreng.helpers;

import java.util.Random;

public class IDGenerator {
    private static final String CHAR_SET = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static final int ID_LENGTH = 8;

    public static String generateId() {
        Random random = new Random();
        StringBuilder id = new StringBuilder(ID_LENGTH);

        for (int i = 0; i < ID_LENGTH; i++) {
            int index = random.nextInt(CHAR_SET.length());
            id.append(CHAR_SET.charAt(index));
        }

        return id.toString();
    }
}