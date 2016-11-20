import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zouxuan on 11/14/16.
 */
public class First {
    public static void main(String args[] ) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            Pattern pattern = Pattern.compile("(\\[)(.*?)(\\])");
            Matcher matcher = pattern.matcher(line);
            ArrayList<String> listMatches = new ArrayList<String>();
            while(matcher.find())
            {
                listMatches.add(matcher.group(2));
            }
            StringBuffer sb=new StringBuffer();
            for(String s:listMatches){
                sb.append(s+",");
            }
            if(sb.length()>0){
                System.out.println(sb.toString().substring(0,sb.length()-1));
            }
            else{
                System.out.println("");
            }

        }
    }
}
