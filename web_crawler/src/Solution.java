
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zouxuan on 7/26/16.
 */
class HtmlHelper {
    public static List<String> parseUrls(String url) {
    }

    ;
    // Get all urls from a webpage of given url.
}

public class Solution {
    HashSet<String> set = new HashSet<>();
    Queue<String> queue = new LinkedList<>();

    public List<String> crawler(String url) {
        List<String> list = HtmlHelper.parseUrls(url);
        List<String> result = new ArrayList<>();
        queue.offer(url);
        set.add(url);
        while (!queue.isEmpty()) {
            String s = queue.poll();
            result.add(s);
            List<String> next = HtmlHelper.parseUrls(s);
            for (String n : next) {
                if (!set.contains(n) && n.contains("wikipedia")) {
                    queue.offer(n);
                    set.add(n);
                }
            }
        }
        return result;
    }
}

class CrawlerThread extends Thread {
    private static BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private static HashSet<String> set = new HashSet<>();
     static List<String> result = new ArrayList<>();

    public static void setFirstUrl(String url) {
        try {
            queue.put(url);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        set.add(url);
    }
    @Override
    public void run() {
        while (true) {
            String url = "";
            try {
                url = queue.take();
            } catch (InterruptedException e) {
                break;
            }
            result.add(url);
            List<String> next = HtmlHelper.parseUrls(url);
            for (String n : next) {
                if (!set.contains(n) && n.contains("wikipedia")) {
                    try {
                        queue.put(n);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    set.add(n);
                }
            }
        }
    }

    public static  List<String> getResults(){
        return result;
    }
}

class Solution2 {
    public List<String> crawler(String url) {
        CrawlerThread.setFirstUrl(url);
        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(new CrawlerThread());
            threads[i].start();
        }
        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0;i<4;i++){
            threads[i].stop();
        }
        List<String> result=CrawlerThread.getResults();
        return result;
    }
}

