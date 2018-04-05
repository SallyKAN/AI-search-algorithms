import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
        ArrayList<Node> explored = new ArrayList<>();
        limit = 0;
        stack.add(startNode);
        while (true){
            DepthLimitSearch dls = new DepthLimitSearch(startNode,goalNode,limit);
            if(dls.compute())
                return true;
            limit++;
        }
    }


}
