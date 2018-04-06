import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.abs;

public class Node {
    int nodeNumbers;
    int lastChanged = -1;
    int level = 0;
    Node firstChild;
    Node secondChild;
    Node thirdChild;
    Node forthChild;
    Node fifthChild;
    Node sixthChild;
    Node parentNode;
    List<Integer> forbNumbers;
    public Node(){}
    public Node(int nodeNumbers){
        this.nodeNumbers = nodeNumbers;
    }
    public void generateChildren() throws IOException {
        if (this.lastChanged == -1){
            this.firstChild = GenerateChild(this,"sub",nodeNumbers,0);
            this.secondChild = GenerateChild(this,"add",nodeNumbers,0);
            this.thirdChild = GenerateChild(this,"sub",nodeNumbers,1);
            this.forthChild = GenerateChild(this,"add",nodeNumbers,1);
            this.fifthChild = GenerateChild(this,"sub",nodeNumbers,2);
            this.sixthChild = GenerateChild(this,"add",nodeNumbers,2);
        }
        if (this.lastChanged == 0){
            this.firstChild = GenerateChild(this,"sub",nodeNumbers,1);
            this.secondChild = GenerateChild(this,"add",nodeNumbers,1);
            this.thirdChild = GenerateChild(this,"sub",nodeNumbers,2);
            this.forthChild = GenerateChild(this,"add",nodeNumbers,2);
            this.fifthChild = null;
            this.sixthChild = null;
        }
        if (this.lastChanged == 1){
            this.firstChild = GenerateChild(this,"sub",nodeNumbers,0);
            this.secondChild = GenerateChild(this,"add",nodeNumbers,0);
            this.thirdChild = GenerateChild(this,"sub",nodeNumbers,2);
            this.forthChild = GenerateChild(this,"add",nodeNumbers,2);
            this.fifthChild = null;
            this.sixthChild = null;
        }
        if (this.lastChanged == 2){
            this.firstChild = GenerateChild(this,"sub",nodeNumbers,0);
            this.secondChild = GenerateChild(this,"add",nodeNumbers,0);
            this.thirdChild = GenerateChild(this,"sub",nodeNumbers,1);
            this.forthChild = GenerateChild(this,"add",nodeNumbers,1);
            this.fifthChild = null;
            this.sixthChild = null;
        }
    }
    static public Node GenerateChild(Node parentNode, String operation,int parentNodeNumber,int location) throws IOException {
        List<String> builder = new ArrayList<>();
        String formatted = String.format("%03d", parentNodeNumber);
        for (int i = 0;i <formatted.length();i++) {
            builder.add(String.valueOf(formatted.charAt(i)));
        }
        int digitNumber = Integer.valueOf(builder.get(location));
        if(operation.equals("sub") && (digitNumber!=0))
            digitNumber--;
        else if (operation.equals("add") && (digitNumber!=9))
            digitNumber++;
        else
            return null;
        builder.set(location,String.valueOf(digitNumber));
        int returnInt = Integer.valueOf(String.join("",builder));
//        System.out.println(builder.toString());
//        StringBuilder bu = new StringBuilder();
//        for (String s:builder){
//            bu.append(s);
//        }
//        System.out.println(bu.toString());
//        int returnInt = Integer.valueOf(bu.toString());
        if(parentNode.forbNumbers.contains(returnInt)){
            return null;
        }
        Node childNode = new Node();
        childNode.setParentNode(parentNode);
        childNode.setNodeNumbers(returnInt);
        childNode.setLastChanged(location);
        childNode.setLevel(parentNode.level + 1);
        childNode.setForbNumbers(parentNode.forbNumbers);
        return childNode;
    }
    public ArrayList<Node> getChildren(){
        ArrayList<Node> childNodes = new ArrayList<>();
        if ( this.firstChild != null)
            childNodes.add(firstChild);
        if (  this.secondChild != null)
            childNodes.add(secondChild);
        if ( this.thirdChild != null)
            childNodes.add(thirdChild);
        if ( this.forthChild != null)
            childNodes.add(forthChild);
        if ( this.fifthChild != null)
            childNodes.add(fifthChild);
        if ( this.sixthChild != null)
            childNodes.add(sixthChild);
        return childNodes;
     }

//     public ArrayList<Node> generateAllNodes(int startNumber, int goalNumber){
//         ArrayList<Node> allNodes = new ArrayList<>();
//         Node node = new Node(startNumber);
//         allNodes.add(node);
//         ArrayList<Node> nodes = node.getChildren();
//         for(Node n: nodes){
//             allNodes.add(n);
//             do {
//                 Node childNode = new Node(n.nodeNumbers);
//                 allNodes.add(childNode);
//             }while (n.nodeNumbers != goalNumber);
//         }
//     }
    public boolean removeChild(Node n){
        return false;
    }
    public int getNodeNumbers(){
        return nodeNumbers;
    }
    public int getHeuristic(Node goalNode){
//        int abValue = abs(this.nodeNumbers - goalNode.nodeNumbers);
////        int sum = 0;
////        while (abValue > 0) {
////            sum = sum + abValue % 10;
////            abValue = abValue / 10;
        int sum=0;
        for (int i = 0;i<3;i++){
            sum = sum + abs(this.getDigitList().get(i) - goalNode.getDigitList().get(i));
        }
        return sum;
    }
    public List<Integer> getDigitList(){
        int digit = this.nodeNumbers;
        List<Integer> digitList = new ArrayList<>();
        while (digit > 0) {
            digitList.add(digit % 10 );
            digit = digit / 10;
        }
        Collections.reverse(digitList);
        return digitList;
    }
    public int getCostSoFar() {
//        Node back = this.getParentNode();
        return this.level;
    }
    public int getTotalCost(Node startNode,Node goalNode)  {
        return this.getHeuristic(goalNode)+ this.getCostSoFar();
    }
    public List<Integer> getForbidden(){
        return this.forbNumbers;
    }
    public void setNodeNumbers(int nodeNumbers){
        this.nodeNumbers = nodeNumbers;
    }
    public int getLastChanged(){ return lastChanged;}
    public void setLastChanged(int lastChanged){this.lastChanged = lastChanged;}
    public void setForbNumbers(List<Integer> forbNumbers){
        this.forbNumbers = forbNumbers;
    }
    public void setParentNode(Node parentNode){this.parentNode = parentNode;}
    public void setLevel(int level){this.level = level;}
    public int getLevel(){return this.level;}
    public Node getParentNode(){return this.parentNode;}
    public static void main(String[] agrs) throws CloneNotSupportedException {
        Node test = new Node(311);
        Node startNode = new Node(320);
        Node goalNode = new Node(110);
        System.out.println(test.getTotalCost(startNode,goalNode));
        System.out.println();
//        System.out.println(test.getDigitList());
//        System.out.println(test.getCostSoFar(startNode));
    }

}
