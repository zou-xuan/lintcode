
/**
 * Created by zouxuan on 11/20/16.
 */
public class Solution {
    public String removeKdigits(String num, int k) {
        for (int i = 0; i < k; i++) {
            num = removeOne(num);
        }
        int i = 0;
        for (; i < num.length(); i++) {
            if (num.charAt(i) != '0') {
                break;
            }
        }
        int index = i == num.length() ? i - 1 : i;
        return num.substring(index);
    }

    private String removeOne(String num) {
        if (num.length() < 2) {
            return "0";
        }
        String min = num.substring(1);
        for (int i = 0; i < num.length(); i++) {
            String newNum = num.substring(0, i) + num.substring(i + 1, num.length());
            if (newNum.compareTo(min) < 0) {
                min = newNum;
            }
        }
        return min;
    }

    public String removeKdigits(String num, int k) {
        char[] stack = new char[num.length()];
        int digit = num.length() - k;
        int top = 0;
        for (char c : num.toCharArray()) {
            while (top > 0 && stack[top - 1] > c && k > 0) {
                top--;
                k--;
            }
            stack[top++] = c;
        }
        int index = 0;
        while (index <= digit&&stack[index] == '0') {
            index++;
        }
        return index==digit?"0":new String(stack,index,digit-index);

    }


}
