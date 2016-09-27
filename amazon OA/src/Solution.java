import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by zouxuan on 9/22/16.
 */
public class Solution {

    static String movePlane(String command) {
        if (command == null || command.length() == 0) return "(999, 999)";
        int location_x = 0;
        int location_y = 0;
        Stack<Point> traj=new Stack<>();
        int index1 = 0;
        int index2 = 0;
        while (index1 < command.length() && index2 < command.length()) {
            while (command.charAt(index2) >= '0' && command.charAt(index2) <= '9') {
                index2++;
            }
            String numberString = command.substring(index1, index2);
            int step = 1;
            if (!numberString.equals("")) {
                step = Integer.parseInt(numberString);
            }
            char c = command.charAt(index2);
            traj.push(new Point(location_x,location_y));
            if (c == 'U') {
                location_y += step;
            } else if (c == 'D') {
                location_y -= step;
            } else if (c == 'L') {
                location_x -= step;
            } else if (c == 'R') {
                location_x += step;
            } else if (c == 'X') {
                for(int i=0;i<step;i++){
                    if(!traj.isEmpty()){
                        traj.pop();
                    }
                }
                if(traj.isEmpty()){
                    location_x=0;
                    location_y=0;
                }else{
                    Point p=traj.pop();
                    location_x=p.x;
                    location_y=p.y;
                }
            } else {
                return "(999, 999)";
            }
            index1 = index2 + 1;
            index2 = index1;
        }
        return "(" + location_x + ", " + location_y + ")";
    }

    public static void main(String[] args) {
        System.out.println(movePlane2("UDLL2X"));
    }

    static String movePlane2(String command) {
        if (command == null || command.length() == 0) return "(999, 999)";
        int location_x = 0;
        int location_y = 0;
        ArrayList<String> history=new ArrayList<>();
        int index1 = 0;
        int index2 = 0;
        while (index1 < command.length() && index2 < command.length()) {
            while (command.charAt(index2) >= '0' && command.charAt(index2) <= '9') {
                index2++;
            }
            String numberString = command.substring(index1, index2);
            int step = 1;
            if (!numberString.equals("")) {
                step = Integer.parseInt(numberString);
            }
            char c = command.charAt(index2);
            history.add(new String("(" + location_x + ", " + location_y + ")"));
            if (c == 'U') {
                location_y += step;
            } else if (c == 'D') {
                location_y -= step;
            } else if (c == 'L') {
                location_x -= step;
            } else if (c == 'R') {
                location_x += step;
            } else if (c == 'X') {
                for(int i=0;i<step;i++){
                    if(!history.isEmpty()){
                        history.remove(history.size()-1);
                    }
                }
                if(history.isEmpty()){
                    location_x=0;
                    location_y=0;
                }else{
                    String s=history.get(history.size()-1);
                    history.remove(history.size()-1);
                    s=s.substring(1,s.length()-1);
                    String[] split=s.split(",");
                    location_x=Integer.parseInt(split[0].trim());
                    location_y=Integer.parseInt(split[1].trim());
                }
            } else {
                return "(999, 999)";
            }
            index1 = index2 + 1;
            index2 = index1;
        }
        return "(" + location_x + ", " + location_y + ")";
    }


}
