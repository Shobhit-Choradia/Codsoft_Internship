import java.util.Random;
import java.util.Scanner;

public class NumberGuessGame //new class NumberGuessGame
{  
    final int secretNumber;
    final int minRange;
    final int maxRange;
    final int maxGuesses;
    private int currentGuess;
    private int numGuesses;
    private boolean gameOver;

    public NumberGuessGame(int minRange, int maxRange) //2 parameter constructor 
    {
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.maxGuesses = calculateMaxGuesses();
        this.secretNumber = generateSecretNumber();
        this.currentGuess = 0;
        this.numGuesses = 0;
        this.gameOver = false;
    }

    private int generateSecretNumber() // method to genereate a random number
    {
        Random random = new Random();
        return random.nextInt(maxRange - minRange + 1) + minRange;
    }

    private int calculateMaxGuesses() //method to calculate maximum guesses possible for that range
    {
        int range = maxRange - minRange + 1;
        if (range > 10000) {
            return 20;
        } else if (range > 1000) {
            return 15;
        } else {
            return 10;
        }
    }

    public void makeGuess(int guess) //method to check if guess maken is right
    {
        if (!gameOver)
        {
            currentGuess = guess;
            numGuesses++;

            if (guess == secretNumber)
            {
                System.out.println("\n\nCONGRATULATIONS! You have guessed the right number.");
                System.out.println("You took " + numGuesses + " guesses to win.");
                gameOver = true;
            }
            else if (guess < secretNumber)
            {
                System.out.println("\n" + guess + " is lower than the number.");
            }
            else
            {
                System.out.println("\n" + guess + " is higher than the number.");
            }
            if (numGuesses >= maxGuesses)
            {
                System.out.println("Sorry, you couldn't guess the number in " + maxGuesses + " guesses. You lose.");
                System.out.println("The number was " + secretNumber + ".");
                gameOver = true;
            }
        }
        else
        {
            System.out.println("The game is already over. Please start a new game.");
        }
    }

    public boolean isGameOver() //method to say game over
    {
        return gameOver;
    }

    public static void main(String[] args) //main method of game
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("HELLO");
        System.out.println("WELCOME TO THE NUMBER GUESS MINI GAME");

        System.out.print("\n\nPlease type the start range for the number: ");
        int minRange = scanner.nextInt();

        System.out.print("Please type the end range for the number: ");
        int maxRange = scanner.nextInt();

        NumberGuessGame game = new NumberGuessGame(minRange, maxRange);

        while (!game.isGameOver())
        {
            System.out.print("Your guess: ");
            int guess = scanner.nextInt();
            game.makeGuess(guess);
        }

        System.out.print("\n\nWANNA PLAY AGAIN? (yes/no): ");
        String playAgain = scanner.next();

        if (playAgain.equalsIgnoreCase("yes"))
        {
            System.out.println("Restarting game...");
            main(null); // Restart the game
        }
        else
        {
            System.out.println("Exiting game. Goodbye!");
        }

        scanner.close();
    }
}
