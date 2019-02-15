import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    @Test
    public void integrationTest() {
        //parses ((a|b)*abb)# to tree, VisitorOne executed, VisitorTwo executed
        //checks after that the output from VisitorTwo
        TopDownParser parser = new TopDownParser();
        Visitor visitor2 = new VisitorTwo();

        //Example regular expression
        Visitable tree = parser.parse("((a|b)*abb)#");

        DepthFirstIterator.traverse(tree, new VisitorOne());
        DepthFirstIterator.traverse(tree, visitor2);

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
    @Test
    public void wrongRegularExpressionTest(){
        TopDownParser parser = new TopDownParser();
        //return null, exception
        String testExpressions1[]= {
                "(abcd",
                "cdsd)#",
                "(sdsad)",
                "(sdsada#",
                "",
                "()#",
                "(())#",
                "(sasd&wa/)#"
        };
        for(int i=0; i<testExpressions1.length;i++){
            assertNull(parser.parse(testExpressions1[i]));
        }

        //dont return null, no exception
        String testExpressions2[]= {
                "(a|b*c*)#",
                "(a|b)*abb)#",
                "(((ac|ad*)ab|(da)*))#",
                "(abc)#",
                "(a*b?c*)#",
                "(abc|abb|acc)#"
        };
        for(int i=0; i<testExpressions2.length;i++){
            assertNotNull(parser.parse(testExpressions2[i]));
        }
    }

}