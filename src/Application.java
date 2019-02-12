public class Application {
    public static void main(String[] args) {
        Parser p = new Parser();
        Visitable visitable=p.go();
        DepthFirstIterator.traverse(visitable,new VisitorOne());
        DepthFirstIterator.traverse(visitable,new VisitorTwo());
        System.out.println("fertig");
    }
}
