package com.bookmark.domain.helper;

public enum ConversionHelper {
    CONVERSION_HELPER;

    private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private char[] allowedCharacters = allowedString.toCharArray();
    private int base = allowedCharacters.length;

    public String encode(Long id) {
        StringBuilder encodedString = new StringBuilder();
        if(id == 0) {
            return String.valueOf(allowedCharacters[0]);
        }
        while (id > 0) {
            encodedString.append(allowedCharacters[(int) (id % base)]);
            id = id / base;
        }
        return encodedString.reverse().toString();
    }


    public Long decode(String shortString) {
        char[] characters = shortString.toCharArray();
        int length = characters.length;
        long decoded = 0;
        //counter is used to avoid reversing input string
        int counter = 1;
        for (int i = 0; i < length; i++) {
            decoded += allowedString.indexOf(characters[i]) * Math.pow(base, length - counter);
            counter++;
        }
        return decoded;
    }
}
