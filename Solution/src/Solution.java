/**
 * Created by zouxuan on 16/5/22.
 */

class ArrayReader {

        public int get(int index);
     }
public class Solution {
    public int searchBigSortedArray(ArrayReader reader, int target) {
        int index=0;
        while(reader.get(index)<target&&reader.get(index)!=-1){
            index=index*2+1;
        }
        int start=0;
        int end=index;
        while(start+1<end){
            int mid=start+(end-start)/2;
            if(reader.get(mid)<target&&reader.get(mid)!=-1) start=mid;
            else end=mid;
        }
        if(reader.get(start)==target) return start;
        else if(reader.get(end)==target) return end;
        return -1;
    }
}
