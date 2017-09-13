package org.example.james.guessmaster;

/**
 * Created by James on 2/6/2016.
 */
/*
THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS. James Martin
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GuessingGame {

    List<Integer> possibleGuesses = new ArrayList<>();
    Random guess = new Random();
    int counter;
    int compGuess = 0;

    public void GuessingGame() {

        counter = 0;

        for (int i = 1000; i <= 9999; i++) {
            possibleGuesses.add(i);
        }

    }

    public int myGuessIs() {

        if(possibleGuesses.size() < 1) {
            return -1;
        }

        int rIndex = guess.nextInt(possibleGuesses.size());
        compGuess =possibleGuesses.get(rIndex);
        counter++;
        return possibleGuesses.get(rIndex);
    }

    public int totalNumGuesses() {

        return counter;
    }

    public void updateMyGuess(int nMatches) {

        List<Integer> updatedGuesses = new ArrayList<>();

        for (int i = 0; i < possibleGuesses.size(); i++) {

            int match = 0;

            if (compGuess/1000%10 == possibleGuesses.get(i)/1000%10) {
                match++;
            }

            if (compGuess/100%10 == possibleGuesses.get(i)/100%10) {
                match++;
            }

            if (compGuess/10%10 == possibleGuesses.get(i)/10%10) {
                match++;
            }

            if (compGuess%10 == possibleGuesses.get(i)%10) {
                match++;
            }

            if (match == nMatches) {
                updatedGuesses.add(possibleGuesses.get(i));
            }
        }possibleGuesses = updatedGuesses;
    }
}

