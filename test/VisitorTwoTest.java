import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class VisitorTwoTest {
    Visitor visitor = new VisitorTwo();
    DepthFirstIterator iterator = new DepthFirstIterator();

    @Test
    public void testVisitorTwo() {
        //Baum aus der Vorlesung wird erstellt
        //Create node 1
        OperandNode node1 = new OperandNode("a");
        node1.position = 1;
        node1.firstpos.add(1);
        node1.lastpos.add(1);

        //Create node 2
        OperandNode node2 = new OperandNode("b");
        node2.position = 2;
        node2.firstpos.add(2);
        node2.lastpos.add(2);

        //Create node 3
        BinOpNode node3 = new BinOpNode("|", node1, node2);
        node3.firstpos.add(1);
        node3.firstpos.add(2);
        node3.lastpos.add(1);
        node3.lastpos.add(2);

        //node 4
        UnaryOpNode node4 = new UnaryOpNode("*", node3);
        node4.firstpos.add(1);
        node4.firstpos.add(2);
        node4.lastpos.add(1);
        node4.lastpos.add(2);

        //Create node 5
        OperandNode node5 = new OperandNode("a");
        node5.position = 3;
        node5.firstpos.add(3);
        node5.lastpos.add(3);

        //Create node 6
        BinOpNode node6 = new BinOpNode("°", node4, node5);
        node6.lastpos.add(3);
        node6.firstpos.add(1);
        node6.firstpos.add(2);
        node6.firstpos.add(3);

        //Create node 7
        OperandNode node7 = new OperandNode("b");
        node7.position = 4;
        node7.lastpos.add(4);
        node7.firstpos.add(4);

        //Create node 8
        BinOpNode node8 = new BinOpNode("°", node6, node7);
        node8.firstpos.add(1);
        node8.firstpos.add(2);
        node8.firstpos.add(3);
        node8.lastpos.add(4);

        //Create node 9
        OperandNode node9 = new OperandNode("b");
        node9.firstpos.add(5);
        node9.lastpos.add(5);
        node9.position = 5;

        //Create node 10
        BinOpNode node10 = new BinOpNode("°", node8, node9);
        node10.firstpos.add(1);
        node10.firstpos.add(2);
        node10.firstpos.add(3);
        node10.lastpos.add(5);

        //Create node 11
        OperandNode node11 = new OperandNode("#");
        node11.position = 6;
        node11.firstpos.add(6);
        node11.lastpos.add(6);

        //Create Root
        BinOpNode root = new BinOpNode("°", node10, node11);
        root.lastpos.add(6);
        root.firstpos.add(1);
        root.firstpos.add(2);
        root.firstpos.add(3);

        iterator.traverse(root, visitor);

        HashSet<Integer> testing = new HashSet<>();
        testing.add(1);
        testing.add(2);
        testing.add(3);

        assertEquals(testing, ((VisitorTwo) visitor).followposTableEntrySortedMap.get(1).followpos);
        assertEquals(testing, ((VisitorTwo) visitor).followposTableEntrySortedMap.get(2).followpos);
        testing.clear();
        testing.add(4);
        assertEquals(testing, ((VisitorTwo) visitor).followposTableEntrySortedMap.get(3).followpos);
        testing.clear();
        testing.add(5);
        assertEquals(testing, ((VisitorTwo) visitor).followposTableEntrySortedMap.get(4).followpos);
        testing.clear();
        testing.add(6);
        assertEquals(testing, ((VisitorTwo) visitor).followposTableEntrySortedMap.get(5).followpos);
        testing.clear();
        assertEquals(testing, ((VisitorTwo) visitor).followposTableEntrySortedMap.get(6).followpos);
    }
}