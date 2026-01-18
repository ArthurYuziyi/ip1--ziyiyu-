package edu.brandeis.cosi103a.ip1;

import java.util.Random;
import java.util.Scanner;

public class App 
{
    private static final int TURNS = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int player1Score = 0;
        int player2Score = 0;

        System.out.println("Welcome to the Dice Game!");

        for (int turn = 1; turn <= TURNS; turn++) {
            System.out.println("\nTurn " + turn + " for Player 1:");
            player1Score += playTurn(scanner, random, "Player 1");

            System.out.println("\nTurn " + turn + " for Player 2:");
            player2Score += playTurn(scanner, random, "Player 2");
        }

        displayResults(player1Score, player2Score);

        scanner.close();
    }

    public static int playTurn(Scanner scanner, Random random, String playerName) {
        int roll = rollDie(random);
        System.out.println(playerName + " rolled a " + roll);

        roll = handleRerolls(scanner, random, roll, playerName);

        System.out.println(playerName + "'s final roll for this turn is: " + roll);
        return roll;
    }

    public static int handleRerolls(Scanner scanner, Random random, int roll, String playerName) {
        for (int rerollCount = 0; rerollCount < 2; rerollCount++) {
            System.out.print(playerName + ", do you want to re-roll? (yes/no): ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("no")) {
                break;
            } else if (choice.equals("yes")) {
                roll = rollDie(random);
                System.out.println(playerName + " re-rolled and got a " + roll);
            } else {
                System.out.println("Invalid input. Please type 'yes' or 'no'.");
                rerollCount--;
            }
        }
        return roll;
    }

    public static int rollDie(Random random) {
        return random.nextInt(6) + 1;
    }

    public static void displayResults(int player1Score, int player2Score) {
        System.out.println("\nGame Over!");
        System.out.println("Player 1's Total Score: " + player1Score);
        System.out.println("Player 2's Total Score: " + player2Score);

        if (player1Score > player2Score) {
            System.out.println("Player 1 Wins!");
        } else if (player2Score > player1Score) {
            System.out.println("Player 2 Wins!");
        } else {
            System.out.println("It's a Tie!");
        }
    }
}