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
        if(this.startNode.nodeNumbers == goalNode.nodeNumbers){
            System.out.println("Goal Node Found at 0 depth");
            System.out.println(startNode.nodeNumbers);
        }
        Stack<Node> stack = new Stack<>();
        ArrayList<Node> explored = new ArrayList<>();
        ArrayList<Node> path = new ArrayList<>();
        stack.push(startNode);
        while(!stack.isEmpty()){
            Node current = stack.pop();
            explored.add(current);
            if(current.nodeNumbers == goalNode.nodeNumbers){
                path.add(current);
                while (current.getParentNode()!= null){
                    path.add(current.getParentNode());
                    current = current.getParentNode();
                }
                Collections.reverse(path);
                for (Node pa:path){
                    System.out.print(pa.nodeNumbers+",");
                }
                System.out.println();
                for(Node n:explored){
                System.out.print(n.nodeNumbers+",");
                }

                return true;
            }
            else {
                try {
                    current.generateChildren();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (current.getChildren().isEmpty())
                    return false;
                else {
//                    Collections.reverse(current.getChildren());
//                    for (Node n:current.getChildren())
//                    stack.push(n);
                     ArrayList<Node> reverseChildren = current.getChildren();
                      Collections.reverse(reverseChildren);
                      stack.addAll(reverseChildren);

                }
            }
        }
    return false;
    }

}
