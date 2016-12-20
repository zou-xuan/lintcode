import java.util.*;

/**
 * Created by zouxuan on 12/19/16.
 */
public class NewSolution {

    static class Tranction {
        String name;
        boolean isBuy;
        int price;
        int amount;

        public Tranction(String name, boolean isBuy, int currentPrice, int amount) {
            this.name = name;
            this.isBuy = isBuy;
            this.price = currentPrice;
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

    static String[] findPotentialInsiderTraders(String[] datafeed) {
        if(datafeed==null||datafeed.length==0){
            String[] r=new String[1];
            return r;
        }
        HashMap<String,Flag> flagMap=new HashMap<>();
        HashMap<Integer, ArrayList<Tranction>> map=new HashMap<>();
        int currentPrice=0;
        for(String s:datafeed){
            String[] split=s.split("\\|");
            int date=Integer.parseInt(split[0]);
            if(split.length==2){
                currentPrice=Integer.parseInt(split[1]);
                for(int d=date-3;d<date;d++){
                    if(map.containsKey(d)){
                        for(Tranction t:map.get(d)){
                            String flagString=d+"|"+t.name;
                            if(flagMap.containsKey(flagString)){
                                continue;
                            }
                            boolean insider=false;
                            if (t.isBuy){
                                insider=(currentPrice-t.price)*t.amount>=5000000;
                            }
                            else{
                                insider=(t.price-currentPrice)*t.amount>=5000000;
                            }
                            if (insider){
                                flagMap.put(flagString,new Flag(d,t.name));
                            }
                        }
                    }
                }
            }
            else{
                String name=split[1];
                boolean isBuy=split[2].length()==3;
                int amount=Integer.parseInt(split[3]);
                if(!map.containsKey(date)){
                    map.put(date,new ArrayList<Tranction>());
                }
                map.get(date).add(new Tranction(name,isBuy,currentPrice,amount));
            }
        }
        ArrayList<Flag> flaglist=new ArrayList<>();
        for(Map.Entry<String,Flag> f:flagMap.entrySet()){
            flaglist.add(f.getValue());
        }
        Collections.sort(flaglist, new Comparator<Flag>() {
            @Override
            public int compare(Flag o1, Flag o2) {
                if (o1.date == o2.date) {
                    return o1.name.compareTo(o2.name);
                } else {
                    return o1.date - o2.date;
                }
            }
        });
        String[] result = new String[flaglist.size()];
        for (int i = 0; i < flaglist.size(); i++) {
            result[i] = flaglist.get(i).date + "|" + flaglist.get(i).name;
        }
        return result;
    }

    public static void main(String[] args){
        String total="0|1000\n" +
                "0|Shilpa|BUY|30000\n" +
                "0|Will|BUY|50000\n" +
                "0|Tom|BUY|40000\n" +
                "0|Kristi|BUY|15000\n" +
                "1|Kristi|BUY|11000\n" +
                "1|Tom|BUY|1000\n" +
                "1|Will|BUY|19000\n" +
                "1|Shilpa|BUY|25000\n" +
                "2|1500\n" +
                "2|Will|SELL|7000\n" +
                "2|Shilpa|SELL|8000\n" +
                "2|Kristi|SELL|6000\n" +
                "2|Tom|SELL|9000\n" +
                "3|500\n" +
                "38|1000\n" +
                "78|Shilpa|BUY|30000\n" +
                "79|Kristi|BUY|60000\n" +
                "80|1100\n" +
                "81|1200";
//        String[] feed=new String[]{"2|1500","2|Tom|Sell|6000","3|500"};
        String[] feed=total.split("\\n");
        findPotentialInsiderTraders(feed);
    }

}
