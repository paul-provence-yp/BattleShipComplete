import java.util.ArrayList;
import java.util.Random;


public class BattleShip {
    char[][] spacesUser;
    ArrayList<Coordinates> shipCoordinates;
    UserInterface ui;
    SolutionsInterface sui;
    String board;
    Random random;
    int gameTurn;
    int rowChoice;
    int colChoice;
    int shipsRemaining;
    int numOfTurns;
    boolean gameWon;



    public BattleShip() {
      random = new Random();
      ui = new UserInterface(this);
      spacesUser = new char[6][6];
      shipCoordinates = new ArrayList<>();
      gameTurn = GameProperties.GET_ROW;
      rowChoice = -1;
      colChoice = -1;
      shipsRemaining = GameProperties.NUM_OF_SHIPS;
      numOfTurns = 0;
      gameWon = false;
      playGame();
      }

    public void playGame() {
        generateShips();
        createInitialBoard();
        displayBoard();
        printAllShipCoordinates();

        ui.updateInterfaceQuestionField("Welcome to Battleship!");

        sleep();

        ui.updateInterfaceQuestionField("Enter a row");

        while (!gameWon) {

            if (gameTurn == GameProperties.GET_ROW && (rowChoice < 0 || rowChoice >= GameProperties.BOARD_DIMENSIONS)) {
                ui.updateInterfaceQuestionField("Enter a row");
            } else if (gameTurn == GameProperties.GET_ROW && ( !(rowChoice < 0) && !(rowChoice >= GameProperties.BOARD_DIMENSIONS))) {
                  gameTurn = GameProperties.GET_COL;
            } else if (gameTurn == GameProperties.GET_COL && (colChoice < 0 || rowChoice >= GameProperties.BOARD_DIMENSIONS)) {
                ui.updateInterfaceQuestionField("Enter a column");
            } else if ((!(rowChoice < 0) && rowChoice < GameProperties.BOARD_DIMENSIONS) && (!(colChoice < 0) && colChoice < GameProperties.BOARD_DIMENSIONS)) {
                checkForHit();
                rowChoice = -1;
                colChoice = -1;
                gameTurn = GameProperties.GET_ROW;
            }

        } //end of while loop

        ui.updateInterfaceGameDisplay("You won in " + numOfTurns + " turns!");
        ui.updateInterfaceQuestionField("You won!");

    }


    public void generateShips() {
        int row = -1;
        int col = -1;

        for (int i = 0; i < GameProperties.NUM_OF_SHIPS; i++) {
            boolean added = false;

            while (!added) {
                row = random.nextInt(GameProperties.BOARD_DIMENSIONS);
                col = random.nextInt(GameProperties.BOARD_DIMENSIONS);

                Coordinates temp = new Coordinates(row, col);

                if (searchCoordinateList(temp) == -1) {
                    shipCoordinates.add(temp);
                    added = true;
                }

            }

        }

    }

    public void createInitialBoard() {

      for (int i = 0; i < spacesUser.length; i++) {
          for (int j = 0; j < spacesUser[0].length; j++) {
            spacesUser[i][j] = '?';
          }
      }

      String topBoard =  "_|0|1|2|3|4|5|\n";
      int rowNumber = 0;

      String theRest = "";
      for (int i = 0; i < spacesUser.length; i++) {
          theRest += rowNumber;
          for (int j = 0; j < spacesUser[0].length; j++) {
            theRest += "|" + spacesUser[i][j];
          }
          theRest += "|\n";
          rowNumber++;
      }

      board = topBoard + theRest;

    }

    public void refreshUserBoard(boolean hit) {
        if (hit) {
            spacesUser[rowChoice][colChoice] = 's';
        } else {
            spacesUser[rowChoice][colChoice] = '_';
        }

        String topBoard =  "_|0|1|2|3|4|5|\n";
        int rowNumber = 0;

        String theRest = "";
        for (int i = 0; i < spacesUser.length; i++) {
            theRest += rowNumber;
            for (int j = 0; j < spacesUser[0].length; j++) {
              theRest += "|" + spacesUser[i][j];
            }
            theRest += "|\n";
            rowNumber++;
        }

        board = topBoard + theRest;
    }

    public void displayBoard() {
        ui.updateInterfaceGameDisplay(board);
    }

    public int searchCoordinateList(Coordinates coordinate) {
        int index = -1;

        for (int i = 0; i < shipCoordinates.size(); i++) {

            if (Coordinates.compareTo(coordinate, shipCoordinates.get(i))) {
                index = i;
            }
        }


        return index;
    }

    public void printAllShipCoordinates() {
        String shipCoordinateList = "";

        for (int i = 0; i < shipCoordinates.size(); i++) {
            shipCoordinateList += "Ship " + i + " " + shipCoordinates.get(i).toString() +"\n";
        }
        sui = new SolutionsInterface();
        sui.updateSolutionsArea(shipCoordinateList);
    }

    public void setRowChoice(int rowChoice) {
        this.rowChoice = rowChoice;
    }

    public void setColChoice(int colChoice) {
        this.colChoice = colChoice;
    }

    public int getGameTurn() {
        return gameTurn;
    }

    public void checkForHit() {
        Coordinates temp = new Coordinates(rowChoice, colChoice);

        int index = searchCoordinateList(temp);

        if (index == -1) {
          refreshUserBoard(false);
          ui.updateInterfaceQuestionField("Miss!");
          sleep();
        } else {
          shipCoordinates.remove(index);
          shipsRemaining--;
          refreshUserBoard(true);
          ui.updateInterfaceQuestionField("Hit!");
          sleep();
        }
        displayBoard();

        if (shipsRemaining == 0) {
            gameWon = true;
        } else {
            ui.updateInterfaceQuestionField("Enter a row");
        }

        numOfTurns++;

    }

    public static void main(String[] args) {
        BattleShip bs = new BattleShip();
    }

    public void sleep() {
        try {
            // Pause for 3 seconds
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // Handle the exception
            e.printStackTrace();
      }
    }

}
