import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends AbstractSearch{
    Node startNode;
    Node goalNode;

    public BreadthFirstSearch(Node startNode, Node goalNode) {
        super(startNode, goalNode);
        this.startNode = startNode;
        this.goalNode = goalNode;
    }

    public boolean compute(){
        if (this.startNode.nodeNumbers == (goalNode.nodeNumbers)) {
            System.out.println("Goal Node Found!");
            System.out.println(startNode.nodeNumbers);
        }
        Queue<Node> queue = new LinkedList<>();
        ArrayList<Node> explored = new ArrayList<>();
        ArrayList<Node> path = new ArrayList<>();
        queue.add(this.startNode);
        while (!queue.isEmpty()) {
            Node current = queue.remove();
            explored.add(current);
            if (current.nodeNumbers == (this.goalNode.nodeNumbers)) {
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
            } else {
                try {
                    current.generateChildren();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (current.getChildren().isEmpty())
                    return false;
                else
                    queue.addAll(current.getChildren());
            }

        }
            return false;

    }
}
//    public void printPath(){}
//    public void printExpandedNodes(){}

