import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main(String[] args) throws IOException {


//        ArrayList<Node> nodes = node.getChildren();
//        for(Node n:nodes){
//        System.out.println(n.nodeNumbers + "" + n.latsChanged);
//        }
        Node startNode = new Node(320);
        startNode.generateChildren();
        Node goalNode = new Node(110);
        goalNode.generateChildren();
//        BreadthFirstSearch bfs = new BreadthFirstSearch(startNode, goalNode);
////      AbstractSearch searchAlgo = new DepthFirstSearch(startNode,goalNode);
        IterativeDS it = new IterativeDS(startNode,goalNode);
//        Greedy gr = new Greedy(startNode,goalNode);
//        AStar as = new AStar(startNode,goalNode);
        HillClimbing hi = new HillClimbing(startNode,goalNode);
        if(it.compute())
            System.out.print("Path Found!");

    }
}
