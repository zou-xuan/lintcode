import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zouxuan on 7/8/16.
 */
public class AnimalShelter {

    class Animal{
        String name;
        int type;
        public Animal(String name,int type){
            this.name=name;
            this.type=type;
        }
    }

    Queue<Animal> queue;

    public AnimalShelter() {
        queue=new LinkedList<>();
    }

    /**
     * @param name a string
     * @param type an integer, 1 if Animal is dog or 0
     * @return void
     */
    void enqueue(String name, int type) {
        queue.offer(new Animal(name,type));
    }

    public String dequeueAny() {
        return queue.poll().name;
    }

    public String dequeueDog() {
        if(queue.peek().type==1) return queue.poll().name;
        Iterator<Animal> it=queue.iterator();
        while(it.hasNext()){
            Animal curt=it.next();
            if(curt.type==1){
                queue.remove(curt);
                return curt.name;
            }
        }
        return "";
    }

    public String dequeueCat() {
        if(queue.peek().type==0) return queue.poll().name;
        Iterator<Animal> it=queue.iterator();
        while(it.hasNext()){
            Animal curt=it.next();
            if(curt.type==0){
                queue.remove(curt);
                return curt.name;
            }
        }
        return "";
    }
}
