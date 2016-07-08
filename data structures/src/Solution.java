import java.util.*;

/**
 * Created by zouxuan on 7/7/16.
 */
public class Solution {
    PriorityQueue<Integer> queue;
    int top;

    public Solution(int k) {
        // initialize your data structure here.
        queue = new PriorityQueue<>();
        top = k;
    }

    public void add(int num) {
        if (queue.size() < top) {
            queue.offer(num);
        } else {
            if (queue.peek() < num) {
                queue.poll();
                queue.offer(num);
            }
        }
    }

    public List<Integer> topk() {
        Iterator<Integer> it = queue.iterator();
        List<Integer> list = new ArrayList<>();
        while (it.hasNext()) {
            list.add(it.next());
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 < o2 ? 1 : -1;
            }
        });
        return list;
    }

    public static void main(String[] args) {
        Solution s = new Solution(3);
        s.add(3);
        s.add(10);
        s.topk();
    }

    public void stackSorting(Stack<Integer> stack) {
        Stack<Integer> tmp = new Stack<>();
        while (!stack.isEmpty()) {
            int curt = stack.pop();
            if (tmp.isEmpty()) {
                tmp.push(curt);
            } else {
                if (tmp.peek() >= curt) {
                    tmp.push(curt);
                } else {
                    while (!tmp.isEmpty() && tmp.peek() < curt) {
                        int back = tmp.pop();
                        stack.push(back);
                    }
                    tmp.push(curt);
                }
            }
        }
        while (!tmp.isEmpty()) {
            stack.push(tmp.pop());
        }
    }

    class Pair {
        String word;
        int frequency;

        public Pair(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }
    }

    public String[] topKFrequentWords(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : words) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        ArrayList<Pair> list = new ArrayList<>();
        for (String s : map.keySet()) {
            list.add(new Pair(s, map.get(s)));
        }
        Collections.sort(list, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.frequency < o2.frequency) return 1;
                else if (o1.frequency > o2.frequency) return -1;
                else {
                    return o1.word.compareTo(o2.word);
                }
            }
        });
        String[] result = new String[k];
        for (int i = 0; i < k; i++) {
            result[i] = list.get(i).word;
        }
        return result;
    }

    public int longestConsecutive(int[] num) {
        HashSet<Integer> set = new HashSet<>();
        int result=1;
        for (int n : num) {
            set.add(n);
        }
        for (int n : num) {
            if (set.contains(n)) {
                int length = 1;
                int down = n - 1;
                while (set.contains(down)) {
                    set.remove(down);
                    length++;
                    down--;
                }
                int up=n+1;
                while(set.contains(up)){
                    set.remove(up);
                    length++;
                    up++;
                }
                result=Math.max(result,length);
            }
        }
        return result;
    }




}
