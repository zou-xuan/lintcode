import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by zouxuan on 7/30/16.
 */
class Point {
    int x;
    int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int a, int b) {
        x = a;
        y = b;
    }
}

class Line {
    double a;
    double b;

    Line(double a, double b) {
        this.a = a;
        this.b = b;
    }
}

public class Solution {

    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        set.add(n);
        while (n != 1) {
            int tmp = 0;
            while (n != 0) {
                tmp += n % 10 * n % 10;
                n /= 10;
            }
            if (set.contains(tmp)) return false;
            else {
                set.add(tmp);
                n = tmp;
            }
        }
        return true;
    }

    public int maxPoints(Point[] points) {
        HashMap<Line,Integer> map=new HashMap<>();
        if(points==null||points.length<1) return 0;
        if(points.length==1) return 1;
        int result=0;
        for(int i=0;i<points.length;i++){
            for(int j=i+1;j<points.length;j++){
                Point p1=points[i];
                Point p2=points[j];
                if(p1.x==p2.x&&p1.y==p2.y){
                    int count=0;
                    for(Point p: points){
                        if(p.x==p1.x&&p.y==p1.y) count++;
                    }
                    result = Math.max(result,count);
                }
                else{
                    Line l=formLine(p1,p2);
                    if(l==null){
                        int count=0;
                        for(Point p:points){
                            if(p.y==p1.y){
                                count++;
                            }
                        }
                        result=Math.max(result,count);
                    }
                    else if(!map.containsKey(l)){
                        int count=0;
                        for(Point p:points){
                            if(inTheLine(l,p)){
                                count++;
                            }
                        }
                        map.put(l,count);
                        result=Math.max(result,count);
                    }
                }
            }
        }
        return result;
    }

    private Line formLine(Point p1 ,Point p2){
        if(p1.y-p2.y==0) return null;
        double a=(double)(p1.x-p2.x)/(p1.y-p2.y);
        double b=(double)(p2.x*p1.y-p1.x*p2.y)/(p1.y-p2.y);
        return new Line(a,b);
    }

    private boolean inTheLine(Line l,Point p){
        return p.x==l.a*p.y+l.b?true:false;
    }

    public String minWindow(String source, String target) {
        int[] map=new int[256];
        for(char c:target.toCharArray()){
            map[c]++;
        }
        int counter=target.length();
        int start=0,end=0;
        int min=Integer.MAX_VALUE;
        String result="";
        while(end<source.length()){
            if(map[source.charAt(end++)]-->0){
                counter--;
            }
            while(counter==0){
                if(min>end-start) {
                    result=source.substring(start,end);
                    min=end-start;
                }
                if(map[source.charAt(start++)]++==0) counter++;
            }
        }
        return result;

    }


    public static void main(String[] args){
        HashMap<Line,Integer> map=new HashMap<>();
        Line l1=new Line(1,2);
        map.put(l1,1);
        Line l2=new Line(1,2);
        if(map.containsKey(l2)){
            System.out.println("Contains!");
        }else{
            System.out.println("Not Contains!");
            System.out.println(l1.hashCode());
            System.out.println(l2.hashCode());

        }
    }

}
