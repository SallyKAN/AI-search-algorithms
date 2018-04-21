import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch extends AbstractSearch{
    Node startNode;
    Node goalNode;
    public DepthFirstSearch(Node startNode, Node goalNode){
        super(startNode, goalNode);
        this.startNode = startNode;
        this.goalNode = goalNode;
    }
    public boolean compute(){
        Stack<Node> stack = new Stack<>();
        ArrayList<Node> explored = new ArrayList<>();
        ArrayList<Node> path = new ArrayList<>();
        stack.push(startNode);
        if(this.startNode.nodeNumbers == goalNode.nodeNumbers){
            path.add(startNode);
            explored.add(startNode);
            print(path);
            print(explored);
            return true;
        }
        while(!stack.isEmpty()){
            Node current = stack.pop();
//            if (current.isRepeated(explored))
//                current = stack.pop();
            while (current.isRepeated(explored)){
                if(stack.isEmpty())
                    break;
                current = stack.pop();
            }
            explored.add(current);
            //explored.add(current);
            if(current.nodeNumbers == goalNode.nodeNumbers){
                path.add(current);
                while (current.getParentNode()!= null){
                    path.add(current.getParentNode());
                    current = current.getParentNode();
                }
                Collections.reverse(path);
                print(path);
                print(explored);
                return true;
            }
            else {
                if (current.getChildren().isEmpty()) {
                    try {
                        current.generateChildren();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

//                   Collections.reverse(current.getChildren());
//                    for (Node n:current.getChildren())
//                    stack.push(n);
                ArrayList<Node> reverseChildren = current.getChildren();
                Collections.reverse(reverseChildren);
                stack.addAll(reverseChildren);
                if(explored.size()>1000) {
                    noSolutionPrint(explored);
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
        System.out.println(String.join(",",pathNumber));
    }
    public void noSolutionPrint(ArrayList<Node> path){
        System.out.println("No solution found.");
        List<String> pathNumber = new ArrayList<>();
        for (int i=0;i <1000;i++) {
            String formatted = String.format("%03d", path.get(i).nodeNumbers);
            pathNumber.add(formatted);
        }
        System.out.println(String.join(",",pathNumber));

    }
}
