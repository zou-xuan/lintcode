import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by zouxuan on 12/12/16.
 */
public class Ontology {
    static class QuestionTrieNode {

        int questionCount;
        Map<Character, QuestionTrieNode> children;

        public QuestionTrieNode() {
            this.questionCount = 0;
            this.children = new HashMap<>();
        }
    }

    static class QuestionTrie {

        // The root of this QuestionTrie
        QuestionTrieNode root;

        public QuestionTrie() {
            this.root = new QuestionTrieNode();
        }

        // Function to add a question into the QuestionTrie, updating the count for each prefix.
        void addQuestion(String question) {
            QuestionTrieNode currNode = root;
            int currIndex = 0;
            while (currIndex < question.length()) {
                currNode.questionCount++;
                char currChar = question.charAt(currIndex);
                if (!currNode.children.containsKey(currChar)) {
                    currNode.children.put(currChar, new QuestionTrieNode());
                }
                currNode = currNode.children.get(currChar);
                currIndex++;
            }
            currNode.questionCount++;
        }

        // Function to get the number of times we see a question prefix in this QuestionTrie.
        int getQuestionPrefixCount(String questionPrefix) {
            QuestionTrieNode currNode = root;
            int currIndex = 0;
            while (currIndex < questionPrefix.length()) {
                char currChar = questionPrefix.charAt(currIndex);
                if (!currNode.children.containsKey(currChar)) {
                    return 0;
                }
                currNode = currNode.children.get(currChar);
                currIndex++;
            }
            return currNode.questionCount;
        }
    }

    static class TopicTreeNode {

        String topic;
        Stack<String> questionsLiteral;
        QuestionTrie questions;
        List<TopicTreeNode> children;
        int depth;

        // Constructor to build a TopicTreeNode with topic and depth.
        public TopicTreeNode(String topic, int depth)
        {
            this.topic = topic;
            this.questionsLiteral = new Stack<>();
            this.questions = null;
            this.children = new ArrayList<>();
            this.depth = depth;
        }

        // Function to record a question for the topic by adding
        // it to the questionsLiteral Stack.
        void addQuestion(String question) {
            this.questionsLiteral.push(question);
        }
    }

    static void buildTopicOntologyTreeHelper(
            String[] flattenedTree,
            Map<String, TopicTreeNode> topicTreeMap)
    {
        // Stack to keep track of parent nodes.
        Stack<TopicTreeNode> parentStack = new Stack<>();

        // Initialize root node in tree. This is the node above the root topic.
        parentStack.push(new TopicTreeNode("", 0));
        for (int i = 0; i < flattenedTree.length; i++) {
            if (flattenedTree[i].equals("(")) {
                continue;
            }
            if (flattenedTree[i].equals(")")) {
                parentStack.pop();
                continue;
            }

            // Set its depth to the size of the stack, representing how many parent topics
            // are above it.
            TopicTreeNode currNode = new TopicTreeNode(flattenedTree[i], parentStack.size());
            parentStack.peek().children.add(currNode);
            topicTreeMap.put(currNode.topic, currNode);
            if (i < flattenedTree.length-1 && flattenedTree[i+1].equals("(")) {
                parentStack.push(currNode);
            }
        }
    }

    static Map<String, TopicTreeNode> buildTopicOntology(String flattenedTree) {

        Map<String, TopicTreeNode> topicTreeMap = new HashMap<>();
        String[] flattenedTreeArr = flattenedTree.split(" ");
        buildTopicOntologyTreeHelper(flattenedTreeArr, topicTreeMap);
        return topicTreeMap;
    }

    static void addQuestionToTopic(
            Map<String, TopicTreeNode> topicOntologyTreeMap,
            String topicQuestion)
    {
        int indexOfColon = topicQuestion.indexOf(':');
        String topic = topicQuestion.substring(0,indexOfColon);
        String question = topicQuestion.substring(indexOfColon+2);
        topicOntologyTreeMap.get(topic).addQuestion(question);
    }

    static void mergeTrieHelper(QuestionTrieNode parentNode, QuestionTrieNode childNode) {

        parentNode.questionCount += childNode.questionCount;
        for (Character childsChild : childNode.children.keySet()) {

            // If parent has this prefix node, we continue to recurse on that node.
            if (parentNode.children.keySet().contains(childsChild)) {
                mergeTrieHelper(parentNode.children.get(childsChild), childNode.children.get(childsChild));
            } else {
                // If parent does not have the node, add it in from the child.
                parentNode.children.put(childsChild, childNode.children.get(childsChild));
            }
        }
    }

