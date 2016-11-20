import java.util.*;

/**
 * Created by zouxuan on 11/14/16.
 */
class Score implements Comparable {
    String playid;
    int points;

    public Score(String playid) {
        this.playid = playid;
        this.points = 0;
    }

    public Score(String playid, int points) {
        this.playid = playid;
        this.points = points;
    }


    @Override
    public int compareTo(Object o) {
        Score s = (Score) o;
        if (this.points < s.points) {
            return -1;
        } else if (this.points > s.points) {
            return 1;
        } else {
            return this.playid.compareTo(s.playid);
        }
    }
}

public class Solution {
    public static void main(String args[]) throws Exception {
        PriorityQueue<Score> scores = new PriorityQueue<>();
        int MAX_SIZE = 10;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.equals("PRINT")) {
                print(scores);
            } else {
                String[] split = line.split(",");
                String playid = split[0];
                int points = (Integer.parseInt(split[1]) * 2) + Integer.parseInt(split[2]) + Integer.parseInt(split[3]) * 6 -
                        Integer.parseInt(split[4]);
                if (scores.size() < MAX_SIZE) {
                    scores.offer(new Score(playid, points));
                } else {
                    Score top = scores.peek();
                    Score newNode = new Score(playid, points);
                    if (newNode.compareTo(top) > 0) {
                        scores.poll();
                        scores.offer(newNode);
                    }
                }
            }
        }
        scanner.close();

    }

    private static void print(PriorityQueue<Score> scores) {
        ArrayList<Score> result = new ArrayList<>();
        for (Score s : scores) {
            result.add(s);
        }
        Collections.sort(result);
        Collections.reverse(result);
        System.out.println("Leaders");
        for (int i = 0; i < result.size(); i++) {
            System.out.println(String.valueOf(i + 1) + "," + result.get(i).playid + "," + result.get(i).points);
        }
    }
}
