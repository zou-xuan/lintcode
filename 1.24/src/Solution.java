import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouxuan on 1/24/17.
 */
public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result=new ArrayList<>();
        int w=0;
        for(int i=0;i<words.length;i=w){
            int len=-1;
            for(w=i;w<words.length&&len+words[w].length()+1<=maxWidth;w++){
                len+=words[w].length()+1;
            }
            StringBuffer sb=new StringBuffer(words[i]);
            int space=1;
            int extra=0;
            if(w!=i+1&&w!=words.length){
                space=(maxWidth-len)/(w-i-1)+1;
                extra=(maxWidth-len)%(w-i-1);
            }
            for(int j=i+1;j<w;j++){
                for(int s=0;s<space;s++){
                    sb.append(' ');
                }
                if(extra-->0){
                    sb.append(' ');
                }
                sb.append(words[j]);
            }
            int strlen=maxWidth-sb.length();
            while(strlen-->0){
                sb.append(' ');
            }
            result.add(sb.toString());
        }
        return result;
    }

    public ArrayList<Integer> addDay(ArrayList<Integer> list,int add){
        int year=list.get(0);
        int month=list.get(1);
        int day=list.get(2);
        while(add>=getDaybyMonth(year,month)){
            add-=getDaybyMonth(year,month);
            month++;
            if(month==13){
                month=1;
                year++;
            }
        }
        if(getDaybyMonth(year,month)-day>=add){
            day+=add;
        }else{
            int extra=add-(getDaybyMonth(year,month)-day);
            month++;
            day=extra;
        }
        ArrayList<Integer> result=new ArrayList<>();
        result.add(year);
        result.add(month);
        result.add(day);
        return result;
    }

    private boolean isLeapYear(int year){
        if((year%100!=0&&year%4==0)||(year%100==0&&year%400==0)){
            return true;
        }
        return false;
    }

    private int getDaybyMonth(int year,int month){
        int[] date=new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        if(isLeapYear(year)&&(month==2)){
            return 29;
        }
        return date[month-1];
    }

    public static void main(String[] args){
        Solution s=new Solution();
        ArrayList<Integer> result=new ArrayList<>();
        result.add(2015);
        result.add(4);
        result.add(21);
        System.out.println(s.addDay(result,365).toString());
    }

}
