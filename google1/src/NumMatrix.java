/**
 * Created by zouxuan on 9/18/16.
 */
public class NumMatrix {
    int[][] sum;
    int[][] matrix;

    public NumMatrix(int[][] matrix) {
        if(matrix==null||matrix.length==0||matrix[0].length==0) return;
        this.matrix=matrix;
        int row = matrix.length;
        int column = matrix[0].length;
        sum = new int[row][column];
        sum[0][0] = matrix[0][0];
        for (int i = 1; i < row; i++) {
            sum[i][0] = sum[i - 1][0] + matrix[i][0];
        }
        for (int i = 1; i < column; i++) {
            sum[0][i] = sum[0][i - 1] + matrix[0][i - 1];
        }
        for(int i=1;i<row;i++){
            for(int j=1;j<column;j++){
                sum[i][j]=sum[i-1][j]+sum[i][j-1]-sum[i-1][j-1]+matrix[i][j];
            }
        }
    }

    public void update(int row, int col, int val) {
        int diff=val-matrix[row][col];
        matrix[row][col]=val;
        for(int i=row;i<matrix.length;i++){
            for(int j=col;j<matrix[0].length;j++){
                sum[i][j]+=diff;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int bigPart=getSum(row2,col2);
        int minus1=getSum(row1-1,col2);
        int minus2=getSum(row2,col1-1);
        int add=getSum(row1-1,col1-1);
        return bigPart-minus1-minus2+add;
    }

    private int getSum(int row,int column){
        if(row<0||column<0) return 0;
        else{
            return sum[row][column];
        }
    }

}
