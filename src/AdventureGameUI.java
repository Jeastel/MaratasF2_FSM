import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

enum GameState {
    START,
    TAXI,
    BUS,
    SHIP,
    TRICYCLE,
    END
}

public class AdventureGameUI extends JFrame {
    private GameState currentState = GameState.START;
    private JTextArea gameTextArea;
    private JButton choiceButton1;
    private JButton choiceButton2;

    public AdventureGameUI() {
        // Set up the frame
        setTitle("Adventure Game");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        gameTextArea = new JTextArea();
        gameTextArea.setEditable(false);
        gameTextArea.setLineWrap(true);
        gameTextArea.setWrapStyleWord(true);
        add(new JScrollPane(gameTextArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        choiceButton1 = new JButton("Choice 1");
        choiceButton2 = new JButton("Choice 2");

        buttonPanel.add(choiceButton1);
        buttonPanel.add(choiceButton2);

        add(buttonPanel, BorderLayout.SOUTH);

        updateGameState();

        choiceButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleChoice(1);
            }
        });

        choiceButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleChoice(2);
            }
        });
    }

    private void updateGameState() {
        switch (currentState) {
            case START:
                gameTextArea.setText("You are in Cebu and want to get home to Escalante City. How will you start your journey?");
                choiceButton1.setText("Take a taxi to the North Bus Terminal");
                choiceButton2.setText("Cancel the trip.");
                break;
            case TAXI:
                gameTextArea.setText("You are in the taxi, heading to the North Bus Terminal.");
                choiceButton1.setText("Ride the bus to the port");
                choiceButton2.setText("Explore the terminal.");
                break;
            case BUS:
                gameTextArea.setText("You are on the bus to the port.");
                choiceButton1.setText("Take a nap");
                choiceButton2.setText("Chat with the person next to you");
                break;
            case SHIP:
                gameTextArea.setText("You arrived at the port and boarded the ship.");
                choiceButton1.setText("Enjoy the view from the deck");
                choiceButton2.setText("Go to sleep in your cabin");
                break;
            case TRICYCLE:
                gameTextArea.setText("You took a tricycle home from the port. You're almost there!");
                choiceButton1.setText("Tell the driver about your day");
                choiceButton2.setText("Enjoy the ride in silence");
                break;
            case END:
                choiceButton1.setEnabled(false);
                choiceButton2.setEnabled(false);
                break;
        }
    }

    private void handleChoice(int choice) {
        switch (currentState) {
            case START:
                if (choice == 1) {
                    currentState = GameState.TAXI;
                } else {
                    gameTextArea.setText("Trip canceled. Game over.");
                    currentState = GameState.END;
                }
                break;
            case TAXI:
                if (choice == 1) {
                    currentState = GameState.BUS;
                } else {
                    gameTextArea.setText("You got lost in the terminal... Game over.");
                    currentState = GameState.END;
                }
                break;
            case BUS:
                if (choice == 1) {
                    gameTextArea.setText("You missed your stop... Game over.");
                    currentState = GameState.END;
                } else {
                    currentState = GameState.SHIP;
                }
                break;
            case SHIP:
                if (choice == 1) {
                    currentState = GameState.TRICYCLE;
                } else {
                    gameTextArea.setText("You missed your stop... Game over.");
                    currentState = GameState.END;
                }
                break;
            case TRICYCLE:
                if (choice == 1) {
                    gameTextArea.setText("You made it to MARATAS COMPOUND safely! Congratulations.");
                } else {
                    gameTextArea.setText("You reached home quietly... Game over.");
                }
                currentState = GameState.END;
                break;
        }
        updateGameState();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdventureGameUI gameUI = new AdventureGameUI();
            gameUI.setVisible(true);
        });
    }
}
