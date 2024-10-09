import java.util.Random;
import java.util.HashSet;

public class GameLogic {
	
    private String secretNumber;
    
    public GameLogic() {
        secretNumber = createSecretNumber();
    }

    // Create a random 4-digit number with unique digits.
    private String createSecretNumber() {
        Random rand = new Random();
        HashSet<Integer> uniqueDigits = new HashSet<>();
        StringBuilder generatedNumber = new StringBuilder();
        while (generatedNumber.length() < 4) {
        	
        	 // Generate a digit between 0 and 9.
            int digit = rand.nextInt(10);
            
            // Ensure no duplicates.
            if (!uniqueDigits.contains(digit)) {
                uniqueDigits.add(digit);
                generatedNumber.append(digit);
            }
        }
        return generatedNumber.toString();
    }

    // Method to evaluate the guess: count exact matches (bulls) and partial matches (hits).
    public String evaluateGuess(String guess) {
        if (guess.length() != 4) {
            return "Your guess must be exactly 4 digits.";
        }
        
        int exactMatches = 0;
        int partialMatches = 0;

        // Compare the guess with the secret number
        for (int i = 0; i < 4; i++) {
            if (guess.charAt(i) == secretNumber.charAt(i)) {
                exactMatches++;
            } else if (secretNumber.contains(Character.toString(guess.charAt(i)))) {
                partialMatches++;
            }
        }
        return "Exact matches (Bulls): " + exactMatches + ", Partial matches (Hits): " + partialMatches;
    }

    // Method to retrieve the secret number (for testing or debugging)
    public String getSecretNumber() {
        return secretNumber;
    }
}
