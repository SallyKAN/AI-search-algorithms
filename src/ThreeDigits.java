import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ThreeDigits {
    public static void main(String[] args) throws IOException {
        if (args.length > 0){
            List<String> lines = Files.readAllLines(Paths.get(args[1]));
            Node startNode = new Node(Integer.parseInt(lines.get(0)));
            List<Integer> forbNumbers = new ArrayList<>();
            if (lines.size()== 3 && (!lines.get(2).isEmpty())) {
                String[] forbStrings = lines.get(2).split(",");
                for (String st : forbStrings) {
                    forbNumbers.add(Integer.valueOf(st));
                }
            }
            startNode.setForbNumbers(forbNumbers);
            startNode.generateChildren();
            Node goalNode = new Node(Integer.parseInt(lines.get(1)));
            if (args[0].equals("B")) {
                BreadthFirstSearch bfs = new BreadthFirstSearch(startNode, goalNode);
                bfs.compute();
            }
            else if (args[0].equals("D")){
                DepthFirstSearch dfs  = new DepthFirstSearch(startNode,goalNode);
                dfs.compute();
            }
            else if(args[0].equals("I")){
                IterativeDS ids = new IterativeDS(startNode,goalNode);
                ids.compute();
            }
            else if (args[0].equals("G")){
                Greedy gr = new Greedy(startNode,goalNode);
                gr.compute();
            }
            else if (args[0].equals("H")){
                HillClimbing hi = new HillClimbing(startNode,goalNode);
                hi.compute();
            }
            else if (args[0].equals("A")){
                AStar as = new AStar(startNode,goalNode);
                as.compute();
            }
        }

    }
}
