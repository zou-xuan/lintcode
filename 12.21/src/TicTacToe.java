/**
 * Created by zouxuan on 12/22/16.
 */
public class TicTacToe {
    int[] row;
    int[] column;
    int diagoanl;
    int anti_diagonal;
    int n;

    public TicTacToe(int n) {
        row=new int[n];
        column=new int[n];
        diagoanl=0;
        anti_diagonal=0;
        this.n=n;
    }

    public int move(int i, int j, int player) {
        if(player==1){
            if(++row[i]==n) return 1;
            if(++column[j]==n) return 1;
            if(i==j){
                if(++diagoanl==n){
                    return 1;
                }
            }
            if (i+j==n-1){
                if(++anti_diagonal==n){
                    return 1;
                }
            }
        }else{
            if(--row[i]==-n) return 2;
            if(--column[j]==-n) return 2;
            if(i==j){
                if(--diagoanl==-n){
                    return 2;
                }
            }
            if (i+j==n-1){
                if(--anti_diagonal==-n){
                    return 2;
                }
            }
        }
        return 0;
    }
}
