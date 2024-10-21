import java.util.Scanner;

public class AdventureGame {
    private static int currentState = 0; // 0 for START

    private static String[][] options = {
            {"1: Take a taxi to the North Bus Terminal (30 min)", "2: Cancel the trip."},
            {"1: Ride the bus to the port (3 hours).", "2: Explore the terminal."},
            {"1: Take a nap.", "2: Chat with the person next to you."},
            {"1: Enjoy the view from the deck.", "2: Go to sleep in your cabin."},
            {"1: Tell the driver about your day.", "2: Use phone all the time."},
            {"You made it home safely!", "Game over."}
    };

    private static int[][] transitions = {
            {1, 5}, // From START (0), choice 1 goes to TAXI (1), choice 2 goes to GAME OVER (5)
            {2, 5}, // From TAXI (1), choice 1 goes to BUS (2), choice 2 goes to GAME OVER (5)
            {5, 3}, // From BUS (2), choice 1 goes to GAME OVER (5), choice 2 goes to SHIP (3)
            {4, 5}, // From SHIP (3), choice 1 goes to TRICYCLE (4), choice 2 goes to GAME OVER (5)
            {6, 5}  // From TRICYCLE (4), choice 1 goes to WIN (6), choice 2 goes to GAME OVER (5)
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Adventure Game To Escalante City!");

        while (currentState != 5) {
            printCurrentState();
            handleUserInput(scanner);
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    private static void printCurrentState() {
        switch (currentState) {
            case 0:
                System.out.println("You are in Cebu and want to get home to Escalante City. How will you start your journey?");
                break;
            case 1:
                System.out.println("You are in the taxi, heading to the North Bus Terminal.");
                break;
            case 2:
                System.out.println("You are on the bus to the port.");
                break;
            case 3:
                System.out.println("You arrived at the port and boarded the ship.");
                break;
            case 4:
                System.out.println("You took a tricycle home from the port.");
                break;
            case 6:
                System.out.println("You made it to MARATAS COMPOUND safely! Congratulations.");
                break;
            default:
                break;
        }

        for (String option : options[currentState]) {
            System.out.println(option);
        }
    }

    private static void handleUserInput(Scanner scanner) {
        int choice = scanner.nextInt();

        if (choice < 1 || choice > 2) {
            System.out.println("Invalid choice, please try again.");
            return;
        }

        int nextState = transitions[currentState][choice - 1];

        if (nextState == 5) {
            switch (currentState) {
                case 0:
                    System.out.println("Trip canceled. Game over.");
                    break;
                case 1:
                    System.out.println("You got lost in the terminal... Game over.");
                    break;
                case 2:
                    System.out.println("You missed your stop... Game over.");
                    break;
                case 3:
                    System.out.println("You missed your stop... Game over.");
                    break;
                case 4:
                    System.out.println("You missed your house... Game over.");
                    break;
            }
        }

        currentState = nextState;
    }
}
