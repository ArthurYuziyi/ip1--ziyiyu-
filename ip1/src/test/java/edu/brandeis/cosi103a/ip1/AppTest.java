package edu.brandeis.cosi103a.ip1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

class AppTest {

    @Test
    void testRollDie() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int roll = App.rollDie(random);
            assertTrue(roll >= 1 && roll <= 6, "Die roll should be between 1 and 6");
        }
    }

    @Test
    void testHandleRerolls() {
        String input = "yes\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int initialRoll = 3;
        int finalRoll = App.handleRerolls(scanner, random, initialRoll, "Player 1");
        assertTrue(finalRoll >= 1 && finalRoll <= 6, "Final roll should be between 1 and 6");
    }

    @Test
    void testPlayTurn() {
        String input = "no\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int score = App.playTurn(scanner, random, "Player 1");
        assertTrue(score >= 1 && score <= 6, "Score should be between 1 and 6");
    }

    @Test
    void testDisplayResults() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        App.displayResults(50, 40);
        String output = outputStream.toString();
        assertTrue(output.contains("Player 1 Wins!"), "Output should indicate Player 1 wins");

        outputStream.reset();
        App.displayResults(40, 50);
        output = outputStream.toString();
        assertTrue(output.contains("Player 2 Wins!"), "Output should indicate Player 2 wins");

        outputStream.reset();
        App.displayResults(50, 50);
        output = outputStream.toString();
        assertTrue(output.contains("It's a Tie!"), "Output should indicate a tie");
    }
}