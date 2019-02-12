import java.util.SortedMap;
import java.util.TreeMap;

public class VisitorTwo implements Visitor {
    //Attributes
    public SortedMap<Integer, FollowposTableEntry> followposTableEntrySortedMap;

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
    }

    @Override
    public void visit(BinOpNode node) { //two childs
        visit(node.left);
        visit(node.right);
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
        visit(node.subNode);
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

    public void visit(Visitable visitable)
    {
        //assign the respective method
        if (visitable instanceof OperandNode)
        {
            OperandNode operandNode = (OperandNode) visitable;
            operandNode.accept(this);
        }
        if (visitable instanceof BinOpNode)
        {
            BinOpNode binOpNode = (BinOpNode) visitable;
            binOpNode.accept(this);
        }
        if (visitable instanceof UnaryOpNode)
        {
            UnaryOpNode unaryOpNode = (UnaryOpNode) visitable;
            unaryOpNode.accept(this);
        }
    }
}
