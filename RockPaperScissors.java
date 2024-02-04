package rockpaperscissors;

import java.io.*;
import java.util.*;

public class RockPaperScissors {

    private static final String ROCK = "rock";
    private static final String PAPER = "paper";
    private static final String SCISSORS = "scissors";
    private static final String LIZARD = "lizard";
    private static final String SPOCK = "spock";

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String username = scanner.nextLine();
        System.out.println("Hello, " + username);

        int userScore = readUserScore(username);

        System.out.print("Enter options (comma-separated, or press Enter for default): ");
        String optionsInput = scanner.nextLine();
        List<String> gameOptions = Arrays.asList(optionsInput.isEmpty() ? new String[]{ROCK, PAPER, SCISSORS} : optionsInput.split(","));

        System.out.println("Okay, let's start!");

        while (true) {
            System.out.print("> ");
            String userMove = scanner.nextLine();

            if ("!exit".equals(userMove)) {
                System.out.println("Bye!");
                break;
            } else if ("!rating".equals(userMove)) {
                System.out.println("Your rating: " + userScore);
            } else if (gameOptions.contains(userMove)) {
                String computerMove = getRandomMove(gameOptions);
                String result = determineResult(userMove, computerMove);
                int roundScore = calculateScore(result);
                userScore += roundScore;
                System.out.println(result);
            } else {
                System.out.println("Invalid input. Please enter a valid move or !exit to end the game.");
            }
        }

        saveUserScore(username, userScore);

        scanner.close();
    }
    
    private static int calculateScore(String result) {
        if (result.startsWith("Draw")) {
            return 50;
        } else if (result.startsWith("Win")) {
            return 100;
        } else {
            return 0;
        }
    }
    private static int readUserScore(String username) {
        try (Scanner fileScanner = new Scanner(new File("rating.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\s+");
                if (parts.length == 2 && parts[0].equals(username)) {
                    return Integer.parseInt(parts[1]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
    private static void saveUserScore(String username, int score) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("rating.txt", true))) {
            writer.println(username + " " + score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String getRandomMove(List<String> options) {
        Random random = new Random();
        return options.get(random.nextInt(options.size()));
    }
    private static String determineResult(String userMove, String computerMove) {
        List<String> winsWithRock = Arrays.asList(LIZARD, SPOCK);

        if (userMove.equals(computerMove)) {
            return "Draw: There is a draw (" + computerMove + ")";
        } else if (
                (userMove.equals(ROCK) && winsWithRock.contains(computerMove)) ||
                        (userMove.equals(PAPER) && (computerMove.equals(ROCK) || computerMove.equals(SPOCK))) ||
                        (userMove.equals(SCISSORS) && (computerMove.equals(PAPER) || computerMove.equals(LIZARD))) ||
                        (userMove.equals(LIZARD) && (computerMove.equals(SPOCK) || computerMove.equals(PAPER))) ||
                        (userMove.equals(SPOCK) && (computerMove.equals(SCISSORS) || computerMove.equals(ROCK)))
        ) {
            return "Win: Well done. The computer chose " + computerMove + " and failed";
        } else {
            return "Loss: Sorry, but the computer chose " + computerMove;
        }
    }

}
