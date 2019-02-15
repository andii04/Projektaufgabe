import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    @Test
    public void integrationTest() {
        Parser parser = new Parser();
        Visitor visitor2 = new VisitorTwo();
        parser.setRegularExpression("((a|b)*abb)#");
        parser.parse();
        Visitable visitable = parser.getTree();
        DepthFirstIterator.traverse(visitable, new VisitorOne());
        DepthFirstIterator.traverse(visitable, visitor2);

        SortedMap<Integer, FollowposTableEntry> table = ((VisitorTwo) visitor2).followposTableEntrySortedMap;

        HashSet<Integer> testing = new HashSet<>();
        testing.add(1);
        testing.add(2);
        testing.add(3);

        assertEquals(testing, table.get(1).followpos);
        assertEquals(testing, table.get(2).followpos);
        testing.clear();
        testing.add(4);
        assertEquals(testing, table.get(3).followpos);
        testing.clear();
        testing.add(5);
        assertEquals(testing, table.get(4).followpos);
        testing.clear();
        testing.add(6);
        assertEquals(testing, table.get(5).followpos);
        testing.clear();
        assertEquals(testing, table.get(6).followpos);
    }
}