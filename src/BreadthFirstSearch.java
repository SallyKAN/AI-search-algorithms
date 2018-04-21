import java.io.IOException;
import java.util.*;

public class BreadthFirstSearch extends AbstractSearch {
    Node startNode;
    Node goalNode;

    public BreadthFirstSearch(Node startNode, Node goalNode) {
        super(startNode, goalNode);
        this.startNode = startNode;
        this.goalNode = goalNode;
    }

    public boolean compute() {
        Queue<Node> queue = new LinkedList<>();
        ArrayList<Node> explored = new ArrayList<>();
        ArrayList<Node> path = new ArrayList<>();
        queue.add(this.startNode);
        if(this.startNode.nodeNumbers == goalNode.nodeNumbers){
            path.add(startNode);
            explored.add(startNode);
            print(path);
            print(explored);
            return true;
        }
        while (!queue.isEmpty()) {
//            System.out.println(queue.peek().nodeNumbers);
            Node current = queue.remove();
//            if (explored.size() == 47){
//                System.out.println("test");
//            }
            while (current.isRepeated(explored)){
                if(queue.isEmpty())
                    break;
                current = queue.remove();
            }
            explored.add(current);
            if (current.nodeNumbers == (this.goalNode.nodeNumbers)) {
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

                queue.addAll(current.getChildren());
//                if (current.nodeNumbers == 125)
//                    try {
//                        current.generateChildren();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                if(queue.size() > 864)
//                    System.out.println(current.nodeNumbers);
                if(explored.size()>1000) {
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
        System.out.println(String.join(",",pathNumber));
    }
}
//    public void printExpandedNodes(){}

