import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Greedy extends AbstractSearch{

    Node startNode;
    Node goalNode;

    public Greedy(Node startNode, Node goalNode) {
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
        while (!nodeStack.isEmpty()){
//            int max = Collections.min(valueStack);
//            Node current = new Node(max);
            Node current = nodeStack.pop();
            Stack<Node> reverseStack = nodeStack;
            Collections.reverse(reverseStack);
            for (Node n: reverseStack){
                if(n.getHeuristic(goalNode) < current.getHeuristic(goalNode))
                    current = n;
            }
            explored.add(current);
            if(current.nodeNumbers == goalNode.nodeNumbers){
                path.add(current);
                do{
                    path.add(current.getParentNode());
                    current = current.getParentNode();
                }while (current.getParentNode() != null);
                Collections.reverse(path);
                for (Node pa:path){
                    System.out.print(pa.nodeNumbers+",");
                }
                System.out.println();
                for (Node ex : explored) {
                    System.out.print(ex.nodeNumbers+",");
                }

                return true;
            }else {
                if (current.getChildren().isEmpty())
                {
                    try {
                        current.generateChildren();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                nodeStack.clear();
                ArrayList<Node> reverseChildren = current.getChildren();
                Collections.reverse(reverseChildren);
                nodeStack.addAll(current.getChildren());

            }

        }
        return false;
    }
}
