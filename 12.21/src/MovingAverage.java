import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zouxuan on 12/22/16.
 */
public class MovingAverage {
    int size;
    Queue<Integer> queue;
    int sum;

    public MovingAverage(int size) {
        this.size=size;
        sum=0;
        queue=new LinkedList<>();
    }

    public double next(int val) {
        if(queue.size()>=size){
            sum-=queue.poll();
            queue.offer(val);
            sum+=val;
        }
        else{
            queue.offer(val);
            sum+=val;
        }
        return sum*1.0/queue.size();
    }
}
