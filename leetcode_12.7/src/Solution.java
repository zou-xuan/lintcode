/**
 * Created by zouxuan on 12/7/16.
 */
public class Solution {
    char[] buftmp = new char[4];
    int buftmp_size = 0;
    int buftmp_ptr = 0;


//    public int read(char[] buf, int n) {
//        int bufptr = 0;
//        if (n <= 0) return 0;
//        int tmpsize = 0;
//        while (buftmp_size != 0) {
//            buf[bufptr++] = buftmp[buftmp_ptr++];
//            buftmp_size--;
//        }
//        buftmp_ptr = 0;
//        while (((tmpsize = read4(buftmp)) != 0) && bufptr < n) {
//            while (bufptr < n && tmpsize > 0) {
//                buf[bufptr++] = buftmp[buftmp_ptr++];
//                tmpsize--;
//            }
//            buftmp_ptr = 0;
//        }
//        buftmp_size = tmpsize;
//        return bufptr;
//
//    }

    public static double sqrtDouble(double num) {
        if (num == 0 || num == 1) return num;
        double left = 0;
        double right = num;
        double precision = 0.0001;
        if (num < 1) {
            left = num;
            right = 1;
        }
        while (right - left > precision) {
            double mid = left + (right - left) / 2;
            if (mid * mid < num) {
                left = mid;
            } else if (mid * mid == num) {
                return mid;
            } else {
                right = mid;
            }
        }
        if (Math.abs(left * left - num) < precision) return left;
        return right;
    }

    public static void main(String[] args) {
        System.out.println(sqrtDouble(25));
    }
}
