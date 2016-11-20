/**
 * Created by zouxuan on 11/14/16.
 */
class OrderKey{
    String gameId;
    String teamId;
    String playerId;
    int battingOrder;

    public Order(String gameId, String teamId, String playerId, String position, int battingOrder, int subOrder) {
        this.gameId = gameId;
        this.teamId = teamId;
        this.playerId = playerId;
        this.battingOrder = battingOrder;
    }

    public String toString(){
        String result=gameId+","+teamId+","+playerId+","+battingOrder;
        return result;
    }

    public int hashCode() {
        return Integer.parseInt(gameId)+Integer.parseInt(teamId);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof OrderKey))
            return false;
        if (obj == this)
            return true;

        OrderKey rhs = (OrderKey) obj;
        return this.gameId.equals(rhs.gameId)&&this.teamId.equals(rhs.teamId)&&this.playerId

}

public class Third {
}
