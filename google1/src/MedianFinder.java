import java.io.PipedReader;
import java.util.PriorityQueue;

/**
 * Created by zouxuan on 9/20/16.
 */
public class MedianFinder {
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public MedianFinder(){
        maxHeap=new PriorityQueue<>();
        minHeap=new PriorityQueue<>();
    }

    public void addNum(int num) {
        maxHeap.add(num);
        minHeap.add(-maxHeap.poll());
        if(maxHeap.size()<minHeap.size()){
            maxHeap.add(-minHeap.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        return maxHeap.size()>minHeap.size()?maxHeap.peek()
                :(maxHeap.peek()-minHeap.peek())/2.0;
    }
}

