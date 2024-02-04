import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class RockPaperScissorsGUI extends Application {

    private String[] options = {"Rock", "Paper", "Scissors"};
    private Random random = new Random();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Rock Paper Scissors");

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Button rockButton = new Button("Rock");
        Button paperButton = new Button("Paper");
        Button scissorsButton = new Button("Scissors");

        rockButton.setOnAction(e -> play("Rock"));
        paperButton.setOnAction(e -> play("Paper"));
        scissorsButton.setOnAction(e -> play("Scissors"));

        layout.getChildren().addAll(rockButton, paperButton, scissorsButton);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void play(String playerMove) {
        int computerIndex = random.nextInt(3);
        String computerMove = options[computerIndex];

        String result = determineWinner(playerMove, computerMove);

        displayResult(playerMove, computerMove, result);
    }

    private void displayResult(String playerMove, String computerMove, String result) {
        Stage resultStage = new Stage();
        resultStage.setTitle("Result");

        VBox resultLayout = new VBox(10);
        resultLayout.setAlignment(Pos.CENTER);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> resultStage.close());

        resultLayout.getChildren().addAll(
                javafx.scene.control.LabelBuilder.create()
                        .text("Your move: " + playerMove + "\nComputer's move: " + computerMove + "\nResult: " + result)
                        .build(),
                closeButton
        );

        Scene resultScene = new Scene(resultLayout, 250, 150);
        resultStage.setScene(resultScene);
        resultStage.show();
    }

    private String determineWinner(String playerMove, String computerMove) {
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
