public class VisitorOne implements Visitor {
    int counter;

    @Override
    public void visit(OperandNode node) {
        //position
        node.position = counter;

                //nullable
                if(node.symbol.equals("ε"))
                {
                    node.nullable = true;
                    node.firstpos.clear();
                    node.lastpos.clear();
                }
                else
                {
                    node.nullable = false;
                    //firstpos lastpos
                    node.firstpos.add(counter);
                    node.lastpos.add(counter);
                }
        counter++;
    }

    @Override
    public void visit(BinOpNode node) {

        SyntaxNode leftNode = ((SyntaxNode) node.left);
        SyntaxNode rightNode = ((SyntaxNode) node.right);

        switch (node.operator)
        {
            case "|":
                //nullable
                node.nullable = leftNode.nullable || rightNode.nullable;
                //firstpos
                node.firstpos.addAll(leftNode.firstpos);
                node.firstpos.addAll(rightNode.firstpos);
                //lastpos
                node.lastpos.addAll(leftNode.lastpos);
                node.lastpos.addAll(rightNode.lastpos);

                break;

            case "°":
                //nullable
                node.nullable = leftNode.nullable && rightNode.nullable;

                //firstpos

                if (leftNode.nullable){
                    node.firstpos.addAll(leftNode.firstpos);
                    node.firstpos.addAll(rightNode.firstpos);
                }

                else{
                    node.firstpos.addAll(leftNode.firstpos);
                }

                //Lastpos

                if(rightNode.nullable) {
                    node.lastpos.addAll(leftNode.lastpos);
                    node.lastpos.addAll(rightNode.lastpos);
                }

                break;

            default:
                System.out.println("some unexpected things happened: " + node.getClass().toGenericString() + " " + node.operator);
                break;
        }
    }

    @Override
    public void visit(UnaryOpNode node) {
        SyntaxNode subNode = ((SyntaxNode) node.subNode);

        switch(node.operator)
        {
            case"*":
                //nullable
                node.nullable = true;
                //firstpos
                node.firstpos.addAll(subNode.firstpos);
                //lastpos
                node.lastpos.addAll(subNode.lastpos);
                break;

            case"+":
                //nullable
                node.nullable = subNode.nullable;
                //firstpos
                node.firstpos.addAll(subNode.firstpos);
                //lastpos
                node.lastpos.addAll(subNode.lastpos);
                break;

            case"?":
                //nullable
                node.nullable = true;
                //firstpos
                node.firstpos.addAll(subNode.firstpos);
                //lastpos
                node.lastpos.addAll(subNode.lastpos);
                break;

            default:
                System.out.println("some unexpected things happened: " + node.getClass().toGenericString() + " " + node.operator);

        }

    }

    public VisitorOne() {
        counter = 0;
    }
}
