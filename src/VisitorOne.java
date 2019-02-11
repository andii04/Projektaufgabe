import java.util.Collections;
import java.util.Set;

public class VisitorOne implements Visitor {
    int counter;

    @Override
    public void visit(OperandNode node) {


        //position
        node.position = counter;

                //nullable
                if(node.equals("ε"))
                {
                    node.nullable = true;
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


    }

    @Override
    public void visit(UnaryOpNode node) {

    }

    public VisitorOne() {
        counter = 0;
    }
}
