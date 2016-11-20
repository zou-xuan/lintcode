import java.util.HashMap;

/**
 * Created by zouxuan on 11/7/16.
 */
class UnionFind {
    HashMap<Integer, Integer> map;

    public UnionFind(int n) {
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, i);
        }
    }

    int find(int x) {
        int parent = map.get(x);
        while (parent != map.get(parent)) {
            parent = map.get(parent);
        }
        int next;
        while (x != map.get(x)) {
            next = map.get(x);
            map.put(x, parent);
            x = next;
        }
        return parent;
    }

    void union(int x, int y) {
        int x_parent = find(x);
        int y_parent = find(y);
        if (x_parent != y_parent) {
            map.put(x_parent, y_parent);
        }
    }

}

public class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (n - 1 != edges.length) {
            return false;
        }
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < edges.length; i++) {
            if (uf.find(edges[i][0]) == uf.find(edges[i][1])) {
                return false;
            }
            uf.union(edges[i][0], edges[i][1]);
        }
        return true;
    }

    public void solveSudoku(char[][] board) {
        fillBoard(board);
    }

    public boolean fillBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for(char c='1';c<='9';c++){
                        if(isValid(board,i,j,c)){
                            board[i][j]=c;
                            if(fillBoard(board)){
                                return true;
                            }
                            else{
                                board[i][j]='.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
    }

    private boolean isValid(char[][] board,int row,int column,char c){
        for(int i=0;i<9;i++){
            if(board[row][i]==c){
                return false;
            }
            if(board[i][column]==c){
                return false;
            }
        }
        int cell=(row/3)*3+column/3;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                int curt=(i/3)*3+j/3;
                if(cell==curt){
                    if(board[i][j]==c){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
