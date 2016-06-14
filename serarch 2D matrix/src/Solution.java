/**
 * Created by zouxuan on 16/5/22.
 */
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null||matrix.length==0) return false;
        int start=0;
        int end=matrix.length*matrix[0].length-1;
        while(start+1<end){
            int mid=start+(end-start)/2;
            int row=mid/matrix[0].length;
            int column=mid%matrix[0].length;
            if(matrix[row][column]==target){
                return true;
            }
            else if(matrix[row][column]>target){
                end=mid;
            }
            else start=mid;
        }
        int row=start/matrix[0].length;
        int column=start%matrix[0].length;
        if(matrix[row][column]==target) return true;
        row=end/matrix[0].length;
        column=end%matrix[0].length;
        if(matrix[row][column]==target) return true;
        return false;
    }
}
