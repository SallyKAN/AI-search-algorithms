import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class DepthLimitSearch {

    Node startNode;
    Node goalNode;
    int depth;
    int limit;
    public DepthLimitSearch(Node startNode, Node goalNode,int limit){
        this.startNode = startNode;
        this.goalNode = goalNode;
        this.limit = limit;
    }
    public boolean compute() {
        Stack<Node> stack = new Stack<>();
        ArrayList<Node> explored = new ArrayList<>();
        stack.add(startNode);
        Node currentN = stack.peek();
        depth = 0;
        while (!stack.isEmpty()) {
            if (depth <= limit) {
                Node current = stack.pop();
                if (current.nodeNumbers == goalNode.nodeNumbers) {
                    for (Node n : explored) {
                        System.out.print(n.nodeNumbers + ",");
                    }
                    return true;
                } else {
                    explored.add(current);

//                    Collections.reverse(current.getChildren());
//                    for (Node n:current.getChildren())
//                    stack.push(n);
                    try {
                        currentN.generateChildren();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                System.out.println("Goal Node not found within depth limit");
                return false;
            }

        }
        for (Node n : explored) {
            System.out.print(n.nodeNumbers + ",");
        }
        ArrayList<Node> reverseChildren = currentN.getChildren();
        Collections.reverse(reverseChildren);
        stack.addAll(reverseChildren);
        depth++;
        return false;
        }
    }

