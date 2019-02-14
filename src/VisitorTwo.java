import java.util.SortedMap;
import java.util.TreeMap;

public class VisitorTwo implements Visitor {
    //Attributes
    public final SortedMap<Integer, FollowposTableEntry> followposTableEntrySortedMap;

    public VisitorTwo() //Constructor
    {
        //create a Map
        followposTableEntrySortedMap = new TreeMap<>();
    }

    //visit Methods
    @Override
    public void visit(OperandNode node) { //no childs (Blattknoten)
        //Insert position, position from Followpos and the Symbol in the Map
        FollowposTableEntry followposTableEntry = new FollowposTableEntry(node.position, node.symbol);
        followposTableEntrySortedMap.put(node.position, followposTableEntry);

        //System.out.println(followposTableEntrySortedMap);
    }

    @Override
    public void visit(BinOpNode node) { //two childs
        if(node.operator == "°") // check, if its a concatenation
        {
            //iterates over all lastpos positions i from left Child
            for(int lastPosition: ((SyntaxNode) node.left).lastpos)
            {
                for (int firstPosition : ((SyntaxNode) node.right).firstpos)
                {
                    //update the corresponding entry in Map
                    followposTableEntrySortedMap.get(lastPosition).followpos.add(firstPosition);
                }
            }

        }
    }

    @Override
    public void visit(UnaryOpNode node) { //one Child (innerer Knoten)
        //check, if its a Kleene star or a Kleene plus
        if (node.operator.equals("*") || node.operator.equals("+"))
        {
            for(int lastPosition : node.lastpos)
            {
                for (int firstPosition : node.firstpos)
                {
                    //update the corresponding entry in Map
                    followposTableEntrySortedMap.get(lastPosition).followpos.add(firstPosition);
                }
            }
        }
    }
}
