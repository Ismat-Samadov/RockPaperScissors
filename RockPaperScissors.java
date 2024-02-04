import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] options = {"Rock", "Paper", "Scissors"};

        while (true) {
            System.out.println("Choose your move (Rock, Paper, Scissors) or type 'exit' to end the game:");
            String playerMove = scanner.nextLine();

            if (playerMove.equalsIgnoreCase("exit")) {
                System.out.println("Game ended. Thanks for playing!");
                break;
            }

            if (!isValidMove(playerMove)) {
                System.out.println("Invalid move. Please choose Rock, Paper, or Scissors.");
                continue;
            }

            int computerIndex = random.nextInt(3);
            String computerMove = options[computerIndex];

            System.out.println("Computer chose: " + computerMove);

            String result = determineWinner(playerMove, computerMove);
            System.out.println(result);
        }

        scanner.close();
    }

    private static boolean isValidMove(String move) {
        return move.equalsIgnoreCase("Rock") || move.equalsIgnoreCase("Paper") || move.equalsIgnoreCase("Scissors");
    }

    private static String determineWinner(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "It's a tie!";
        } else if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
                   (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
                   (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "You win!";
        } else {
            return "Computer wins!";
        }
    }
}
