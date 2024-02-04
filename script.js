function play(playerMove) {
    const options = ["Rock", "Paper", "Scissors"];
    const computerMove = options[Math.floor(Math.random() * 3)];

    const result = determineWinner(playerMove, computerMove);
    displayResult(playerMove, computerMove, result);
}

function determineWinner(playerMove, computerMove) {
    if (playerMove === computerMove) {
        return "It's a tie!";
    } else if (
        (playerMove === "Rock" && computerMove === "Scissors") ||
        (playerMove === "Paper" && computerMove === "Rock") ||
        (playerMove === "Scissors" && computerMove === "Paper")
    ) {
        return "You win!";
    } else {
        return "Computer wins!";
    }
}

function displayResult(playerMove, computerMove, result) {
    const resultElement = document.getElementById("result");
    resultElement.innerHTML = `Your move: ${playerMove}<br>Computer's move: ${computerMove}<br>Result: ${result}`;
}
