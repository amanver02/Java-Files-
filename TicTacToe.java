
import java.util.Scanner;

 public class TicTacToe {
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    // Initialize the board with empty spaces
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print the current board
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Check if the current player has won the game
    public boolean checkWin() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    // Check if any row has all the same symbols
    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
                return true;
            }
        }
        return false;
    }

    // Check if any column has all the same symbols
    private boolean checkColumns() {
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != '-') {
                return true;
            }
        }
        return false;
    }

    // Check if any diagonal has all the same symbols
    private boolean checkDiagonals() {
        return ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-')
                || (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-'));
    }

    // Check if the board is full
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // Switch player turns
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Place the current player's symbol at the specified position
    public boolean placeSymbol(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
            board[row][col] = currentPlayer;
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        boolean gameEnded = false;

        while (!gameEnded) {
            System.out.println("Current board:");
            game.printBoard();
            System.out.println("Player " + game.currentPlayer + ", enter your move (row [0-2] column [0-2]):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (game.placeSymbol(row, col)) {
                if (game.checkWin()) {
                    System.out.println("Player " + game.currentPlayer + " wins!");
                    gameEnded = true;
                } else if (game.isBoardFull()) {
                    System.out.println("It's a draw!");
                    gameEnded = true;
                } else {
                    game.switchPlayer();
                }
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }
        scanner.close();
    }
}
