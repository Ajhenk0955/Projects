
import java.util.*;
import java.awt.*;
import javax.swing.*;
/*
 * This JFrame-based class is the container of JChessBoardPanel. When the user
 * creates a JEightQueenFrame object, he/she is supposed to pass a 2D (8x8)
 * boolean array, board, to tell where the queens should be placed. The size of
 * board is 8x8. Each element in board indicates if a queen is there. For
 * example, if board[4][5]==true, there is a queen at row 4 and column 5.
 */
class JEightQueenFramePart extends JFrame {
	private boolean board[][];
	private JChessBoardPanel chessBoard;

	public JEightQueenFramePart(boolean board[][]) {

		super();

		this.board = board;

		// setting the layout
		getContentPane().setLayout(new BorderLayout());

		// adding the ChessBoard
		getContentPane().add(new JChessBoardPanel(board), BorderLayout.CENTER);

		// other settings
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(590, 610);
		setResizable(false);
		setVisible(true);
	}

	public static boolean trial(boolean board[][], int n) {

		/* add code here TODO */

		/* see trial() code on book pg. 154 */
		for (int i = 0; i < 8; i++) {
			board[n][i] = true;
			if (n == 7 && noConflict(board, n, i))
				return true;
			// recursion happens here
			if (n < 7 && noConflict(board, n, i) && trial(board, n + 1))
				return true;
			board[n][i] = false;
		}
		return false;
	}

	private static boolean noConflict(boolean[][] board, int n, int i) {
		// checks for diagonal conflicts
		int left = i - 1;
		int right = i + 1;
		while (n != 0) {
			n--;

			if (board[n][i] == true) {
				return false;
			}
			if (left >= 0) {
				if (board[n][left] == true) {
					return false;
				}
				left--;
			}

			if (right < 8) {
				if (board[n][right] == true) {
					return false;
				}
				right++;
			}
		}
		return true;
	}

	public static void main(String args[]) {

		/* add code to declare the board and frame TODO */
		boolean[][] board = new boolean[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = false;
			}
		}

		trial(board, 0);// see if we can place a queen at row 0
		JEightQueenFramePart queenFrame = new JEightQueenFramePart(board);

	}
}