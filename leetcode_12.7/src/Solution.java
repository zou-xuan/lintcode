/**
 * Created by zouxuan on 12/7/16.
 */
public class Solution {
    char[] buftmp = new char[4];
    int buftmp_size = 0;
    int buftmp_ptr = 0;


    int read4(char[] buf) {
    }

    public int read(char[] buf, int n) {
        int bufptr = 0;
        if (n <= 0) return 0;
        int tmpsize = 0;
        while (buftmp_size != 0) {
            buf[bufptr++] = buftmp[buftmp_ptr++];
            buftmp_size--;
        }
        buftmp_ptr = 0;
        while (((tmpsize = read4(buftmp)) != 0) && bufptr < n) {
            while (bufptr < n && tmpsize > 0) {
                buf[bufptr++] = buftmp[buftmp_ptr++];
                tmpsize--;
            }
            buftmp_ptr = 0;
        }
        buftmp_size=tmpsize;
        return bufptr;

    }
}
