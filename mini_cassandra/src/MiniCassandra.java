import java.util.*;

/**
 * Created by zouxuan on 7/22/16.
 */

public class Column {
    public int key;
    public String value;

    public Column(int key, String value) {
        this.key = key;
        this.value = value;
    }
}

public class MiniCassandra {
    HashMap<String,ArrayList<Column>> map;

    public MiniCassandra() {
        // initialize your data structure here.
        map=new HashMap<>();
    }

    /**
     * @param raw_key a string
     * @param column_start an integer
     * @param column_end an integer
     * @return void
     */
    public void insert(String raw_key, int column_key, String column_value) {
        // Write your code here
        Column c=new Column(column_key,column_value);
        if(map.containsKey(raw_key)){
            ArrayList<Column> list=map.get(raw_key);
            for(Column tmp:list){
                if(tmp.key==column_key){
                    tmp.value=column_value;
                    map.put(raw_key,list);
                    return;
                }
            }
            map.get(raw_key).add(c);
        }else{
            ArrayList<Column> list=new ArrayList<>();
            list.add(c);
            map.put(raw_key,list);
        }
    }

    /**
     * @param raw_key a string
     * @param column_start an integer
     * @param column_end an integer
     * @return a list of Columns
     */
    public List<Column> query(String raw_key, int column_start, int column_end) {
        // Write your code here
        List<Column> result=new ArrayList<>();
        if(!map.containsKey(raw_key)) return result;
        ArrayList<Column> list=map.get(raw_key);
        Collections.sort(list, new Comparator<Column>() {
            @Override
            public int compare(Column o1, Column o2) {
                return o1.key-o2.key;
            }
        });
        for(Column c:list){
            if(c.key>=column_start&&c.key<=column_end){
                result.add(c);
            }else if(c.key>column_end){
                break;
            }
        }
        return result;
    }
}
