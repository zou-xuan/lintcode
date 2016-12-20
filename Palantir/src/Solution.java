import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by zouxuan on 11/27/16.
 */


public class Solution {

    static class Tranction {
         int date;
        String name;
        boolean isBuy;
        int amount;

        public Tranction(int date, String name, boolean isBuy, int amount) {
            this.date = date;
            this.name = name;
            this.isBuy = isBuy;
            this.amount = amount;
        }
    }

    static class Flag {
        int date;
        String name;

        public Flag(int date, String name) {
            this.date = date;
            this.name = name;
        }
    }

    static HashMap<Integer, Integer> priceMap = new HashMap<>();
    static HashMap<Integer, ArrayList<Tranction>> tranctionMap = new HashMap<>();

    static String[] findPotentialInsiderTraders(String[] datafeed) {
        initData(datafeed);
        ArrayList<Tranction> record=new ArrayList<>();
        for (Integer d : tranctionMap.keySet()) {
            ArrayList<Tranction> tmp = tranctionMap.get(d);
            for (Tranction t : tmp) {
                double currentValue = t.amount * getCurrentPrice(t.date);
                for (int i = 1; i <= 3; i++) {
                    if (priceMap.containsKey(t.date + i)) {
                        double diff = priceMap.get(t.date + i) * t.amount - currentValue;
                        if ((t.isBuy && diff >= 5000000) || (!t.isBuy && diff <=-5000000)) {
                            record.add(t);
                        }
                    }
                }
            }
        }
        ArrayList<Flag> resultList = new ArrayList<>();
        for(Tranction t:record){
            resultList.add(new Flag(t.date,t.name));
        }
        Collections.sort(resultList, new Comparator<Flag>() {
            @Override
            public int compare(Flag o1, Flag o2) {
                if (o1.date == o2.date) {
                    return o1.name.compareTo(o2.name);
                } else {
                    return o1.date - o2.date;
                }
            }
        });
        String[] result = new String[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i).date + "|" + resultList.get(i).name;
        }
        return result;

    }

    static int getCurrentPrice(int date) {
        int price = 0;
        for (Integer d : priceMap.keySet()) {
            if(d==date) return priceMap.get(d);
            if (d < date) {
                price = priceMap.get(d);
            } else {
                return price;
            }
        }
        return price;
    }

    static void initData(String[] datafeed) {
        for (String s : datafeed) {
            String[] split = s.split("\\|");
            if (split.length == 2) {
                int date = Integer.parseInt(split[0]);
                int price = Integer.parseInt(split[1]);
                priceMap.put(date, price);
            } else if(split.length==4){
                int date = Integer.parseInt(split[0]);
                String name = split[1];
                boolean isBuy = split[2].equalsIgnoreCase("BUY");
                int amount = Integer.parseInt(split[3]);
                Tranction t = new Tranction(date, name, isBuy, amount);
                if (tranctionMap.containsKey(date)) {
                    tranctionMap.get(date).add(t);
                } else {
                    ArrayList<Tranction> list = new ArrayList<>();
                    list.add(t);
                    tranctionMap.put(date, list);
                }
            }
        }
    }

    public static void main(String[] args){
        String[] feed=new String[]{"2|1500","2|Tom|Sell|6000","3|500"};
        findPotentialInsiderTraders(feed);
    }
}
