public class Application {
    public static void main(String[] args) {
        //visitable visitable=null;
        //DepthFirstIterator.traverse(visitable,new VisitorOne());
        //DepthFirstIterator.traverse(visitable,new SecondVisitor());
        Parser p = new Parser();
        p.go();
    }
}
