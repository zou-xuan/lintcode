import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by zouxuan on 11/11/16.
 */
public class TrackerTest {

    @Test
    public void testAddCall() throws Exception {
        Tracker t=new Tracker();
        ApiCall one=new ApiCall("first",100,false,1);
        ApiCall two=new ApiCall("first",200,true,2);
        ApiCall three=new ApiCall("two",150,true,2);
        ApiCall four=new ApiCall("two",200,true,5);
        t.addCall(one);
        t.addCall(two);
        t.addCall(three);
        t.addCall(four);
        HashMap<String,Statistics> result=t.getAndReset(true,false);
        assertEquals(result.get("first").total_count,2);
        assertEquals(result.get("first").total_success,1);
        assertEquals(result.get("first").total_elapsed,300);
        assertEquals(result.get("first").avg_elapsed,150,1e-3);
        assertEquals(result.get("two").total_count,2);
        assertEquals(result.get("two").total_success,2);
        assertEquals(result.get("two").total_elapsed,350);
        assertEquals(result.get("two").avg_elapsed,175,1e-3);
        ApiCall five=new ApiCall("first",100,false,1);
        t.addCall(five);
        HashMap<String,Statistics> reset_result=t.getAndReset(true,false);
        assertEquals(reset_result.get("first").total_count,1);
        assertEquals(reset_result.get("first").total_success,0);
        assertEquals(reset_result.get("first").total_elapsed,100);
        assertEquals(reset_result.get("first").avg_elapsed,100,1e-3);
    }

}