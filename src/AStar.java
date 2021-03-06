import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


public class AStar extends AbstractSearch {
    Node startNode;
    Node goalNode;

    public AStar(Node startNode, Node goalNode) {
        super(startNode, goalNode);
        this.startNode = startNode;
        this.goalNode = goalNode;
    }

    @Override
    public boolean compute() {
        Stack<Node> nodeStack = new Stack<>();
        ArrayList<Node> explored = new ArrayList<>();
        ArrayList<Node> path = new ArrayList<>();
        nodeStack.add(startNode);
        if(this.startNode.nodeNumbers == goalNode.nodeNumbers){
            path.add(startNode);
            explored.add(startNode);
            print(path);
            print(explored);
            return true;
        }
        while (!nodeStack.isEmpty()) {
            Node current = nodeStack.peek();
            Stack<Node> reverseStack = nodeStack;
            Collections.reverse(reverseStack);
            int nValue;
            int currentValue;
            for (Node n : reverseStack) {
                nValue = n.getTotalCost(startNode, goalNode);
                currentValue = current.getTotalCost(startNode, goalNode);
                if (nValue < currentValue) {
                    current = n;
                }
            }
            explored.add(current);
            if (current.nodeNumbers == goalNode.nodeNumbers) {
                path.add(current);
                do {
                    path.add(current.getParentNode());
                    current = current.getParentNode();
                } while (current.getParentNode() != null);
                Collections.reverse(path);
                print(path);
                print(explored);
                return true;
            } else {
                if (current.getChildren().isEmpty()) {
                    try {
                        current.generateChildren();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                nodeStack.remove(current);
                ArrayList<Node> reverseChildren = current.getChildren();
                Collections.reverse(reverseChildren);
                nodeStack.addAll(current.getChildren());
                if (explored.size() >= 1000) {
                    System.out.println("No solution found.");
                    print(explored);
                    return false;
                }

            }
        }
        return false;
    }

    public void print(ArrayList<Node> path) {
        List<String> pathNumber = new ArrayList<>();
        for (Node n : path) {
            String formatted = String.format("%03d", n.nodeNumbers);
            pathNumber.add(formatted);
        }
        System.out.println(String.join(",", pathNumber));
    }
}
