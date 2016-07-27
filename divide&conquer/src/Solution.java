import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by zouxuan on 7/27/16.
 */
public class Solution {

    public double myPow(double x, int n) {
        boolean flag = true;
        if (n < 0) {
            flag = false;
            n = -n;
        }
        if (n == 0) return 1;
        double result = myPow(x * x, n / 2);
        if (n % 2 != 0) {
            result *= x;
        }
        if (!flag) {
            result = 1 / result;
        }
        return result;
    }

    public ArrayList<Integer> grayCode(int n) {
        if (n == 0) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(0);
            return list;
        }
        ArrayList<Integer> result = grayCode(n - 1);
        ArrayList<Integer> tmp = new ArrayList<>(result);
        Collections.reverse(tmp);
        int k = 1 << n - 1;
        for (Integer t : tmp) {
            result.add(t | k);
        }
        return result;
    }

    public void surroundedRegions(char[][] board) {
        if (board == null || board.length <= 2 || board[0].length <= 2) {
            return;
        }
        int row = board.length;
        int column = board[0].length;
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < column - 1; j++) {
                if (board[i][j] == 'O') {
                    if (isSurrounded(board, i, j)) {
                        capture(board);
                    } else {
                        free(board);
                    }
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (board[i][j] == 'F') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private boolean isSurrounded(char[][] board, int i, int j) {
        int row=board.length;
        int column=board[0].length;
        if(i<0||i>=row||j<0||j>=column) return false;
        if(board[i][j]=='X'||board[i][j]=='T') return true;
        board[i][j]='T';
        boolean result=true;
        result=isSurrounded(board,i-1,j)&&isSurrounded(board,i+1,j)&&isSurrounded(board,i,j-1)
                &&isSurrounded(board,i,j+1);
        return result;
    }



    private void capture(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'T') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void free(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'T') {
                    board[i][j] = 'F';
                }
            }
        }
    }
}
