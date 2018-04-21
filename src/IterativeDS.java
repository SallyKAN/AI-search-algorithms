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
        if(this.startNode.nodeNumbers == goalNode.nodeNumbers){
            path.add(startNode);
            explored.add(startNode);
            print(path);
            print(explored);
            return true;
        }
        while (true){
            DepthLimitSearch dls = new DepthLimitSearch(startNode,goalNode,limit,explored);
            for (Node n:dls.compute()){
                explored.add(n);
            }
            if (explored.size()<=1000){
                if(dls.isFound){
                    print(explored);
                    return true;
                }
                limit++;
            }else {
                noSolutionPrint(explored);
                return false;
            }
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
    public void noSolutionPrint(ArrayList<Node> path){
        System.out.println("No solution found.");
        List<String> pathNumber = new ArrayList<>();
        for (int i=0;i < 1000;i++) {
            String formatted = String.format("%03d", path.get(i).nodeNumbers);
            pathNumber.add(formatted);
        }
        System.out.println(String.join(",",pathNumber));

    }
}
