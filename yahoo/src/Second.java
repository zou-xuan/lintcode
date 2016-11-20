import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by zouxuan on 11/14/16.
 */
class Order{
    String gameId;
    String teamId;
    String playerId;
    String position;
    int battingOrder;


    public Order(String gameId, String teamId, String playerId, String position, int battingOrder) {
        this.gameId = gameId;
        this.teamId = teamId;
        this.playerId = playerId;
        this.position = position;
        this.battingOrder = battingOrder;
    }

    public String toString(){
        String result=gameId+","+teamId+","+playerId+","+position+","+battingOrder;
        return result;
    }

}

public class Second {
    public static void main(String args[] ) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Order> list=new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] split=line.split(",");
            Order order=new Order(split[0],split[1],split[2],split[3],Integer.parseInt(split[4]));
            if(Integer.parseInt(split[5])>1){
                int index=0;
                while(index<list.size()){
                    Order tmp=list.get(index);
                    if(tmp.gameId.equals(order.gameId)&&tmp.teamId.equals(order.teamId)&&tmp.battingOrder==order.battingOrder){
                        break;
                    }
                    index++;
                }
                list.remove(index);
                list.add(index,order);
            }
            else{
                list.add(order);
            }
        }
        scanner.close();
        for(Order o:list){
            System.out.println(o.toString());
        }
    }
}
