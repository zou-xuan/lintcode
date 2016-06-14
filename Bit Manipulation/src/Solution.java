/**
 * Created by zouxuan on 16/5/30.
 */
public class Solution {

    public static int bitSwapRequired(int a, int b) {
        int xor = a ^ b;
        int result = 0;
        while (xor != 0) {
            result += xor & 1;
            xor = xor >>> 1;
        }
        return result;
    }

    public static void main(String[] args)
    {
        Solution s=new Solution();
        s.updateBits(-521,0,31,31);
    }

    public long trailingZeros(long n) {
        long result = 0;
        while (n > 0) {
            result += n / 5;
            n = n / 5;
        }
        return result;
    }

    public int updateBits(int n, int m, int i, int j) {
        int shifti = (-1) << i;
        shifti = ~shifti;
        int shiftj=0;
        if (j!=31){
            shiftj= (-1) << (j + 1);
        }
        int shift = shifti | shiftj;
        n = n & shift;
        m = m << i;
        return n | m;

    }

    public int numTrees(int n) {
        if(n==0||n==1) return 1;
        int[] totalnum=new int[n+1];
        totalnum[0]=1;
        totalnum[1]=1;
        for(int i=2;i<=n;i++){
            int tmp=0;
            for(int j=0;j<i;j++){
                tmp=totalnum[i-1-j]*totalnum[j];
            }
            totalnum[i]=tmp;
        }
        return totalnum[n];
    }

}
