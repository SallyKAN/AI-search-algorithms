import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class HillClimbing extends AbstractSearch {

    Node startNode;
    Node goalNode;

    public HillClimbing(Node startNode, Node goalNode) {
        super(startNode, goalNode);
        this.startNode = startNode;
        this.goalNode = goalNode;
    }

    @Override
    public boolean compute() {
        Stack<Node> stack = new Stack<>();
        ArrayList<Node> explored = new ArrayList<>();
        ArrayList<Node> path = new ArrayList<>();
        stack.add(startNode);
        while (!stack.isEmpty()) {
            Node current = stack.pop();
//            if (!current.isRepeated(explored)) {
//                explored.add(current);
//            }
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
                ArrayList<Node> children = current.getChildren();
                Collections.reverse(children);
                for (Node n : children) {
                    if (n.getHeuristic(goalNode) < current.getHeuristic(goalNode)) {
                        current = n;
                        stack.push(n);
                    }
                }


            }

        }
        System.out.println("No solution found.");
        print(explored);
        return false;
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

