package com.bookstoreapi.utils;

import java.util.Random;

public class GeneratorUtils {

    public static String generateISBN() {
        StringBuilder isbnBuilder = new StringBuilder("978");
        for (int i = 0; i < 9; i++) {
            isbnBuilder.append(new Random().nextInt(10));
        }

        String isbnWithoutCheckDigit = isbnBuilder.toString();
        int checkDigit = calculateCheckDigit(isbnWithoutCheckDigit);

        return isbnWithoutCheckDigit + checkDigit;
    }

    private static int calculateCheckDigit(String isbnWithoutCheckDigit) {
        int sum = 0;
        for (int i = 0; i < isbnWithoutCheckDigit.length(); i++) {
            int digit = Character.getNumericValue(isbnWithoutCheckDigit.charAt(i));
            sum += (i % 2 == 0) ? digit : 3 * digit;
        }
        int modResult = sum % 10;
        return (modResult == 0) ? 0 : 10 - modResult;
    }


}
