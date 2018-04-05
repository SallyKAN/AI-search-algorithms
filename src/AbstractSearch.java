public abstract class AbstractSearch {
    Node startNode;
    Node goalNode;

    public AbstractSearch(Node startNode, Node goalNode){
        this.startNode = startNode;
        this.goalNode = goalNode;
    }

    public abstract boolean compute();

}
