
import java.util.*;
import java.awt.*;
import javax.swing.*;
/*
 * This class is used to draw a chess board with queens. Where the queens are
 * placed on the board is controlled by the instance variable: boolean
 * board[][]. The size of board is 8x8. Each element in board indicates if a
 * queen is there. For example, if board[4][5]==true, there is a queen at row 4
 * and column 5.
 * 
 * The value of this 2D array is passed in at the constructor. This class does
 * not decide which cells have queens.
 */
class JChessBoardPanel extends JPanel {

	// this variable is used to indicate which cell has a queen
	private boolean board[][];

	// the user should pass a 2D boolean array to tell
	// which cells have queens
	public JChessBoardPanel(boolean board[][]) {
		super();
		this.board = board;
	}

	public void drawChessBoard(Graphics g) {
		g.setColor(Color.BLACK);

		// get the cell width/height
		int height = getHeight(), width = getWidth();
		int cellHeight = height / 8;
		int cellWidth = width / 8;

		/*
		 * loop to draw all chess board squares
		 */
		int i = 0, j = 0;
		while (i < 8) { // row
			while (j < 8) {
				// Column
				g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
				j += 2;
			}

			i++;
			// accounts for shaded cell shifting
			if (i % 2 == 0) {
				j = 0;
			} else {
				j = 1;
			}

		}

	}

	public void drawQueens(Graphics g) {
		g.setColor(Color.RED);

		/* insert your code here TODO */

		// get the cell width/height
		int height = getHeight(), width = getWidth();
		int queenHeight = height / 16;
		int queenWidth = width / 16;
		int cellHeight = height / 8;
		int cellWidth = width / 8;
		/*
		 * loop to draw all queens = ovals Ovals should be smaller than squares
		 * and centered on the square
		 */
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j]) {
					g.fillOval(j * cellWidth + queenHeight / 2, i * cellHeight
							+ queenWidth / 2, queenWidth, queenHeight);
				}
			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		drawChessBoard(g);
		drawQueens(g);
	}
}