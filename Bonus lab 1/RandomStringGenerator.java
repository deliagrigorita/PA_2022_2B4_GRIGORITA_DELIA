package com.lab_ag_1_homework;

public class RandomStringGenerator {
    public RandomStringGenerator() {}
    public String Generate(char[] characters, int wordLen)
    {
        StringBuilder generatedString = new StringBuilder();

        for (int it = 0; it < wordLen; it++)
        {
            int charPosition = (int)(Math.random() * characters.length);
            generatedString.append(characters[charPosition]);
        }

        return generatedString.toString();
    }
}
