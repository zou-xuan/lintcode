import com.sun.tools.corba.se.idl.InterfaceGen;
import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Created by zouxuan on 9/7/16.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result=new ArrayList<>();
        if(root==null) return result;
        TreeMap<Integer,ArrayList<Integer>> map=new TreeMap<>();
        Stack<TreeNode> nodeStack=new Stack<>();
        Stack<Integer> colStack=new Stack<>();
        nodeStack.push(root);
        colStack.push(0);
        while(!nodeStack.isEmpty()){
            TreeNode node=nodeStack.pop();
            int col=colStack.pop();
            if(!map.containsKey(col)){
                ArrayList<Integer> list=new ArrayList<>();
                list.add(node.val);
                map.put(col,list);
            }else{
                map.get(col).add(node.val);
            }
            if(node.right!=null){
                nodeStack.push(node.right);
                colStack.push(col+1);
            }
            if(node.left!=null){
                nodeStack.push(node.left);
                colStack.push(col-1);
            }
        }
        for(Integer key:map.keySet()){
            result.add(map.get(key));
        }
        return result;

    }
}
