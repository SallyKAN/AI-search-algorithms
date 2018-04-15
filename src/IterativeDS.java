import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class IterativeDS extends AbstractSearch {
    Node startNode;
    Node goalNode;
    int limit;

    public IterativeDS(Node startNode,Node goalNode){
        super(startNode,goalNode);
        this.startNode = startNode;
        this.goalNode = goalNode;

    }
    public boolean compute() {
        Stack<Node> stack = new Stack<>();
        limit = 0;
        stack.add(startNode);
        ArrayList<Node> explored = new ArrayList<>();
        ArrayList<Node> path = new ArrayList<>();
        while (true){
            DepthLimitSearch dls = new DepthLimitSearch(startNode,goalNode,limit);
            for (Node n:dls.compute()){
                explored.add(n);
            }
            if(dls.isFound){
                print(explored);
                return true;
            }
            limit++;
        }
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
