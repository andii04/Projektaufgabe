public class Application {
    public static void main(String[] args) {
        Visitable visitable=null;
        DepthFirstIterator.traverse(visitable,new VisitorOne());
        //DepthFirstIterator.traverse(visitable,new SecondVisitor());
    }
}
