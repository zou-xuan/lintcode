import java.util.*;

/**
 * Created by zouxuan on 7/7/16.
 */
public class Solution {
    PriorityQueue<Integer> queue;
    int top;

    public Solution(int k) {
        // initialize your data structure here.
        queue=new PriorityQueue<>();
        top=k;
    }

    public void add(int num) {
       if(queue.size()<top){
           queue.offer(num);
       }else{
           if(queue.peek()<num){
               queue.poll();
               queue.offer(num);
           }
       }
    }

    public List<Integer> topk() {
        Iterator<Integer> it=queue.iterator();
        List<Integer> list=new ArrayList<>();
        while(it.hasNext()){
            list.add(it.next());
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1<o2?1:-1;
            }
        });
        return list;
    }

    public static void main(String[] args){
        Solution s=new Solution(3);
        s.add(3);
        s.add(10);
        s.topk();
    }
}
