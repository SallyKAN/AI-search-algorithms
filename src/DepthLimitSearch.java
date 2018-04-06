import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class DepthLimitSearch {

    Node startNode;
    Node goalNode;
    int depth;
    int limit;

    public DepthLimitSearch(Node startNode, Node goalNode, int limit) {
        this.startNode = startNode;
        this.goalNode = goalNode;
        this.limit = limit;
    }

    public boolean compute() {
        Stack<Node> stack = new Stack<>();
        ArrayList<Node> explored = new ArrayList<>();
        ArrayList<Node> path = new ArrayList<>();
        stack.add(startNode);
        depth = 0;
        while (depth <= limit) {
            while (!stack.isEmpty()) {
                Node current = stack.pop();
                path.add(current);
                if (current.nodeNumbers == goalNode.nodeNumbers) {
                    for (Node n : path) {
                        System.out.print(n.nodeNumbers + ",");
                    }
                    for (Node n: explored){
                        System.out.print(n.nodeNumbers + ",");
                    }
                    return true;

                } else {
//                    Collections.reverse(current.getChildren());
//                    for (Node n:current.getChildren())
//                    stack.push(n);
                    if (current.getChildren().isEmpty()) {
                        try {
                            current.generateChildren();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (current.level < limit){
                    ArrayList<Node> reverseChildren = current.getChildren();
                    Collections.reverse(reverseChildren);
                    stack.addAll(reverseChildren);
                    }

                }
            }
            depth++;

        }
        for (Node n : path) {
            explored.add(n);
        }
        return false;
    }
}