    static void mergeTrie(QuestionTrie parentTrie, QuestionTrie childTrie) {
        mergeTrieHelper(parentTrie.root, childTrie.root);
    }

    static int bfsQuestionPrefix(
            Map<String, TopicTreeNode> topicOntologyTreeMap,
            String topic,
            String questionPrefix)
    {
        // Get the node from the tree for this topic.
        TopicTreeNode topicSubTreeRoot = topicOntologyTreeMap.get(topic);

        // If the trie for this topic is already built, answer the query.
        if (topicSubTreeRoot.questions != null) {
            return topicSubTreeRoot.questions.getQuestionPrefixCount(questionPrefix);
        }

        Queue<TopicTreeNode> bfsFrontier = new LinkedList<>();
        bfsFrontier.add(topicSubTreeRoot);
        QuestionTrie currTrie = new QuestionTrie();

        while (!bfsFrontier.isEmpty()) {

            TopicTreeNode currChildTopic = bfsFrontier.remove();

            // If a child's trie is not null, merge it with the current trie, currTrie.
            if (currChildTopic.questions != null) {
                mergeTrie(currTrie, currChildTopic.questions);

                // Set the child trie to null once it is consumed since we do not need
                // it anymore; saves memory since this is the only pointer to it.
                currChildTopic.questions = null;
                continue;
            }
            // Consume all of the child's questions from its questionsLiteral stack.
            while(!currChildTopic.questionsLiteral.isEmpty()) {
                currTrie.addQuestion(currChildTopic.questionsLiteral.pop());
            }
            bfsFrontier.addAll(currChildTopic.children);
        }

        // Set the current topic's TopicTreeNode's QuestionTrie to currTrie.
        topicSubTreeRoot.questions = currTrie;
        // Answer the query.
        return currTrie.getQuestionPrefixCount(questionPrefix);
    }

    static int processQuery(
            Map<String, TopicTreeNode> topicOntologyTreeMap,
            String query)
    {
        int indexOfSpace = query.indexOf(' ');
        String topic = query.substring(0,indexOfSpace);
        String questionPrefix = query.substring(indexOfSpace+1);
        return bfsQuestionPrefix(topicOntologyTreeMap, topic, questionPrefix);
    }

    static void orderQuery(
            Map<String, TopicTreeNode> topicOntology,
            Map<String, Queue<Integer>> ordered,
            List<String>[] sorted,
            String query,
            int order)
    {
        int ordering = topicOntology.get(query.substring(0,query.indexOf(' '))).depth;
        if (sorted[ordering] == null) {
            sorted[ordering] = new ArrayList<>();
        }
        sorted[ordering].add(query);
        if (!ordered.containsKey(query)) {
            ordered.put(query, new LinkedList<Integer>());
        }
        ordered.get(query).add(order);
    }

    static void doOntology(
            Map<String, TopicTreeNode> topicOntology,
            Map<String, Queue<Integer>> ordered,
            List<String>[] sorted,
            int[] results)
    {
        for (int i = sorted.length-1; i>=0; i--) {
            if (sorted[i] != null) {
                for (String query : sorted[i]) {
                    // Set the next ordering of this query to the result.
                    results[ordered.get(query).remove()] = processQuery(topicOntology, query);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        // 1. Initialize IO stuff.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        // 2. Get number of topics.
        int N = Integer.parseInt(br.readLine());

        // 3. Get flattened topic tree.
        String flattenedTree = br.readLine();

        // 4. Build our tree map.
        Map<String, TopicTreeNode> topicOntology = buildTopicOntology(flattenedTree);

        // 5. Get number of questions.
        int M = Integer.parseInt(br.readLine());

        // 6. Add all questions to our topic ontology tree.
        for (int i = 0; i < M; i++) {
            addQuestionToTopic(topicOntology, br.readLine());
        }

        // 7. Get number of queries.
        int K = Integer.parseInt(br.readLine());
        List<String>[] sortedQueries = (ArrayList<String> []) new ArrayList[N];
        Map<String, Queue<Integer>> orderedQueries = new HashMap<>();

        // 8. Create ordering for the queries based on depth.
        for (int j = 0; j < K; j++) {
            orderQuery(topicOntology, orderedQueries, sortedQueries, br.readLine(), j);
        }

        // 9. Process all queries, deepest first.
        int[] results = new int[K];
        doOntology(topicOntology, orderedQueries, sortedQueries, results);

        // 10. Print query results in the same order they came in.
        for (int res : results) {
            writer.println(res);
        }

        // 11. We are done, close IO.
        writer.flush();
        writer.close();
    }
}
