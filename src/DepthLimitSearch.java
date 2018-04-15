import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DepthLimitSearch {

    Node startNode;
    Node goalNode;
    int depth;
    int limit;
    boolean isFound = false;
    ArrayList<Node> totalExplored;

    public DepthLimitSearch(Node startNode, Node goalNode, int limit, ArrayList<Node>
                            totalExplored) {
        this.startNode = startNode;
        this.goalNode = goalNode;
        this.limit = limit;
        this.totalExplored = totalExplored;
    }

    public ArrayList<Node> compute() {
        Stack<Node> stack = new Stack<>();
        ArrayList<Node> explored = new ArrayList<>();
        ArrayList<Node> exploredOnce = new ArrayList<>();
        ArrayList<Node> path = new ArrayList<>();
        stack.add(startNode);
        depth = 0;
        while (depth <= limit) {
            while (!stack.isEmpty()) {
                Node current = stack.pop();
//                if (!current.isRepeated(explored)) {
//                    explored.add(current);
//                }
                while (current.isRepeated(exploredOnce)){
                    if(stack.isEmpty())
                        break;
                    current = stack.pop();
                }
                if (current.isRepeated(exploredOnce)) {
                    break;
                }
                exploredOnce.add(current);
                if (current.nodeNumbers == goalNode.nodeNumbers) {
                    path.add(current);
                    do {
                        path.add(current.getParentNode());
                        current = current.getParentNode();
                    } while (current.getParentNode() != null);
                    Collections.reverse(path);
                    if ((totalExplored.size()+ exploredOnce.size()) <= 1000) {
                        print(path);
                    }
                    isFound = true;
                    return exploredOnce;

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
        for (Node n : exploredOnce) {
            explored.add(n);
        }
//        if (totalExplored.size() == 1000){
//            System.out.println("No solution found.");
//        }
        return explored;
    }
    public void print(ArrayList<Node> path) {
        List<String> pathNumber = new ArrayList<>();
        for (Node n : path) {
            String formatted = String.format("%03d", n.nodeNumbers);
            pathNumber.add(formatted);
        }
        System.out.println(String.join(",",pathNumber));
    }
}

