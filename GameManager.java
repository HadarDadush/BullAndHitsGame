import javax.swing.JOptionPane;
import java.util.HashSet;

public class GameManager {
    public static void main(String[] args) {
        // Welcome message
        JOptionPane.showMessageDialog(null, "Welcome to Bulls and Hits Game!\n\n"
                + "The goal is to guess a 4-digit number with unique digits.\n"
                + "For each guess, you'll receive feedback:\n"
                + "- Bulls: Correct digits in the correct position\n"
                + "- Hits: Correct digits but in the wrong position\n\n"
                + "Good luck!");

        boolean playAgain = true;

        while (playAgain) {
            GameLogic bullAndHitsGame = new GameLogic();
            int attemptCount = 0;
            StringBuilder previousAttempts = new StringBuilder();
            boolean guessedCorrectly = false;
            
            while (!guessedCorrectly) {
                String userInput = JOptionPane.showInputDialog("Please enter your 4-digit guess (with unique digits):\n- To exit the game, type 'exit'");

                // Check if the user wants to exit
                if (userInput != null && userInput.equalsIgnoreCase("exit")) {
                    JOptionPane.showMessageDialog(null, "Thank you for playing!");
                    return; // Exit the program
                }

                // Validate user input
                if (userInput == null || !isInputValid(userInput)) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter exactly 4 unique digits.");
                    continue;
                }
                
                attemptCount++;
                String feedback = bullAndHitsGame.evaluateGuess(userInput);
                previousAttempts.append("Guess: ").append(userInput).append(" - ").append(feedback).append("\n");
                JOptionPane.showMessageDialog(null, previousAttempts.toString());

                // Check if the user guessed the number
                if (userInput.equals(bullAndHitsGame.getSecretNumber())) {
                    JOptionPane.showMessageDialog(null, "Congratulations! You've guessed the number in " + attemptCount + " attempts.");
                    guessedCorrectly = true;
                }
            }

            // Ask the user if they want to play again.
            int response = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
            playAgain = (response == JOptionPane.YES_OPTION);
        }
        JOptionPane.showMessageDialog(null, "Thank you for playing!");
    }

    // Validate user input
    private static boolean isInputValid(String input) {
        if (input.length() != 4) return false;

        HashSet<Character> uniqueDigitsSet = new HashSet<>();
        for (char digit : input.toCharArray()) {
            if (!Character.isDigit(digit) || !uniqueDigitsSet.add(digit)) {
                return false;
            }
        }
        return true;
    }
}
