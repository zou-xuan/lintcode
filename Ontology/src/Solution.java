import java.util.*;

/**
 * Created by zouxuan on 11/28/16.
 */
class QuestionNode {
    HashMap<Character, QuestionNode> children;
    int questionCount;

    public QuestionNode(){
        children=new HashMap<>();
        questionCount=0;
    }

    public void addQuestion(String question, int index) {
        if (index == question.length()) {
            return;
        }
        char c = question.charAt(index);
        if (!children.containsKey(c)) {
            children.put(c, new QuestionNode());
        }
        this.questionCount++;
        children.get(c).addQuestion(question, index + 1);

    }

    public int find(String prefix, int index) {
        if (index == prefix.length()) {
            return this.questionCount;
        }
        char c = prefix.charAt(index);
        if (children.containsKey(c)) {
            return children.get(c).find(prefix, index + 1);
        } else {
            return 0;
        }
    }
}

class QuestionTrie {
    QuestionNode root;

    public QuestionTrie() {
        root = new QuestionNode();
    }

    public void addQuestion(String question) {
        root.addQuestion(question, 0);
    }

    public int findQuestion(String question) {
        return root.find(question, 0);
    }
}

class TopicTreeNode {
    String topic;
    QuestionTrie questionTrie;
    ArrayList<TopicTreeNode> children;

    public TopicTreeNode(String topic) {
        this.topic = topic;
        questionTrie = new QuestionTrie();
        children = new ArrayList<>();
    }
}


public class Solution {
    HashMap<String, TopicTreeNode> topicTreeMap = new HashMap<>();

    public void buildTopicTree(String s) {
        String[] split = s.split(" ");
        Stack<TopicTreeNode> stack = new Stack<>();
        TopicTreeNode prev = null;
        stack.push(new TopicTreeNode(""));
        for (String tmp : split) {
            if (tmp.equals("(")) {
                stack.push(prev);
            } else if (tmp.equals(")")) {
                stack.pop();
            } else {
                TopicTreeNode topicTreeNode = new TopicTreeNode(tmp);
                stack.peek().children.add(topicTreeNode);
                topicTreeMap.put(tmp, topicTreeNode);
                prev = topicTreeNode;
            }
        }
    }

    public void addQuestionToTopic(String topic_question) {
        String[] split = topic_question.split(":");
        String topic = split[0].trim();
        String question = split[1].trim();
        TopicTreeNode node = topicTreeMap.get(topic);
        node.questionTrie.addQuestion(question);
    }

    public int countBFS(String topic, String questionPrefix) {
        Queue<TopicTreeNode> queue = new LinkedList<>();
        TopicTreeNode topicNode = topicTreeMap.get(topic);
        int count = 0;
        queue.offer(topicNode);
        while (!queue.isEmpty()) {
            TopicTreeNode curt = queue.poll();
            count+=curt.questionTrie.findQuestion(questionPrefix);
            for (TopicTreeNode n : curt.children) {
                queue.offer(n);
            }
        }
        return count;
    }

    public void processQuery(String s) {
        int index = s.indexOf(" ");
        String topic = s.substring(0, index);
        String question = s.substring(index + 1);
        int result = countBFS(topic, question);
        System.out.println(result);
    }

    public void process() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> input = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            input.add(line);
        }
        String topic_string = input.get(1);
        buildTopicTree(topic_string);
        int question_num = Integer.parseInt(input.get(2));
        for (int i = 3; i < 3 + question_num; i++) {
            addQuestionToTopic(input.get(i));
        }
        int query_num = Integer.parseInt(input.get(3 + question_num));
        for (int i = question_num + 4; i < question_num + 4 + query_num; i++) {
            processQuery(input.get(i));
        }
    }

    public static void main(String[] args) {
        Solution s=new Solution();

        String[] input=new String[]{
                "6",
                "Animals ( Reptiles Birds ( Eagles Pigeons Crows ) )",
                "5",
                "Reptiles: Why are many reptiles green?",
                "Birds: How do birds fly?",
                "Eagles: How endangered are eagles?",
                "Pigeons: Where in the world are pigeons most densely populated?",
                "Eagles: Where do most eagles live?",
                "4",
                "Eagles How en",
                "Birds Where",
                "Reptiles Why do",
                "Animals Wh"
        };
        s.process(Arrays.asList(input));
    }

}
