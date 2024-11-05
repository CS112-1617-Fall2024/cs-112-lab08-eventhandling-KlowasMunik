package edu.miracosta.cs112.lab08eventhandling;
public class Main extends Application {

    // CONSTANTS
    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matemáticas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9)
    };
    private static final int TOTAL_CARDS = LOTERIA_CARDS.length;

    // CLASS-LEVEL VARIABLES
    private ImageView cardImageView;
    private Label messageLabel;
    private Button drawCardButton;
    private ProgressBar gameProgressBar;
    private boolean[] drawnCards = new boolean[TOTAL_CARDS]; // Track drawn cards
    private int cardsDrawn = 0;
@Override
    public void start(Stage primaryStage) {

        // Initialize GUI components
        Label titleLabel = new Label("EChALE STEM Loteria");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        cardImageView = new ImageView(); // Holds the card images
        cardImageView.setFitWidth(300);
        cardImageView.setFitHeight(300);

        messageLabel = new Label("Click 'Draw Card' to begin!");
        messageLabel.setStyle("-fx-font-size: 14px;");

        drawCardButton = new Button("Draw Card");
        drawCardButton.setOnAction(event -> handleDrawCard());

        gameProgressBar = new ProgressBar(0.0); // Hacker Challenge progress bar
        gameProgressBar.setPrefWidth(300);

        // VBox Layout
        VBox root = new VBox(10, titleLabel, cardImageView, messageLabel, drawCardButton, gameProgressBar);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20; -fx-background-color: #f4f4f4;");

        // Set up the scene and stage
        Scene scene = new Scene(root, 350, 500);
        primaryStage.setTitle("Loteria + Event Handling");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     
Handles the logic when the "Draw Card" button is clicked.*/
private void handleDrawCard() {
        if (cardsDrawn == TOTAL_CARDS) {
            endGame(); // End the game if all cards are drawn
            return;
        }

        int randomIndex = getRandomCardIndex();
        LoteriaCard selectedCard = LOTERIA_CARDS[randomIndex];

        // Update the ImageView with the selected card image
        cardImageView.setImage(selectedCard.getImage());

        // Mark the card as drawn
        drawnCards[randomIndex] = true;
        cardsDrawn++;

        // Update progress bar
        gameProgressBar.setProgress((double) cardsDrawn / TOTAL_CARDS);

        // Update message label
        messageLabel.setText("You drew: " + selectedCard.getCardName());

        // Check if the game is over
        if (cardsDrawn == TOTAL_CARDS) {
            endGame();
        }
    }

    /
     
Returns a random index for a card that hasn't been drawn yet.*/
  private int getRandomCardIndex() {
      Random rand = new Random();
      int index;
      do {
          index = rand.nextInt(TOTAL_CARDS);} while (drawnCards[index]); // Ensure the card hasn't been drawn yet
      return index;}

    /
     
Ends the game by showing the final message and disabling the button.*/
  private void endGame() {
      messageLabel.setText("GAME OVER. No more cards! Exit and restart to play again.");
      cardImageView.setImage(new Image("file:src/main/resources/echale_logo.png")); // EChALE logo
      drawCardButton.setDisable(true);
      gameProgressBar.setStyle("-fx-accent: red;"); // Change progress bar color to red}

    public static void main(String[] args) {
        launch(args);
    }
}
